package view.game

import utils.Iterables.getRandomElement
import controller.GameEngine
import model.configuration.builders.RegionIdentifierBuilder.RegionIdentifier
import model.configuration.Loader
import model.configuration.Loader.{ConfigurationsLoader, RegionIdentifierFile, regionIdentifierFilePath}
import view.game.RegionsView.RegionsPanel
import model.configuration.Loader.ConfigurationsLoader.given
import model.dnapoints.DnaPoints.DnaPoint

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.image.{BufferedImage, DataBufferByte}
import java.awt.{Color, Graphics, image}
import java.awt.{Graphics, Image, image}
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JButton, JPanel}

type HexCode = String
type Position = (Int, Int)
type Dimension = (Int, Int)

class WorldMapPanel(val gameEngine: GameEngine, val regionsPanel: RegionsPanel) extends JPanel with MouseClickListener:
  val mapImage: BufferedImage = ImageIO.read(getClass().getResource("/images/worldMap.png"))
  val portAndAirportIcons: BufferedImage = ImageIO.read(getClass().getResource("/images/portAndAirportIcons.png"))
  val portRoutes: BufferedImage = ImageIO.read(getClass().getResource("/images/portRoutes.png"))
  val airportRoutes: BufferedImage = ImageIO.read(getClass().getResource("/images/airportRoutes.png"))
  val regions: List[RegionIdentifier] = ConfigurationsLoader.load(RegionIdentifierFile(Loader.regionIdentifierFilePath))
  val pixelStep: Int = 10;
  val dnaPointButtonsSize: Int = 30;

  this.setBackground(new Color(255,255,255))
  this.setLayout(null)
  this.addMouseListener(this)


  val colorsMap: Map[HexCode, Iterable[Position]] = (0 until mapImage.getWidth() by pixelStep)
    .flatMap(i => (0 until mapImage.getHeight() by pixelStep).map(j => (i, j)))
    .map(pos => (pos._1, pos._2, mapImage.getHexCode(pos._1, pos._2)))
    .groupMap(pixel => pixel._3)(pixel => (pixel._1, pixel._2))

  override def paintComponent(g: Graphics): Unit =
    super.paintComponent(g)
    g.drawImage(mapImage, 0, 0, null)
    if gameEngine.arePortsEnabled then g.drawImage(portRoutes, 0, 0, null)
    if gameEngine.areAirportsEnabled then g.drawImage(airportRoutes, 0, 0, null)
    g.drawImage(portAndAirportIcons, 0, 0, null)

  def showDnaPoint(dnaPoint: DnaPoint): Unit = regions.find(_.regionName == dnaPoint.regionName) match
    case Some(regionIdentifier) => colorsMap(regionIdentifier.identifier).getRandomElement() match
      case Some(pos) =>
        this.add(DnaPointButton(dnaPoint, pos, (dnaPointButtonsSize, dnaPointButtonsSize)))
        this.repaint()
        this.revalidate()
      case _ =>
    case _ =>

  override def mouseClicked(e: MouseEvent): Unit = regions.find(_.identifier == mapImage.getHexCode(e.getX, e.getY)) match
    case Some(regionIdentifier) =>
      gameEngine.getRegion(regionIdentifier.regionName) match
        case Some(region) => regionsPanel.showRegionDetails(region)
        case _ =>
    case _ => regionsPanel.showAllRegionsDetails()

trait MouseClickListener extends MouseListener:
  override def mousePressed(e: MouseEvent): Unit = {}
  override def mouseExited(e: MouseEvent): Unit = {}
  override def mouseReleased(e: MouseEvent): Unit = {}
  override def mouseEntered(e: MouseEvent): Unit = {}
extension(image: BufferedImage)
  def getHexCode(x: Int, y: Int): HexCode = "#" + image.getRGB(x, y).toHexString.substring(2).toUpperCase

case class DnaPointButton(dnaPoint: DnaPoint, position: Position, dimension: Dimension) extends JButton:
  val img: Image = ImageIO.read(getClass().getResource("/images/dnaPointV2.png")).getScaledInstance(dimension._1, dimension._2, Image.SCALE_SMOOTH)
  this.setBounds(position._1-dimension._1/2, position._2-dimension._2/2, dimension._1, dimension._2)
  this.setOpaque(false)
  this.setContentAreaFilled(false)
  this.setBorderPainted(false)
  this.setFocusable(false)

  this.addActionListener(e => {
    dnaPoint.collect()
    this.setVisible(false)
  })
  override def paintComponent(g: Graphics): Unit =
    super.paintComponent(g)
    g.drawImage(img, 0, 0, null)