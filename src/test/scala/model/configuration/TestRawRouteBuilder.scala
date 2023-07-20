package model.configuration

import model.configuration.builders.RawRouteBuilder
import model.configuration.builders.RawRouteBuilder.RawRoute
import model.configuration.builders.RegionIdentifierBuilder.*
import model.world.RegionParameters.ReachableMode
import org.junit.Assert.*
import org.junit.*

class TestRawRouteBuilder {

  private var rawRouteBuilder: RawRouteBuilder = RawRouteBuilder()
  val rawRoute: RawRoute = RawRoute("Europe", "Balkans", ReachableMode.Border)

  @Before
  def before(): Unit = {
    rawRouteBuilder = RawRouteBuilder()
  }

  @Test
  def testNameRegion1IsUnsetByDefault(): Unit = {
    assertEquals(None, rawRouteBuilder.nameRegion1)
  }

  @Test
  def testNameRegion2IsUnsetByDefault(): Unit = {
    assertEquals(None, rawRouteBuilder.nameRegion2)
  }

  @Test
  def testReachableModeIsUnsetByDefault(): Unit = {
    assertEquals(None, rawRouteBuilder.reachableMode)
  }

  @Test
  def testSetNameRegion1(): Unit = {
    assertEquals(Some(rawRoute.nameRegion1), rawRouteBuilder.setNameRegion1(rawRoute.nameRegion1).nameRegion1)
  }

  @Test
  def testSetNameRegion2(): Unit = {
    assertEquals(Some(rawRoute.nameRegion2), rawRouteBuilder.setNameRegion2(rawRoute.nameRegion2).nameRegion2)
  }

  @Test
  def testSetReachableMode(): Unit = {
    assertEquals(Some(rawRoute.reachableMode), rawRouteBuilder.setReachableMode(rawRoute.reachableMode).reachableMode)
  }

  @Test
  def testCannotBuildWithoutSettingMandatoryFields(): Unit = {
    assertEquals(None, rawRouteBuilder.build())
  }

  @Test
  def testBuild(): Unit = {
    rawRouteBuilder = rawRouteBuilder
      .setReachableMode(rawRoute.reachableMode)
      .setNameRegion2(rawRoute.nameRegion2)
      .setNameRegion1(rawRoute.nameRegion1)
    assertEquals(Some(rawRoute), rawRouteBuilder.build())
  }

}
