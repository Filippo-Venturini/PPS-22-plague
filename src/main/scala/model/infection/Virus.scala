package model.infection
import model.powerUp.PowerUpLogic
import model.world.RegionParameters.*

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

/**
 * Case class that represent the virus configuration. It contains all the virus's properties
 * @param name is the name of the virus, it is chosen by the user
 * @param coldRegionsInfectivity is the level of viruses infection about the regions with a cold climate
 * @param warmRegionsInfectivity is the level of viruses infection about the regions with a warm climate
 * @param lowDensityRegionInfectivity is the level of viruses infection about the regions with low population density
 * @param highDensityRegionsInfectivity is the level of viruses infection about the regions with high population density
 * @param richRegionsInfectivity is the level of viruses infection about the rich regions
 * @param poorRegionsInfectivity is the level of viruses infection about the poor regions
 * @param vaccineResistance is the level of resistance against the vaccine
 * @param airportEnabled establishes whether the virus can spread through airports
 * @param portEnabled establishes whether the virus can spread through ports
 */
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

/**
 * Class that represent the Virus object. It contains its configuration and it can consume power up
 */
abstract class Virus:
  def virusConfiguration: VirusConfiguration

  val name: Name = virusConfiguration.name
  var coldRegionsInfectivity: ColdRegionsInfectivity = virusConfiguration.coldRegionsInfectivity
  var hotRegionsInfectivity: WarmRegionsInfectivity = virusConfiguration.warmRegionsInfectivity
  var lowDensityRegionInfectivity: LowDensityRegionInfectivity = virusConfiguration.lowDensityRegionInfectivity
  var highDensityRegionsInfectivity: HighDensityRegionsInfectivity = virusConfiguration.highDensityRegionsInfectivity
  var richRegionsInfectivity: RichRegionsInfectivity = virusConfiguration.richRegionsInfectivity
  var poorRegionsInfectivity: PoorRegionsInfectivity = virusConfiguration.poorRegionsInfectivity
  var vaccineResistance: VaccineResistance = virusConfiguration.vaccineResistance
  var airportEnabled: AirportEnabled = virusConfiguration.airportEnabled
  var portEnabled: PortEnabled = virusConfiguration.portEnabled

  /**
   * it is used for add a specific Power Up to the virus for upgrade its properties
   * @param logic contains how a specific Power Up changes ownership of viruses
   */
  def consumePowerUp(logic: PowerUpLogic): Unit =
    logic.applyTo(this)

  def getActualConfiguration: VirusConfiguration =
    VirusConfiguration(this.name, this.coldRegionsInfectivity, this.hotRegionsInfectivity, this.lowDensityRegionInfectivity,
      this.highDensityRegionsInfectivity, this.richRegionsInfectivity, this.poorRegionsInfectivity, this.vaccineResistance, this.airportEnabled, this.portEnabled)

/**
 * Class that represent basic virus in case of next itself implementations
 * @param virusConfiguration contains the virus configurations, set all the value virus property
 */
class BasicVirus(override val virusConfiguration: VirusConfiguration) extends Virus