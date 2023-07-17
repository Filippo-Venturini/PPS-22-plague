package model.vaccine

trait VaccineLogic:
  protected var vaccineProgression: Double = 0.0
  def canResearchStart(totalInfectedPercentage: Double): Boolean
  def researchStep(): Unit
  def getVaccineProgression: Double = vaccineProgression

class BasicVaccineLogic extends VaccineLogic:
  private val researchFactor: Double = 1/600
  override def canResearchStart(totalInfectedPercentage: Double): Boolean = totalInfectedPercentage >= 0.2
  override def researchStep(): Unit = vaccineProgression = vaccineProgression + researchFactor
  
