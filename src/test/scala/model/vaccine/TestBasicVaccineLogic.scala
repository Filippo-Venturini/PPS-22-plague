package model.vaccine

import model.infection.{BasicVirus, Virus, VirusConfiguration}
import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestBasicVaccineLogic {
  val notEnoughInfectedPercentage: Double = 10.0 
  val enoughInfectedPercentage: Double = 25.0
  val basicResearchFactor: Double = 1.0 / 3
  var vaccineProgression: Double = 0.0
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val vaccineLogic: VaccineLogic = new BasicVaccineLogic(new BasicVirus(testVirusConfiguration))

  @Test
  def testCantStartResearch(): Unit =
    assertFalse(vaccineLogic.canResearchStart(notEnoughInfectedPercentage))

  @Test
  def testCanStartResearch(): Unit =
    assertTrue(vaccineLogic.canResearchStart(enoughInfectedPercentage))

  @Test
  def testComputeResearchStep(): Unit =
    assertTrue(Math.abs(vaccineLogic.researchStep(vaccineProgression) - basicResearchFactor) <= 0.01)
}
