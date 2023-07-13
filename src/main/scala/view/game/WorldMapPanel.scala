package view.game

import view.game.MapMouseListener
import controller.GameEngine
import model.configuration.Builders.RegionIdentifier
import model.configuration.Loader
import model.configuration.Loader.{ConfigurationsLoader, RegionIdentifierFile, regionIdentifierFilePath}
import view.game.Regions.RegionsPanel

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.image.BufferedImage
import java.awt.{Graphics, image}
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JButton, JPanel}

type HexCode = String

class WorldMapPanel(val gameEngine: GameEngine, val regionsPanel: RegionsPanel) extends JPanel:
  val mapImage: BufferedImage = ImageIO.read(getClass().getResource("/map.png"))
  this.addMouseListener(new MapMouseListener(mapImage, gameEngine, regionsPanel))

  override def paintComponent(g: Graphics): Unit =
    super.paintComponent(g)
    g.drawImage(mapImage, 0, 0, null)

class MapMouseListener(val image: BufferedImage, val gameEngine: GameEngine, val regionsPanel: RegionsPanel) extends MouseListener:
  import model.configuration.Loader.ConfigurationsLoader.given
  val regions: List[RegionIdentifier] = ConfigurationsLoader.load(RegionIdentifierFile(Loader.regionIdentifierFilePath))
  override def mouseClicked(e: MouseEvent): Unit = regions.find(_.identifier == image.getHexCode(e.getX, e.getY)) match
    case Some(regionIdentifier) =>
      gameEngine.getRegion(regionIdentifier.regionName) match
        case Some(region) => regionsPanel.showRegionDetails(region)
        case _ =>
    case _ => regionsPanel.showAllRegionsDetails()
  override def mousePressed(e: MouseEvent): Unit = {}
  override def mouseExited(e: MouseEvent): Unit = {}
  override def mouseReleased(e: MouseEvent): Unit = {}
  override def mouseEntered(e: MouseEvent): Unit = {}
extension(image: BufferedImage)
  def getHexCode(x: Int, y: Int): HexCode = "#" + image.getRGB(x, y).toHexString.substring(2).toUpperCase
