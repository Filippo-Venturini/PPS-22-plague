package model.vaccine

import org.junit.Assert.assertTrue
import org.junit.Test
import model.vaccine.VaccineLogics.given

class TestVaccineHandler {
  val vaccineHandler: VaccineHandler = new VaccineHandler
  val notEnoughInfectedPercentage: Double = 0.1
  val enoughInfectedPercentage: Double = 0.25
  val basicVaccineLogicFactor: Double = 1.0 / 600

  @Test
  def testVaccineProgressionInitiallyZero: Unit =
    assertTrue(vaccineHandler.vaccineProgression == 0.0)

  @Test
  def testCantStartResearch: Unit =
    vaccineHandler.computeResearchStep(notEnoughInfectedPercentage)
    assertTrue(vaccineHandler.vaccineProgression == 0.0)

  @Test
  def testCanStartResearch: Unit =
    vaccineHandler.computeResearchStep(enoughInfectedPercentage)
    assertTrue(vaccineHandler.vaccineProgression > 0.0)

  @Test
  def testHandlerWithBasicInfectionLogic: Unit =
    vaccineHandler.computeResearchStep(enoughInfectedPercentage)
    assertTrue(Math.abs(vaccineHandler.vaccineProgression - basicVaccineLogicFactor) <= 0.01)

}
