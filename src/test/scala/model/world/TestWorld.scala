package model.world

import model.world.RegionTypes.RegionConfiguration
import model.world.Filters.{infectedRegions, notInfectedRegions, totallyInfectedRegions, given}
import model.world.TestRegionConfigurations.*
import org.junit.{Before, Test}
import org.junit.Assert.{assertEquals, assertTrue}

class TestWorld {
  val infectedAmount: Int = 100
  var notInfectedRegion: Region = new BasicRegion(unitedStatesConfiguration)
  var infectedRegion: Region = new BasicRegion(europeConfiguration)
  var totallyInfectedRegion: Region = new BasicRegion(japanConfiguration)
  val world: World = new World(List(notInfectedRegion, infectedRegion, totallyInfectedRegion))

  @Before
  def init(): Unit =
    infectedRegion.infectedAmount = infectedAmount
    totallyInfectedRegion.infectedAmount = totallyInfectedRegion.population

  @Test
  def testGetAllRegions(): Unit =
    assertEquals(List(notInfectedRegion, infectedRegion, totallyInfectedRegion), world.getRegions)

  @Test
  def testGetInfectedRegions(): Unit =
    assertEquals(List(infectedRegion, totallyInfectedRegion), world.getRegions(using infectedRegions))

  @Test
  def testGetTotallyInfectedRegions(): Unit =
    assertEquals(List(totallyInfectedRegion), world.getRegions(using totallyInfectedRegions))

  @Test
  def testGetNotInfectedRegions(): Unit =
    assertEquals(List(notInfectedRegion), world.getRegions(using notInfectedRegions))

  @Test
  def testGetNotExistingRegionByName(): Unit =
    assertTrue(world.getRegion("Australia").isEmpty)

  @Test
  def testGetExistingRegionByName(): Unit =
    assertTrue(world.getRegion("United States").isDefined)
    assertEquals(Some(notInfectedRegion), world.getRegion("United States"))
}
