package model.world
import model.world.RegionTypes.*
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestRoutes {
  val firstTestRegionConfiguration: RegionConfiguration = RegionConfiguration("Europe", 746000000, 9, 5, 8, 9, 8)
  val firstTestRegion: Region = new BasicRegion(firstTestRegionConfiguration)
  val secondTestRegionConfiguration: RegionConfiguration = RegionConfiguration("United States", 331000000, 10, 2, 8, 1, 3)
  val secondTestRegion: Region = new BasicRegion(firstTestRegionConfiguration)

  val testRoute = Route(firstTestRegion, secondTestRegion, ReachableMode.Border)
  val portTestRoute = Route(firstTestRegion, secondTestRegion, ReachableMode.Port)
  val airportTestRoute = Route(firstTestRegion, secondTestRegion, ReachableMode.Airport)

  @Test
  def testFirstRegion: Unit =
    assertEquals(firstTestRegion, testRoute.firstRegion)

  @Test
  def testSecondRegion: Unit =
    assertEquals(secondTestRegion, testRoute.secondRegion)

  @Test
  def testPortRoute: Unit =
    assertEquals(ReachableMode.Port, portTestRoute.reachableMode)

  @Test
  def testAirportRoute: Unit =
    assertEquals(ReachableMode.Airport, airportTestRoute.reachableMode)
}
