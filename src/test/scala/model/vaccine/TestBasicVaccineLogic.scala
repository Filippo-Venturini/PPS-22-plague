package model.vaccine

import model.infection.{BasicVirus, Virus, VirusConfiguration}
import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestBasicVaccineLogic {
  val notEnoughInfectedPercentage: Double = 10.0 
  val enoughInfectedPercentage: Double = 25.0
  val basicResearchFactor: Double = 0.30
  var initialVaccineProgression: Double = 0.0
  val testVirusConfiguration: VirusConfiguration = VirusConfiguration("DHT11", 0, 0, 0, 0, 0, 0, 0, false, false)
  val testVirus: Virus = new BasicVirus(testVirusConfiguration)
  val vaccineLogic: VaccineLogic = new BasicVaccineLogic(testVirus)

  @Test
  def testCantStartResearch(): Unit =
    assertFalse(vaccineLogic.canResearchStart(notEnoughInfectedPercentage))

  @Test
  def testCanStartResearch(): Unit =
    assertTrue(vaccineLogic.canResearchStart(enoughInfectedPercentage))

  @Test
  def testComputeResearchStep(): Unit =
    assertTrue(vaccineLogic.researchStep(initialVaccineProgression) > 0.0)

  @Test
  def testSlowDownResearch(): Unit =
    this.testVirus.vaccineResistance = 10
    assertTrue(vaccineLogic.researchStep(initialVaccineProgression) < basicResearchFactor)
}
