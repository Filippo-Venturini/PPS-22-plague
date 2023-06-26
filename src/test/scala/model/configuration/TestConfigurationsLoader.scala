package model.configuration

import org.junit.Test
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.{Before, Test}
import model.configuration.File

class TestConfigurationsLoader:

  @Test
  def testNonExistingFileReading(): Unit = {
    assertTrue(File.readLines("foo").isEmpty)
  }