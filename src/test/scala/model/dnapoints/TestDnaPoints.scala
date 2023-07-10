package model.dnapoints

import model.dnapoints.DnaPoints.{DnaPoint, DnaPointsHandler}
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

  @Test
  def testDnaPointCollectIncreaseDnaPointAmount(): Unit =
    val beforeCollectAmount: Int = dnaPointsHandler.collectedPoints
    val dnaPoint: DnaPoint = dnaPointsHandler.spawnDnaPoint()
    dnaPoint.collect()
    assertEquals(beforeCollectAmount+1, dnaPointsHandler.collectedPoints)

  @Test
  def testCantCollectADnaPointMultipleTime(): Unit =
    val initialAmount: Int = dnaPointsHandler.collectedPoints
    val dnaPoint: DnaPoint = dnaPointsHandler.spawnDnaPoint()
    dnaPoint.collect()
    dnaPoint.collect()
    dnaPoint.collect()
    assertEquals(initialAmount + 1, dnaPointsHandler.collectedPoints)
}
