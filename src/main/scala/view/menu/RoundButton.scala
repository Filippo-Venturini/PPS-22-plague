package view.menu

import javax.swing.{BorderFactory, JButton, JFrame}
import java.awt.{Color, Dimension, FlowLayout, Font, Graphics, Graphics2D}
import java.awt.geom.{Ellipse2D, Point2D}
import scala.collection.StepperShape.Shape

class RoundButton() extends JButton():
  setBorder(BorderFactory.createEmptyBorder())
  setContentAreaFilled(false) // Rimuove lo sfondo del bottone
  setForeground(Color.BLACK) // Imposta il colore del testo
  setFocusPainted(false) // Rimuove l'effetto di focuss
  setText("buy")
  setFont(new Font("Arial", 12, 10))

  override def paintComponent(g: Graphics): Unit = {
    val g2d = g.asInstanceOf[Graphics2D]
    val center = new Point2D.Float(getWidth / 2.0f, getHeight / 2.0f)
    val radius = Math.min(getWidth, getHeight) / 2.0f
    val circle = new Ellipse2D.Float(center.x - radius, center.y - radius, 2.0f * radius, 2.0f * radius)
    g2d.setColor(Color.YELLOW) // Imposta il colore del cerchio
    g2d.fill(circle) // Disegna il cerchio
    super.paintComponent(g) // Disegna il testo del bottone
  }