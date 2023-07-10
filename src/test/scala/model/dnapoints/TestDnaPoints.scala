package model.dnapoints

import model.dnapoints.DnaPoints.DnaPointsHandler
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestDNAPoints {

  private var dnaPointsHandler: DnaPointsHandler = DnaPointsHandler()

  @Before
  def before(): Unit =
    dnaPointsHandler = DnaPointsHandler()

  @Test
  def testCollectedDnaPointsIsZeroAtStarts(): Unit =
    assertEquals(0, dnaPointsHandler.collectedPoints)

}
