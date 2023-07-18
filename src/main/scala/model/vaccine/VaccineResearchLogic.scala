package model.vaccine

trait VaccineLogic:
  def canResearchStart(worldInfectionPercentage: Double): Boolean
  def researchStep(vaccineProgression: Double): Double

class BasicVaccineLogic extends VaccineLogic:
  private val researchFactor: Double = 1.0 / 3
  override def canResearchStart(worldInfectionPercentage: Double): Boolean = worldInfectionPercentage >= 0.2
  override def researchStep(vaccineProgression: Double): Double = vaccineProgression + researchFactor

