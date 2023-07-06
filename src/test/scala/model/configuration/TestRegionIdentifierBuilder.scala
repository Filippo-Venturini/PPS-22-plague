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
  def testRegionNameIsUnsetByDefault = {
    assertEquals(None, builder.regionName)
  }

  @Test
  def testIdentifierIsUnsetByDefault = {
    assertEquals(None, builder.identifier)
  }

}

