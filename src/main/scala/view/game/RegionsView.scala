package view.game

import controller.GameEngine
import model.world.Region

import javax.swing.{JPanel, JProgressBar}
import java.awt.{Color, Component, Dimension, Font, Graphics, Graphics2D, GridLayout}
import javax.swing.plaf.basic.BasicProgressBarUI
import javax.swing.*
import scala.collection.immutable.SortedMap
import javax.swing.ScrollPaneConstants

object RegionsView:

  class RegionsPanel(gameEngine: GameEngine) extends RefreshablePanel:
    private var activePanel: RefreshablePanel = new AllRegionsPanel(gameEngine.getRegions())
    this.showAllRegionsDetails()

    private def setNewPanel(panel: RefreshablePanel): Unit =
      this.removeAll()
      this.add(panel)
      this.activePanel = panel
      this.revalidate()
      this.repaint()

    def showAllRegionsDetails(): Unit = this.setNewPanel(new AllRegionsPanel(gameEngine.getRegions()))

    def showRegionDetails(region: Region): Unit = this.setNewPanel(SingleRegionPanel(region))

    override def refresh(): Unit = activePanel.refresh()

  class AllRegionsPanel(var regions: List[Region]) extends RefreshablePanel:
    var progressBars: Map[Region, JProgressBar] = SortedMap()
    val backgroundColor: Color = new Color(217, 217, 217)
    val barBackgroundColor: Color = new Color(35, 187, 197)
    val barForegroundColor: Color = new Color(215, 19, 19)
    this.setBackground(backgroundColor)
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

    regions.foreach(r => progressBars = progressBars + (r -> new DecimalProgressBar(barBackgroundColor, barForegroundColor)))

    progressBars.foreach((r, p) => {
      p.setMaximum(r.population)
      this.add(Box.createRigidArea(new Dimension(320, 30)))
      val regionNameLabel: JLabel = new JLabel(r.name, SwingConstants.CENTER)
      regionNameLabel.setBackground(new Color(255, 0, 0))
      this.add(regionNameLabel)
      this.add(p)
    })

    override def refresh(): Unit =
      regions.foreach(region => progressBars(region).setValue(region.infectedAmount))
  class DecimalProgressBar(bgColor: Color, fgColor: Color) extends JProgressBar :
    this.setBackground(bgColor)
    this.setForeground(fgColor)
    this.setStringPainted(true)
    this.setUI(new BasicProgressBarUI() {
      override def getSelectionBackground: Color = Color.white
      override def getSelectionForeground: Color = Color.white
    })
    override def setValue(n: Int): Unit =
      super.setValue(n)
      this.setString(BigDecimal(100.0 * n / this.getMaximum).setScale(2, BigDecimal.RoundingMode.CEILING).toString + "%")

  case class SingleRegionPanel(region: Region) extends RefreshablePanel:
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

    val infectedAmountLabel: JLabel = new JLabel(region.infectedAmount.toString)
    val infectionBar: JProgressBar = new DecimalProgressBar(new Color(35, 187, 197), new Color(215, 19, 19))
    infectionBar.setMaximum(region.population)

    val lblName = new JLabel(region.name)
    lblName.setFont(new Font("Arial", Font.PLAIN, 30))

    this.addCenter(lblName)

    val statisticsPanel: JPanel = new JPanel(new GridLayout(3, 2, 20, 15))
    statisticsPanel.add(new JLabel("population: " + String.format("%,d", region.population)))
    statisticsPanel.add(new JLabel("population density: " + region.populationDensity + "/5"))
    statisticsPanel.add(new JLabel("richness: " + region.richness + "/5"))
    statisticsPanel.add(new JLabel("climate: " + region.climate + "/3"))
    statisticsPanel.add(new JLabel("borders control: " + region.bordersControl + "/5"))
    statisticsPanel.add(new JLabel("globalization: " + region.globalization + "/5"))

    this.addCenter(statisticsPanel)
    //this.add(new JLabel("climate: " + region.climate))

    private def addCenter(comp: JComponent): Unit =
      val container: JPanel = new JPanel()
      container.add(comp)
      this.add(container)

    this.addCenter(infectedAmountLabel)
    this.add(infectionBar)

    override def refresh(): Unit =
      infectionBar.setValue(region.infectedAmount)
      infectedAmountLabel.setText(String.format("population infected: %,d", region.infectedAmount))

  case class WrapWithScrollBar(component: Component) extends JScrollPane:
    this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED)
    this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER)
    this.getVerticalScrollBar().setUnitIncrement(10);
    this.setViewportView(component)

  trait RefreshablePanel extends JPanel:
    def refresh(): Unit