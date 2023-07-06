package model.configuration

import model.configuration.Builders.{RegionBuilder, RegionIdentifier, RegionIdentifierBuilder}
import model.world.RegionTypes.*
import model.world.{BasicRegion, RegionWithAirport, RegionWithAirportAndPort, RegionWithPort}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}

class TestRegionIdentifierBuilder {

  private val configuration: RegionIdentifier = RegionIdentifier("Central-Europe", "#AE12ED")
  private var builder: RegionIdentifierBuilder = RegionIdentifierBuilder()

  @Before
  def beforeAll(): Unit = {
    builder = RegionIdentifierBuilder()
  }

  @Test
  def testRegionNameIsUnsetByDefault(): Unit = {
    assertEquals(None, builder.regionName)
  }

  @Test
  def testIdentifierIsUnsetByDefault(): Unit = {
    assertEquals(None, builder.identifier)
  }

  @Test
  def testCantBuildWithoutSettingsMandatoryFields(): Unit = {
    assertEquals(None, builder.build())
  }

  @Test
  def testBuild(): Unit = {
    builder = builder.setIdentifier(configuration.identifier)
      .setRegionName(configuration.regionName)
    assertEquals(Some(configuration), builder.build())
  }

}

