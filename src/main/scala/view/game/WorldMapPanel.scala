package view.game

import view.game.MapMouseListener

import java.awt.event.{MouseEvent, MouseListener}
import java.awt.image.BufferedImage
import java.awt.{Graphics, image}
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JButton, JPanel}

type RGBCode = (Int, Int, Int)

class WorldMapPanel extends JPanel:
  val mapImage: BufferedImage = ImageIO.read(getClass().getResource("/map.png"))
  this.addMouseListener(new MapMouseListener(mapImage))

  override def paintComponent(g: Graphics): Unit =
    super.paintComponent(g)
    g.drawImage(mapImage, 0, 0, null)

class MapMouseListener(val image: BufferedImage) extends MouseListener:
  override def mouseClicked(e: MouseEvent): Unit = println(image.getRGBCode(e.getX, e.getY));
  override def mousePressed(e: MouseEvent): Unit = {}
  override def mouseExited(e: MouseEvent): Unit = {}
  override def mouseReleased(e: MouseEvent): Unit = {}
  override def mouseEntered(e: MouseEvent): Unit = {}
extension(image: BufferedImage)
  def getRGBCode(x: Int, y: Int): RGBCode =
    val rgbValue: Int = image.getRGB(x, y)
    ((rgbValue & 0x00ff0000) >> 16, (rgbValue & 0x0000ff00) >> 8, rgbValue & 0x000000ff)