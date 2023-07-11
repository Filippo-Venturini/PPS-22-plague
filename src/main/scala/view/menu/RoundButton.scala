package view.menu

import javax.swing.JButton
import javax.swing.JFrame
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Graphics
import java.awt.geom.Ellipse2D
import scala.collection.StepperShape.Shape

class RoundButton(val label: String) extends JButton(label):
  this.setFocusable(false)
  this.setContentAreaFilled(false)
  
  override def paintComponent(g: Graphics): Unit =
    if (getModel.isArmed){
      g.setColor(Color.gray)
    }else{
      g.setColor(getBackground)
    }
    g.fillOval(0,0,getSize().width - 1, getSize().height - 1)
    super.paintComponent(g)

  override def paintBorder(g: Graphics): Unit =
    g.setColor(Color.darkGray)
    g.drawOval(0,0,getSize().width - 1, getSize().height -1)
    
  /*var shape: Shape
  
  def contains(x: Int, y: Int): Boolean =
    if(shape == null || !shape.getBounds().equals(getBounds())){
      shape = new Ellipse2D.Float(0,0,getWidth,getHeight)
    }
    return shape.contains(x,y)*/