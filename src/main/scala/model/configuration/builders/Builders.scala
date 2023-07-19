package model.configuration.builders

import model.infection.{BasicVirus, Virus, VirusConfiguration}
import model.world.RegionParameters.*
import model.world.*

trait ConfigurationBuilder[T]:
  def build(): Option[T]

object Builders:

  

  case class RawRoute(nameRegion1: String, nameRegion2: String, reachableMode: ReachableMode)

  case class RawRouteBuilder(nameRegion1: Option[String], nameRegion2: Option[String], reachableMode: Option[ReachableMode]):
    private def copy(nameRegion1: Option[String] = nameRegion1,
                     nameRegion2: Option[String] = nameRegion2,
                     reachableMode: Option[ReachableMode] = reachableMode): RawRouteBuilder =
      RawRouteBuilder(nameRegion1, nameRegion2, reachableMode)

    def setNameRegion1(name: String): RawRouteBuilder = copy(nameRegion1 = Some(name))

    def setNameRegion2(name: String): RawRouteBuilder = copy(nameRegion2 = Some(name))

    def setReachableMode(reachableMode: ReachableMode): RawRouteBuilder = copy(reachableMode = Some(reachableMode))

    def build(): Option[RawRoute] = this match
      case RawRouteBuilder(Some(_), Some(_), Some(_)) => Some(RawRoute(nameRegion1.get, nameRegion2.get, reachableMode.get))
      case _ => None

  object RawRouteBuilder:
    def apply() = new RawRouteBuilder(None, None, None)

  case class RegionIdentifier(regionName: String, identifier: String)

  case class RegionIdentifierBuilder(regionName: Option[String], identifier: Option[String]):
    private def isIdValid(str: String): Boolean = str.toUpperCase.matches("#[0-9A-F]{6}")

    def setRegionName(name: Name): RegionIdentifierBuilder = RegionIdentifierBuilder(Some(name), identifier)

    def setIdentifier(identifier: String): RegionIdentifierBuilder = if isIdValid(identifier) then RegionIdentifierBuilder(regionName, Some(identifier.toUpperCase)) else this

    def build(): Option[RegionIdentifier] = this match
      case RegionIdentifierBuilder(Some(name), Some(identifier)) => Some(RegionIdentifier(name, identifier))
      case _ => None

  object RegionIdentifierBuilder:
    def apply() = new RegionIdentifierBuilder(None, None)
