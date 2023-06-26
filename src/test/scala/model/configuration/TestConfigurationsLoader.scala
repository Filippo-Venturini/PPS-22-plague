package model.configuration

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}
import model.configuration.File
import model.configuration.Loader.{ConfigurationsLoader, RegionFile}
import model.configuration.Parsers.Region.RegionParser

class TestConfigurationsLoader:

  @Test
  def testNonExistingFileReading(): Unit = {
    assertTrue(File.readLinesFromResources("foo").isEmpty)
  }
  @Test
  def testExistingFileReading(): Unit = {
    assertFalse(File.readLinesFromResources("configs/regions.txt").isEmpty)
  }

  @Test
  def testLoadRegions(): Unit = {
    val regionsFile: RegionFile = RegionFile("configs/regions.txt")
    assertFalse(ConfigurationsLoader.load(regionsFile)(using RegionParser()).isEmpty)
  }