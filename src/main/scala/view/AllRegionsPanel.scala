package view

import model.world.Region

import java.awt.{Color, Component}
import javax.swing.{Box, BoxLayout, JComponent, JLabel, JPanel, JProgressBar, SwingConstants}
import java.awt.Dimension

class AllRegionsPanel(var regions: List[Region]) extends JPanel:
  var progressBars: Map[Region, JProgressBar] = Map()
  this.setBackground(new Color(255,255,0))
  this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

  regions.foreach(r => progressBars = progressBars + (r -> new JProgressBar()))
  progressBars.foreach((r, p) => {
    p.setStringPainted(true)
    this.add(Box.createRigidArea(new Dimension(320, 30)))
    val regionNameLabel: JLabel = new JLabel(r.name, SwingConstants.CENTER)
    regionNameLabel.setBackground(new Color(255,0,0))
    this.add(regionNameLabel)
    this.add(p)
  })

  def updateRegions(updatedRegions: List[Region]): Unit =
    updatedRegions.foreach(region => progressBars(region).setValue(region.infectedAmount))
