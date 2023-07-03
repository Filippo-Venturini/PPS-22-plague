package model.world

import model.world.RegionTypes.RegionConfiguration
import model.world.Filters.{infectedRegions, notInfectedRegions, totallyInfectedRegions, given}
import model.world.TestRegionConfigurations.*
import org.junit.{Before, Test}
import org.junit.Assert.assertEquals

class TestWorld {
  var notInfectedRegion: Region = new BasicRegion(europeConfiguration)
  var infectedRegion: Region = new BasicRegion(europeConfiguration)
  val world: World = new World(List(notInfectedRegion, infectedRegion))

  @Before
  def init(): Unit =
    infectedRegion.infectedAmount = 100

  @Test
  def testGetAllRegions(): Unit =
    assertEquals(List(notInfectedRegion, infectedRegion), world.getRegions)

  @Test
  def testGetInfectedRegions(): Unit =
    assertEquals(List(infectedRegion), world.getRegions(using infectedRegions))

  @Test
  def testGetTotallyInfectedRegions(): Unit =
    infectedRegion.infectedAmount = infectedRegion.population
    assertEquals(List(infectedRegion), world.getRegions(using totallyInfectedRegions))

  @Test
  def testGetNotInfectedRegions(): Unit =
    assertEquals(List(notInfectedRegion), world.getRegions(using notInfectedRegions))
}
