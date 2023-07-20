package model.configuration.builders

import model.configuration.builders.RegionIdentifierBuilder.RegionIdentifier
import model.world.RegionParameters.Name

/**
 * allows to easily create a RegionIdentifier using the builder pattern
 * @param regionName the name of the region
 * @param identifier the identifier
 */
case class RegionIdentifierBuilder(regionName: Option[String], identifier: Option[String]) extends ConfigurationBuilder[RegionIdentifier]:
  private def isIdValid(str: String): Boolean = str.toUpperCase.matches("#[0-9A-F]{6}")

  /**
   * set the region name to the given value
   * @param name the region name
   * @return a new RegionIdentifierBuilder with the name field set
   */
  def setRegionName(name: Name): RegionIdentifierBuilder = RegionIdentifierBuilder(Some(name), identifier)

  /**
   * set the identifier to the given value
   *
   * @param identifier the identifier
   * @return a new RegionIdentifierBuilder with the identifier field set
   */
  def setIdentifier(identifier: String): RegionIdentifierBuilder = if isIdValid(identifier) then RegionIdentifierBuilder(regionName, Some(identifier.toUpperCase)) else this

  /**
   * create, if possible, a new RegionIdentifier using the given values
   * @return a new RegionIdentifier if all the fields have been set, None other else
   */
  override def build(): Option[RegionIdentifier] = this match
    case RegionIdentifierBuilder(Some(name), Some(identifier)) => Some(RegionIdentifier(name, identifier))
    case _ => None

/**
 * allows to easily create a RegionIdentifier using the builder pattern
 */
object RegionIdentifierBuilder:
  /**
   * @return a new RegionIdentifierBuilder with all fields unset (None)
   */
  def apply() = new RegionIdentifierBuilder(None, None)

  /**
   * associate a regionName to an identifier
   * @param regionName the name of the region
   * @param identifier the identifier
   */
  case class RegionIdentifier(regionName: String, identifier: String)