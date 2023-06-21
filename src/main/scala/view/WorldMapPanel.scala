package view

import java.awt.{Graphics, Image}
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JPanel}

class WorldMapPanel extends JPanel:
  val mapImage: Image = ImageIO.read(getClass().getResource("/world.png")).getScaledInstance(1208,600, Image.SCALE_DEFAULT)

  override def paintComponent(g: Graphics): Unit =
    super.paintComponent(g)
    g.drawImage(mapImage, 0, 0, null)
