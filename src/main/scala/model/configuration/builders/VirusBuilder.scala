package model.configuration.builders

import model.configuration.builders.ConfigurationBuilder
import model.infection.*


/**
 * an object that allows to easily create a Virus using the builder pattern
 * @param coldRegionsInfectivity the coldRegionsInfectivity of the virus as optional
 * @param warmRegionsInfectivity the warmRegionsInfectivity of the virus as optional
 * @param lowDensityRegionsInfectivity the lowDensityRegionsInfectivity of the virus as optional
 * @param highDensityRegionsInfectivity the highDensityRegionsInfectivity of the virus as optional
 * @param richRegionsInfectivity the richRegionsInfectivity of the virus as optional
 * @param poorRegionsInfectivity the poorRegionsInfectivity of the virus as optional
 * @param vaccineResistance the vaccineResistance of the virus as optional
 * @param airportEnabled the airportEnabled of the virus as optional
 * @param portEnabled the portEnabled of the virus as optional
 */
case class VirusBuilder private (coldRegionsInfectivity: Option[ColdRegionsInfectivity],
                        warmRegionsInfectivity: Option[WarmRegionsInfectivity],
                        lowDensityRegionsInfectivity: Option[LowDensityRegionInfectivity],
                        highDensityRegionsInfectivity: Option[HighDensityRegionsInfectivity],
                        richRegionsInfectivity: Option[RichRegionsInfectivity],
                        poorRegionsInfectivity: Option[PoorRegionsInfectivity],
                        vaccineResistance: Option[VaccineResistance],
                        airportEnabled: Option[AirportEnabled],
                        portEnabled: Option[PortEnabled]) extends ConfigurationBuilder[Virus]:
  private def copy(coldRegionsInfectivity: Option[ColdRegionsInfectivity] = coldRegionsInfectivity,
                   warmRegionsInfectivity: Option[WarmRegionsInfectivity] = warmRegionsInfectivity,
                   lowDensityRegionsInfectivity: Option[LowDensityRegionInfectivity],
                   highDensityRegionsInfectivity: Option[HighDensityRegionsInfectivity],
                   richRegionsInfectivity: Option[RichRegionsInfectivity],
                   poorRegionsInfectivity: Option[PoorRegionsInfectivity],
                   vaccineResistance: Option[VaccineResistance],
                   airportEnabled: Option[AirportEnabled],
                   portEnabled: Option[PortEnabled]): VirusBuilder =
    new VirusBuilder(coldRegionsInfectivity, warmRegionsInfectivity, lowDensityRegionsInfectivity, highDensityRegionsInfectivity,
      richRegionsInfectivity, poorRegionsInfectivity, vaccineResistance, airportEnabled, portEnabled)

  /**
   * @param coldRegionsInfectivity the new coldRegionsInfectivity value
   * @return a new VirusBuilder with the coldRegionsInfectivity field set
   */
  def setColdRegionInfectivity(coldRegionsInfectivity: ColdRegionsInfectivity): VirusBuilder =
    copy(coldRegionsInfectivity = Some(coldRegionsInfectivity))

  /**
   * @param warmRegionsInfectivity the new warmRegionsInfectivity value
   * @return a new VirusBuilder with the warmRegionsInfectivity field set
   */
  def setWarmRegionInfectivity(warmRegionsInfectivity: WarmRegionsInfectivity): VirusBuilder =
    copy(warmRegionsInfectivity = Some(warmRegionsInfectivity))

  /**
   * @param lowDensityRegionsInfectivity the new lowDensityRegionsInfectivity value
   * @return a new VirusBuilder with the lowDensityRegionsInfectivity field set
   */
  def setLowDensityRegionsInfectivity(lowDensityRegionsInfectivity: LowDensityRegionInfectivity): VirusBuilder =
    copy(lowDensityRegionsInfectivity = Some(lowDensityRegionsInfectivity))

  /**
   * @param highDensityRegionsInfectivity the new highDensityRegionsInfectivity value
   * @return a new VirusBuilder with the highDensityRegionsInfectivity field set
   */
  def setHighDensityRegionsInfectivity(highDensityRegionsInfectivity: HighDensityRegionsInfectivity): VirusBuilder =
    copy(highDensityRegionsInfectivity = Some(highDensityRegionsInfectivity))

  /**
   * @param richRegionsInfectivity the new richRegionsInfectivity value
   * @return a new VirusBuilder with the richRegionsInfectivity field set
   */
  def setRichRegionsInfectivity(richRegionsInfectivity: RichRegionsInfectivity): VirusBuilder =
    copy(richRegionsInfectivity = Some(richRegionsInfectivity))

  /**
   * @param poorRegionsInfectivity the new poorRegionsInfectivity value
   * @return a new VirusBuilder with the poorRegionsInfectivity field set
   */
  def setPoorRegionsInfectivity(poorRegionsInfectivity: PoorRegionsInfectivity): VirusBuilder =
    copy(poorRegionsInfectivity = Some(poorRegionsInfectivity))

  /**
   * @param vaccineResistance the new vaccineResistance value
   * @return a new VirusBuilder with the vaccineResistance field set
   */
  def setVaccineResistance(vaccineResistance: VaccineResistance): VirusBuilder =
    copy(vaccineResistance = Some(vaccineResistance))

  /**
   * @param airportEnabled the new airportEnabled value
   * @return a new VirusBuilder with the airportEnabled field set
   */
  def setAirportEnabled(airportEnabled: AirportEnabled): VirusBuilder =
    copy(airportEnabled = Some(airportEnabled))

  /**
   * @param portEnabled the new portEnabled value
   * @return a new VirusBuilder with the portEnabled field set
   */
  def setPortEnabled(portEnabled: PortEnabled): VirusBuilder =
    copy(portEnabled = Some(portEnabled))

  /**
   * build, if possible a new Virus using the fields that have been previously set
   * @return a new Virus as Option if all the mandatory fields have been previously set, None otherwise
   */
  override def build(): Option[Virus] = this match
    case VirusBuilder(Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_), Some(_)) =>
      Some(new BasicVirus(VirusConfiguration(
        "",
        coldRegionsInfectivity.get,
        warmRegionsInfectivity.get,
        lowDensityRegionsInfectivity.get,
        highDensityRegionsInfectivity.get,
        richRegionsInfectivity.get,
        poorRegionsInfectivity.get,
        vaccineResistance.get,
        airportEnabled.get,
        portEnabled.get
      )))
    case _ => None

/**
 * an object that allows to easily create a Virus using the builder pattern
 */
object VirusBuilder:
  /**
   * @return a new VirusBuilder with all the parameters unset by default
   */
  def apply() = new VirusBuilder(None, None, None, None, None, None, None, None, None)