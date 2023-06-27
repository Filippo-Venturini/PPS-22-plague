package model.configuration

import model.configuration.Parsers.Region.RegionParser
import model.world.Region
import model.world.RegionTypes.RegionConfiguration
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}

object TestReaders {

  class TestRegionParser {
    
    val parser: RegionParser = RegionParser()

    @Test
    def testCorrectRowRead(): Unit =
      val conf = RegionConfiguration("Central-Europe", 60_000_000, 6, 6, 8, 9, 10)
      val region: Region = parser.parse(s"${conf.name},${conf.population},${conf.populationDensity},${conf.climate},${conf.richness},${conf.bordersControl},${conf.globalization}").get
      assertEquals(conf.name, region.name)
      assertEquals(conf.population, region.population)
      assertEquals(conf.populationDensity, region.populationDensity)
      assertEquals(conf.climate, region.climate)
      assertEquals(conf.richness, region.richness)
      assertEquals(conf.bordersControl, region.bordersControl)
      assertEquals(conf.globalization, region.globalization)

    @Test
    def testBadComposedString(): Unit =
      assertEquals(None, parser.parse("Central-Europe,foo,foo,foo,0,foo,0"))

    @Test
    def testUncompletedString(): Unit =
      assertEquals(None, parser.parse("Central-Europe,60000000"))

    @Test
    def testReadIgnoresAdditionalFields(): Unit =
      val conf = RegionConfiguration("Central-Europe", 60_000_000, 6, 6, 8, 9, 10)
      val region: Region = parser.parse(s"${conf.name},${conf.population},${conf.populationDensity},${conf.climate},${conf.richness},${conf.bordersControl},${conf.globalization},a,b,c,d,e").get
      assertEquals(conf.name, region.name)
      assertEquals(conf.population, region.population)
      assertEquals(conf.populationDensity, region.populationDensity)
      assertEquals(conf.climate, region.climate)
      assertEquals(conf.richness, region.richness)
      assertEquals(conf.bordersControl, region.bordersControl)
      assertEquals(conf.globalization, region.globalization)
  }
}