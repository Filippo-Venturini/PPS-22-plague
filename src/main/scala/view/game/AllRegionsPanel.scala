package view.game

import model.world.Region

import java.awt.{Color, Component, Dimension}
import javax.swing.plaf.basic.BasicProgressBarUI
import javax.swing.*
import scala.collection.immutable.SortedMap

class AllRegionsPanel(var regions: List[Region]) extends JPanel:
  var progressBars: Map[Region, JProgressBar] = SortedMap()
  val backgroundColor: Color = new Color(217,217,217)
  val barBackgroundColor: Color = new Color(35, 187, 197)
  val barForegroundColor: Color = new Color(215, 19, 19)
  this.setBackground(backgroundColor)
  this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

  regions.foreach(r => progressBars = progressBars + (r -> new DecimalProgressBar()))

  progressBars.foreach((r, p) => {
    p.setMaximum(r.population)
    this.configureProgressBarLayout(p)
    this.add(Box.createRigidArea(new Dimension(320, 30)))
    val regionNameLabel: JLabel = new JLabel(r.name, SwingConstants.CENTER)
    regionNameLabel.setBackground(new Color(255,0,0))
    this.add(regionNameLabel)
    this.add(p)
  })

  def configureProgressBarLayout(progressBar: JProgressBar): Unit =
    progressBar.setBackground(barBackgroundColor)
    progressBar.setForeground(barForegroundColor)
    progressBar.setStringPainted(true)
    progressBar.setUI(new BasicProgressBarUI() {
      override def getSelectionBackground: Color = Color.white
      override def getSelectionForeground: Color = Color.white
    })

  def updateRegions(updatedRegions: List[Region]): Unit =
    updatedRegions.foreach(region => progressBars(region).setValue(region.infectedAmount))

class DecimalProgressBar extends JProgressBar:
  override def setValue(n: Int): Unit = this.setString(BigDecimal(1.0 * n / this.getMaximum).setScale(2, BigDecimal.RoundingMode.CEILING).toString + "%")