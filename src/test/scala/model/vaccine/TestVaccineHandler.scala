package model.vaccine

import model.infection.{BasicVirus, Virus, VirusConfiguration}
import org.junit.Assert.assertTrue
import org.junit.Test

class TestVaccineHandler {
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val testVirus: Virus = new BasicVirus(testVirusConfiguration)
  val vaccineHandler: VaccineHandler = new VaccineHandler(new BasicVaccineLogic(testVirus))
  val notEnoughInfectedPercentage: Double = 10.0
  val enoughInfectedPercentage: Double = 20.0
  val basicResearchFactor: Double = 0.30

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
  def testHandlerWithBasicInfectionLogicNoSlowdown(): Unit =
    vaccineHandler.computeResearchStep(enoughInfectedPercentage)
    assertTrue(vaccineHandler.vaccineProgression > 0.0)

  @Test
  def testHandlerWithBasicInfectionLogicAndSlowdown(): Unit =
    testVirus.vaccineResistance = 10
    vaccineHandler.computeResearchStep(enoughInfectedPercentage)
    assertTrue(vaccineHandler.vaccineProgression < basicResearchFactor)

}
