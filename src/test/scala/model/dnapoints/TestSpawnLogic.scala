package model.dnapoints

import model.world.World

import model.world.{BasicRegion, Region, World}
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import model.world.RegionTypes.RegionConfiguration
import model.dnapoints.DnaPoints.SpawnLogic.{EmptyLogic, OnNewInfectedRegions, OnNewInfectedRegionsLogic, SpawnPointLogic}
import model.dnapoints.DnaPoints.{DnaPoint, DnaPointsHandler}

class TestSpawnLogic {

  private var russia: Region = BasicRegion(RegionConfiguration("Russia", 0, 0, 0, 0, 0, 0))
  private var usa: Region = BasicRegion(RegionConfiguration("USA", 0, 0, 0, 0, 0, 0))
  private var europe: Region = BasicRegion(RegionConfiguration("Europe", 0, 0, 0, 0, 0, 0))
  private var china: Region = BasicRegion(RegionConfiguration("China", 0, 0, 0, 0, 0, 0))
  private var brazil: Region = BasicRegion(RegionConfiguration("Brazil", 0, 0, 0, 0, 0, 0))
  private var world: World = new World(List(russia, usa, europe, china, brazil))

  @Before
  def before(): Unit =
    russia = BasicRegion(RegionConfiguration("Russia", 0, 0, 0, 0, 0, 0))
    usa = BasicRegion(RegionConfiguration("USA", 0, 0, 0, 0, 0, 0))
    europe = BasicRegion(RegionConfiguration("Europe", 0, 0, 0, 0, 0, 0))
    china = BasicRegion(RegionConfiguration("China", 0, 0, 0, 0, 0, 0))
    brazil = BasicRegion(RegionConfiguration("Brazil", 0, 0, 0, 0, 0, 0))
    world = new World(List(russia, usa, europe, china, brazil))

  @Test
  def testOnNewInfectedRegionsWithNoInfectedCountries(): Unit =
    val logic: SpawnPointLogic = OnNewInfectedRegionsLogic(world)
    assertEquals(List(), logic.evaluate())

  @Test
  def testOnNewInfectedRegionsWithOneInfectedCountry(): Unit =
    val logic: SpawnPointLogic = OnNewInfectedRegionsLogic(world)
    russia.infectedAmount = 1
    assertEquals(List(russia), logic.evaluate())

  @Test
  def testOnNewInfectedRegionsWithMultipleInfectedCountries(): Unit =
    val logic: SpawnPointLogic = OnNewInfectedRegionsLogic(world)
    russia.infectedAmount = 1
    usa.infectedAmount = 100
    assertEquals(List(russia, usa), logic.evaluate())

  @Test
  def testOnNewInfectedRegionsCountriesAreDetectedOnlyOnce(): Unit =
    val logic: SpawnPointLogic = OnNewInfectedRegionsLogic(world)
    russia.infectedAmount = 1
    logic.evaluate()
    russia.infectedAmount = 100
    assertEquals(List(), logic.evaluate())

}
