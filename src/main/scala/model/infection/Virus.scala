package model.infection
import model.world.RegionConfiguration

type Name = String
type ColdRegionsInfectivity = Int
type WarmRegionsInfectivity = Int
type LowDensityRegionInfectivity = Int
type HighDensityRegionsInfectivity = Int
type RichRegionsInfectivity = Int
type PoorRegionsInfectivity = Int
type VaccineResistance = Int
type AirportEnabled = Boolean
type PortEnabled = Boolean

case class VirusConfiguration(name: Name,
                              coldRegionsInfectivity: ColdRegionsInfectivity,
                              warmRegionsInfectivity: WarmRegionsInfectivity,
                              lowDensityRegionInfectivity: LowDensityRegionInfectivity,
                              highDensityRegionsInfectivity: HighDensityRegionsInfectivity,
                              richRegionsInfectivity: RichRegionsInfectivity,
                              poorRegionsInfectivity: PoorRegionsInfectivity,
                              vaccineResistance: VaccineResistance,
                              airportEnabled: AirportEnabled,
                              portEnabled: PortEnabled)

abstract class Virus:
  def virusConfiguration: VirusConfiguration

  val name: Name = virusConfiguration.name
  val coldRegionsInfectivity: ColdRegionsInfectivity = virusConfiguration.coldRegionsInfectivity
  val warmRegionsInfectivity: WarmRegionsInfectivity = virusConfiguration.warmRegionsInfectivity
  val lowDensityRegionInfectivity: LowDensityRegionInfectivity = virusConfiguration.lowDensityRegionInfectivity
  val highDensityRegionsInfectivity: HighDensityRegionsInfectivity = virusConfiguration.highDensityRegionsInfectivity
  val richRegionsInfectivity: RichRegionsInfectivity = virusConfiguration.richRegionsInfectivity
  val poorRegionsInfectivity: PoorRegionsInfectivity = virusConfiguration.poorRegionsInfectivity
  val vaccineResistance: VaccineResistance = virusConfiguration.vaccineResistance
  val airportEnabled: AirportEnabled = virusConfiguration.airportEnabled
  val portEnabled: PortEnabled = virusConfiguration.portEnabled

class BasicVirus(override val virusConfiguration: VirusConfiguration) extends Virus