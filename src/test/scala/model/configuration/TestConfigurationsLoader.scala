package model.configuration

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}
import model.configuration.Loader.{ConfigurationsLoader, File, RegionFile, VirusFile}
import model.configuration.Parsers.Region.RegionParser
import model.configuration.Loader.ConfigurationsLoader.given

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
    assertFalse(ConfigurationsLoader.load(regionsFile).isEmpty)
  }

  @Test
  def testLoadVirus(): Unit = {
    val virusFile: VirusFile = VirusFile("configs/virus.txt")
    assertFalse(ConfigurationsLoader.load(virusFile).isEmpty)
  }