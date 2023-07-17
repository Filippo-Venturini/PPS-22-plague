package model.vaccine

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestBasicVaccineLogic {
  val notEnoughInfectedPercentage: Double = 0.1
  val enoughInfectedPercentage: Double = 0.25
  val basicResearchFactor: Double = 1.0 / 600
  val vaccineLogic: VaccineLogic = new BasicVaccineLogic

  @Test
  def testVaccineProgressionInitiallyZero: Unit =
    assertTrue(Math.abs(vaccineLogic.getVaccineProgression) <= 0.01)

  @Test
  def testCantStartResearch: Unit =
    assertFalse(vaccineLogic.canResearchStart(notEnoughInfectedPercentage))

  @Test
  def testCanStartResearch: Unit =
    assertTrue(vaccineLogic.canResearchStart(enoughInfectedPercentage))

  @Test
  def testComputeResearchStep: Unit =
    assertTrue(Math.abs(vaccineLogic.getVaccineProgression) <= 0.01)
    vaccineLogic.researchStep()
    assertTrue(Math.abs(vaccineLogic.getVaccineProgression - basicResearchFactor) <= 0.01)
}
