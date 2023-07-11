package model.dnapoints

import model.dnapoints.DnaPoints.SpawnLogic.{EmptyLogic, OnNewInfectedRegions, OnNewInfectedRegionsLogic, SpawnPointLogic}
import model.dnapoints.DnaPoints.{DnaPoint, DnaPointsHandler}
import model.world.RegionTypes.RegionConfiguration
import model.world.{BasicRegion, Region, World}
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}

class TestDNAPoints {

  private var dnaPointsHandler: DnaPointsHandler = DnaPointsHandler(???)
  private val region: Region = new BasicRegion(RegionConfiguration("Central-Europe", 60_000_000, 6, 6, 8, 9, 10))

  @Before
  def before(): Unit =
    dnaPointsHandler = DnaPointsHandler(???)

  @Test
  def testCollectedDnaPointsIsZeroAtStarts(): Unit =
    assertEquals(0, dnaPointsHandler.collectedPoints)

  @Test
  def testDnaPointCollectIncreaseDnaPointAmount(): Unit =
    val beforeCollectAmount: Int = dnaPointsHandler.collectedPoints
    val dnaPoint: DnaPoint = dnaPointsHandler.spawnDnaPoint(region)
    dnaPoint.collect()
    assertEquals(beforeCollectAmount+1, dnaPointsHandler.collectedPoints)

  @Test
  def testCantCollectADnaPointMultipleTime(): Unit =
    val initialAmount: Int = dnaPointsHandler.collectedPoints
    val dnaPoint: DnaPoint = dnaPointsHandler.spawnDnaPoint(region)
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
    dnaPointsHandler.spawnDnaPoint(region).collect()
    assertEquals(1, spawnedPoints)

}
