package view

import model.world.Region

import java.awt.{Color, Component}
import javax.swing.{Box, BoxLayout, JComponent, JLabel, JPanel, JProgressBar, SwingConstants}
import java.awt.Dimension
import javax.swing.plaf.basic.BasicProgressBarUI

class AllRegionsPanel(var regions: List[Region]) extends JPanel:
  var progressBars: Map[Region, JProgressBar] = Map()
  val backgroundColor: Color = new Color(217,217,217)
  val barBackgroundColor: Color = new Color(35, 187, 197)
  val barForegroundColor: Color = new Color(215, 19, 19)
  val barUI: BasicProgressBarUI = new BasicProgressBarUI() {
    override def getSelectionBackground: Color = Color.white
    override def getSelectionForeground: Color = Color.white
  }
  this.setBackground(backgroundColor)
  this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

  regions.foreach(r => progressBars = progressBars + (r -> new JProgressBar()))
  progressBars.foreach((r, p) => {
    p.setMaximum(r.population)
    p.setBackground(barBackgroundColor)
    p.setForeground(barForegroundColor)
    p.setStringPainted(true)
    p.setUI(barUI)
    this.add(Box.createRigidArea(new Dimension(320, 30)))
    val regionNameLabel: JLabel = new JLabel(r.name, SwingConstants.CENTER)
    this.add(regionNameLabel)
    this.add(p)
  })

  def updateRegions(updatedRegions: List[Region]): Unit =
    updatedRegions.foreach(region => progressBars(region).setValue(region.infectedAmount))
