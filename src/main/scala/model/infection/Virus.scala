package model.infection

trait VirusStructure:
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

  case class Virus(name: Name,
                   coldRegionsInfectivity: ColdRegionsInfectivity,
                   warmRegionsInfectivity: WarmRegionsInfectivity,
                   LowDensityRegionInfectivity: LowDensityRegionInfectivity,
                   highDensityRegionsInfectivity: HighDensityRegionsInfectivity,
                   richRegionsInfectivity: RichRegionsInfectivity,
                   poorRegionsInfectivity: PoorRegionsInfectivity,
                   vaccineResistance: VaccineResistance,
                   airportEnabled: AirportEnabled,
                   portEnabled: PortEnabled) extends VirusStructure