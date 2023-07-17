package model.vaccine

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestBasicVaccineLogic {
  val notEnoughInfectedPercentage: Double = 0.1
  val enoughInfectedPercentage: Double = 0.25
  val basicResearchFactor: Double = 1.0 / 3
  var vaccineProgression: Double = 0.0
  val vaccineLogic: VaccineLogic = new BasicVaccineLogic

  @Test
  def testCantStartResearch: Unit =
    assertFalse(vaccineLogic.canResearchStart(notEnoughInfectedPercentage))

  @Test
  def testCanStartResearch: Unit =
    assertTrue(vaccineLogic.canResearchStart(enoughInfectedPercentage))

  @Test
  def testComputeResearchStep: Unit =
    assertTrue(Math.abs(vaccineLogic.researchStep(vaccineProgression) - basicResearchFactor) <= 0.01)
}
