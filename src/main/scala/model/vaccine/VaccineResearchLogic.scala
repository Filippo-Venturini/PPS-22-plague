package model.vaccine

import model.infection.Virus

/**
 * Trait that represent a vaccine research logic
 */
trait VaccineLogic:
  /**
   * @param worldInfectionPercentage the percentage of the infected population of the whole world
   * @return true if the research can start basing on the percentage given by input
   */
  def canResearchStart(worldInfectionPercentage: Double): Boolean

  /**
   * @param vaccineProgression the actual progress level of the vaccine
   * @return the new amount of the progression of the vaccine
   */
  def researchStep(vaccineProgression: Double): Double

/**
 * Class that represent a simple vaccine logic
 */
class BasicVaccineLogic(val virus: Virus) extends VaccineLogic:
  /**
   * Factor that represent the increment of the research in every step
   */
  private val researchFactor: Double = 0.25

  /**
   * Factor that represent the slow down applied to the research basing on the virus vaccine resistance
   */
  private val slowDownFactor: Double = 0.005

  /**
   * The research will start at 20% of total infected population in the entire world
   *
   * @param worldInfectionPercentage the percentage of the infected population of the whole world
   *  @return true if the research can start basing on the percentage given by input
   */
  override def canResearchStart(worldInfectionPercentage: Double): Boolean = worldInfectionPercentage >= 30.0

  /**
   * The research step will increment the progression by an internal research factor
   *
   * @param vaccineProgression the actual progress level of the vaccine
   *  @return the new amount of the progression of the vaccine
   */
  override def researchStep(vaccineProgression: Double): Double =
    vaccineProgression + researchFactor - virus.vaccineResistance * slowDownFactor
    

