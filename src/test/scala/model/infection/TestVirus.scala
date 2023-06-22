package model.infection

import model.infection.VirusStructure.Virus
import model.infection.{InfectionLogic, InternalInfectionLogic}
import model.world.{BasicRegion, Region}
import org.junit.Assert.assertEquals
import org.junit.{Before, Test}

class TestVirus {
  val virusName: String = "DHT11"
  @Test
  def testVirusName: Unit =
    val virus: Virus = Virus(virusName)
    assertEquals(virus.name, virusName)
}