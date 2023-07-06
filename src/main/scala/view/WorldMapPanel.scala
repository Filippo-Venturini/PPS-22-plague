package view

import controller.GameEngine
import model.configuration.Builders.RegionIdentifier
import model.configuration.Loader
import model.configuration.Loader.{ConfigurationsLoader, RegionIdentifierFile, regionIdentifierFilePath}

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.image.BufferedImage
import java.awt.{Graphics, image}
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JButton, JPanel}

type HexCode = String

class WorldMapPanel(val gameEngine: GameEngine) extends JPanel:
  val mapImage: BufferedImage = ImageIO.read(getClass().getResource("/map.png"))
  this.addMouseListener(new MapMouseListener(mapImage, gameEngine))

  override def paintComponent(g: Graphics): Unit =
    super.paintComponent(g)
    g.drawImage(mapImage, 0, 0, null)

class MapMouseListener(val image: BufferedImage, val gameEngine: GameEngine) extends MouseListener:
  import model.configuration.Loader.ConfigurationsLoader.given
  val regions: List[RegionIdentifier] = ConfigurationsLoader.load(RegionIdentifierFile(Loader.regionIdentifierFilePath))
  override def mouseClicked(e: MouseEvent): Unit = regions
    .find(_.identifier == image.getHexCode(e.getX, e.getY))
    .foreach(r => println(r.regionName))
  override def mousePressed(e: MouseEvent): Unit = {}
  override def mouseExited(e: MouseEvent): Unit = {}
  override def mouseReleased(e: MouseEvent): Unit = {}
  override def mouseEntered(e: MouseEvent): Unit = {}
extension(image: BufferedImage)
  def getHexCode(x: Int, y: Int): HexCode = "#" + image.getRGB(x, y).toHexString.substring(2).toUpperCase
