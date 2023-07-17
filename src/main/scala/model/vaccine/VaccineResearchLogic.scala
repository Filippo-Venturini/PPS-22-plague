package model.vaccine

trait VaccineLogic:
  protected var vaccineProgression: Double = 0.0
  def canResearchStart(worldInfectionPercentage: Double): Boolean
  def researchStep(): Unit
  def getVaccineProgression: Double = vaccineProgression

class BasicVaccineLogic extends VaccineLogic:
  private val researchFactor: Double = 1.0/600
  override def canResearchStart(worldInfectionPercentage: Double): Boolean = worldInfectionPercentage >= 0.2
  override def researchStep(): Unit = vaccineProgression = vaccineProgression + researchFactor

