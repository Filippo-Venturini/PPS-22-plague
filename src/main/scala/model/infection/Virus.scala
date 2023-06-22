package model.infection

object VirusStructure:
  trait Virus:
    def name: String

    def coldRegionsInfectivity: Int = 0

    def warmRegionsInfectivity: Int = 0

    def lowDensityRegionInfectivity: Int = 0

    def highDensityRegionsInfectivity: Int = 0

    def richRegionsInfectivity: Int = 0

    def poorRegionsInfectivity: Int = 0

    def vaccineResistance: Int = 0

    def airportEnabled: Boolean = false

    def portEnabled: Boolean = false
  
  object Virus:
    def apply(name: String): Virus =
      VirusImpl(name)

    private class VirusImpl(override val name: String) extends Virus