package model.world

import model.world.RegionTypes.RegionConfiguration
import model.world.Filters.given
import org.junit.Test
import org.junit.Assert.assertEquals

class TestWorld {
  val europeConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
  val unitedStatesConfiguration: RegionConfiguration = RegionConfiguration("United States", 746000000, 9, 5, 8, 9, 8)
  var europe: Region = new BasicRegion(europeConfiguration)
  val world: World = new World(List(europe))

  @Test
  def testGetAllRegionsFilter(): Unit =
    assertEquals(List(europe), world.getRegions)
}
