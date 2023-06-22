package model.configuration

import model.configuration.Builders.{RegionBuilder}
import model.world.RegionConfiguration
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class RegionBuilderTest {

  private val regionConfiguration = RegionConfiguration("Europe", 60_000_000, 0, 0 ,0, 0, 0)
  private var regionBuilder: RegionBuilder = RegionBuilder()

  @Before
  def beforeAll(): Unit ={
    regionBuilder = RegionBuilder()
  }

  @Test
  def testNameIsUnsetByDefault(): Unit = {
    assertEquals(None, regionBuilder.name)
  }

  @Test
  def testSetName(): Unit = {
    assertEquals(Some(regionConfiguration.name), regionBuilder.setName(regionConfiguration.name).name)
  }



}

