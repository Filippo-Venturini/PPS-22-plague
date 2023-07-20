package model.vaccine
import model.infection.Virus

/**
 * Class that represent an handler that has the purpose to manage the vaccine's research
 */
class VaccineHandler(val vaccineLogic: VaccineLogic):
  private var isResearchStarted: Boolean = false
  var vaccineProgression: Double = 0.0
  
  /**
   * Compute the research step basing on the percentage and the logic specified
   *
   * @param worldInfectionPercentage the percentage of the infected population of the whole world
   */
  def computeResearchStep(worldInfectionPercentage: Double): Unit =
    if !isResearchStarted then tryStartResearch(worldInfectionPercentage)
    else vaccineProgression = vaccineLogic.researchStep(vaccineProgression)

  /**
   * Try to start the research of the vaccine
   *
   * @param worldInfectionPercentage the percentage of the infected population of the whole world
   */
  private def tryStartResearch(worldInfectionPercentage: Double): Unit =
    if vaccineLogic.canResearchStart(worldInfectionPercentage) then
      vaccineProgression = vaccineLogic.researchStep(vaccineProgression)
      this.isResearchStarted = true
