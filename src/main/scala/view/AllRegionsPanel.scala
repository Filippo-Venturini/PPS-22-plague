package view

import javax.swing.{JLabel, JPanel, JProgressBar}

class AllRegionsPanel extends JPanel:
  val progressBar: JProgressBar = new JProgressBar()
  progressBar.setValue(10)
  progressBar.setStringPainted(true)
  this.add(progressBar)
