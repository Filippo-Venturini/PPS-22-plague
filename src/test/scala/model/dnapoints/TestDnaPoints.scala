package model.dnapoints

import model.dnapoints.DnaPoints.Logic.{EmptyLogic, EveryXSecondsLogic, OnNewInfectedRegions, OnNewInfectedRegionsLogic, SpawnPointLogic}
import model.dnapoints.DnaPoints.{DnaPoint, DnaPointsHandler}
import model.world.RegionTypes.RegionConfiguration
import model.world.{BasicRegion, Region, World}
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestDNAPoints {

  private var dnaPointsHandler: DnaPointsHandler = DnaPointsHandler(EmptyLogic())
  private val region: Region = new BasicRegion(RegionConfiguration("Central-Europe", 60_000_000, 6, 6, 8, 9, 10))

  @Before
  def before(): Unit =
    dnaPointsHandler = DnaPointsHandler(EmptyLogic())

  @Test
  def testCollectedDnaPointsIsZeroAtStarts(): Unit =
    assertEquals(0, dnaPointsHandler.collectedPoints)

  @Test
  def testDnaPointCollectIncreaseDnaPointAmount(): Unit =
    val beforeCollectAmount: Int = dnaPointsHandler.collectedPoints
    val dnaPoint: DnaPoint = dnaPointsHandler.spawnDnaPoint(region).get
    dnaPoint.collect()
    assertEquals(beforeCollectAmount+1, dnaPointsHandler.collectedPoints)

  @Test
  def testCantCollectADnaPointMultipleTime(): Unit =
    val initialAmount: Int = dnaPointsHandler.collectedPoints
    val dnaPoint: DnaPoint = dnaPointsHandler.spawnDnaPoint(region).get
    dnaPoint.collect()
    dnaPoint.collect()
    dnaPoint.collect()
    assertEquals(initialAmount + 1, dnaPointsHandler.collectedPoints)

  @Test
  def testDnaPointsSpawnObserver(): Unit =
    var spawnedPoints: Int = 0
    dnaPointsHandler.addObserver(regionName => {
      assertEquals(regionName, region.name)
      spawnedPoints = spawnedPoints+1
    })
    dnaPointsHandler.spawnDnaPoint(region).get.collect()
    assertEquals(1, spawnedPoints)

  @Test
  def testCantSpawnDnaPointTwiceIfNotCollected(): Unit =
    var spawnedPoints: Int = 0
    dnaPointsHandler.addObserver(regionName => {
      assertEquals(regionName, region.name)
      spawnedPoints = spawnedPoints + 1
    })
    assertTrue(dnaPointsHandler.spawnDnaPoint(region).isDefined)
    assertTrue(dnaPointsHandler.spawnDnaPoint(region).isEmpty)
    assertEquals(1, spawnedPoints)

  @Test
  def testCanSpawnDnaPointTwiceIfCollected(): Unit =
    var spawnedPoints: Int = 0
    dnaPointsHandler.addObserver(regionName => {
      assertEquals(regionName, region.name)
      spawnedPoints = spawnedPoints + 1
    })
    dnaPointsHandler.spawnDnaPoint(region).get.collect()
    assertTrue(dnaPointsHandler.spawnDnaPoint(region).isDefined)
    assertEquals(2, spawnedPoints)

}
