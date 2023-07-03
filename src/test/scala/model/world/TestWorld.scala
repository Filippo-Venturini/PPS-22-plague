package model.world

import model.world.RegionTypes.RegionConfiguration
import model.world.Filters.{infectedRegions, notInfectedRegions, totallyInfectedRegions, given}
import model.world.TestRegionConfigurations.*
import org.junit.{Before, Test}
import org.junit.Assert.assertEquals

class TestWorld {
  val infectedAmount: Int = 100
  var notInfectedRegion: Region = new BasicRegion(unitedStatesConfiguration)
  var infectedRegion: Region = new BasicRegion(europeConfiguration)
  val world: World = new World(List(notInfectedRegion, infectedRegion))

  @Before
  def init(): Unit =
    infectedRegion.infectedAmount = infectedAmount

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
