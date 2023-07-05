package model.configuration

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}
import model.configuration.Loader.{ConfigurationsLoader, File, RegionFile, RouteFile, VirusFile}
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

  @Test
  def testLoadRoutes(): Unit = {
    val routeFile: RouteFile = RouteFile("configs/routes.txt")
    assertFalse(ConfigurationsLoader.load(routeFile).isEmpty)
  }

  @Test
  def testDefaultLoadVirus(): Unit = {
    assertTrue(ConfigurationsLoader.loadVirus().isDefined)
  }