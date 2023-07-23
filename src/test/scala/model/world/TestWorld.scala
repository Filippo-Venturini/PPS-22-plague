package model.world

import model.world.RegionParameters.RegionConfiguration
import model.world.Filters.{infectedButNotCompletelyRegions, infectedRegions, notInfectedRegions, totallyInfectedRegions, given}
import model.world.RegionConfigurationsForTests.*
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertTrue}

class TestWorld {
  val infectedAmount: Int = 100
  var notInfectedRegion: Region = new BasicRegion(unitedStatesConfiguration)
  var infectedRegion: Region = new BasicRegion(europeConfiguration)
  var totallyInfectedRegion: Region = new BasicRegion(japanConfiguration)
  var world: World = new World(List(notInfectedRegion, infectedRegion, totallyInfectedRegion))

  @Before
  def init(): Unit =
    infectedRegion.infectedAmount = infectedAmount
    totallyInfectedRegion.infectedAmount = totallyInfectedRegion.population

  @Test
  def testEmptyGetAllRegions(): Unit =
    world = new World(List())
    assertEquals(List(), world.getRegions)

  @Test
  def testGetAllRegions(): Unit =
    assertEquals(List(notInfectedRegion, infectedRegion, totallyInfectedRegion), world.getRegions)

  @Test
  def testEmptyGetInfectedRegions(): Unit =
    world = new World(List(notInfectedRegion))
    assertEquals(List(), world.getRegions(using infectedRegions))

  @Test
  def testGetInfectedRegions(): Unit =
    assertEquals(List(infectedRegion, totallyInfectedRegion), world.getRegions(using infectedRegions))

  @Test
  def testEmptyGetTotallyInfectedRegions(): Unit =
    world = new World(List(notInfectedRegion, infectedRegion))
    assertEquals(List(), world.getRegions(using totallyInfectedRegions))

  @Test
  def testGetTotallyInfectedRegions(): Unit =
    assertEquals(List(totallyInfectedRegion), world.getRegions(using totallyInfectedRegions))

  @Test
  def testEmptyGetNotInfectedRegions(): Unit =
    world = new World(List(totallyInfectedRegion, infectedRegion))
    assertEquals(List(), world.getRegions(using notInfectedRegions))

  @Test
  def testGetNotInfectedRegions(): Unit =
    assertEquals(List(notInfectedRegion), world.getRegions(using notInfectedRegions))

  @Test
  def testEmptyGetInfectedButNotCompletelyRegions(): Unit =
    world = new World(List(totallyInfectedRegion, notInfectedRegion))
    assertEquals(List(), world.getRegions(using infectedButNotCompletelyRegions))

  @Test
  def testGetInfectedButNotCompletelyRegions(): Unit =
    assertEquals(List(infectedRegion), world.getRegions(using infectedButNotCompletelyRegions))

  @Test
  def testGetNotExistingRegionByName(): Unit =
    assertTrue(world.getRegionByName("Australia").isEmpty)

  @Test
  def testGetExistingRegionByName(): Unit =
    assertTrue(world.getRegionByName("United States").isDefined)
    assertEquals(Some(notInfectedRegion), world.getRegionByName("United States"))
}
