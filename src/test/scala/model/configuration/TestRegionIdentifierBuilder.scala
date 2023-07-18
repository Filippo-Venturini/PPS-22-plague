package model.configuration

import model.configuration.Builders.{RegionBuilder, RegionIdentifier, RegionIdentifierBuilder}
import model.world.RegionParameters.*
import model.world.{BasicRegion, RegionWithAirport, RegionWithAirportAndPort, RegionWithPort}
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}

class TestRegionIdentifierBuilder {

  private val configuration: RegionIdentifier = RegionIdentifier("Central-Europe", "#AE12EF")
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
  def testRegionNameSet(): Unit = {
    assertEquals(Some(configuration.regionName), builder.setRegionName(configuration.regionName).regionName)
  }

  @Test
  def testIdentifierSet(): Unit = {
    assertEquals(Some(configuration.identifier), builder.setIdentifier(configuration.identifier).identifier)
  }

  @Test
  def testMalformedIdentifiersNotStartingWithHash(): Unit = {
    assertEquals(None, builder.setIdentifier("ABCDEF").identifier)
  }

  @Test
  def testMalformedIdentifiersTooLong(): Unit = {
    assertEquals(None, builder.setIdentifier("#1234567").identifier)
  }

  @Test
  def testMalformedIdentifiersTooShort(): Unit = {
    assertEquals(None, builder.setIdentifier("#12345").identifier)
  }

  @Test
  def testMalformedIdentifiersNotValidChar(): Unit = {
    assertEquals(None, builder.setIdentifier("#0000Z0").identifier)
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

