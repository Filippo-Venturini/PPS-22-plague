package model.infection
import model.world.RegionTypes.*

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
  var coldRegionsInfectivity: ColdRegionsInfectivity = virusConfiguration.coldRegionsInfectivity
  var warmRegionsInfectivity: WarmRegionsInfectivity = virusConfiguration.warmRegionsInfectivity
  var lowDensityRegionInfectivity: LowDensityRegionInfectivity = virusConfiguration.lowDensityRegionInfectivity
  var highDensityRegionsInfectivity: HighDensityRegionsInfectivity = virusConfiguration.highDensityRegionsInfectivity
  var richRegionsInfectivity: RichRegionsInfectivity = virusConfiguration.richRegionsInfectivity
  var poorRegionsInfectivity: PoorRegionsInfectivity = virusConfiguration.poorRegionsInfectivity
  var vaccineResistance: VaccineResistance = virusConfiguration.vaccineResistance
  var airportEnabled: AirportEnabled = virusConfiguration.airportEnabled
  var portEnabled: PortEnabled = virusConfiguration.portEnabled

class BasicVirus(override val virusConfiguration: VirusConfiguration) extends Virus