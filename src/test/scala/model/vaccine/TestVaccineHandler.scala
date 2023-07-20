package model.vaccine

import model.infection.{BasicVirus, VirusConfiguration}
import org.junit.Assert.assertTrue
import org.junit.Test

class TestVaccineHandler {
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val vaccineHandler: VaccineHandler = new VaccineHandler(new BasicVaccineLogic(new BasicVirus(testVirusConfiguration)))
  val notEnoughInfectedPercentage: Double = 10.0
  val enoughInfectedPercentage: Double = 20.0
  val basicVaccineLogicFactor: Double = 0.30

  @Test
  def testVaccineProgressionInitiallyZero(): Unit =
    assertTrue(vaccineHandler.vaccineProgression == 0.0)

  @Test
  def testCantStartResearch(): Unit =
    vaccineHandler.computeResearchStep(notEnoughInfectedPercentage)
    assertTrue(vaccineHandler.vaccineProgression == 0.0)

  @Test
  def testCanStartResearch(): Unit =
    vaccineHandler.computeResearchStep(enoughInfectedPercentage)
    assertTrue(vaccineHandler.vaccineProgression > 0.0)

  @Test
  def testHandlerWithBasicInfectionLogic(): Unit =
    vaccineHandler.computeResearchStep(enoughInfectedPercentage)
    assertTrue(Math.abs(vaccineHandler.vaccineProgression - basicVaccineLogicFactor) <= 0.01)

}
