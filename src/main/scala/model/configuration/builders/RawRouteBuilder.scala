package model.configuration.builders

import model.configuration.builders.ConfigurationBuilder
import model.configuration.builders.RawRouteBuilder.RawRoute
import model.world.RegionParameters.ReachableMode

/**
 * allows to easily create a RawRoute through the builder pattern
 * @param nameRegion1 the name of the first region
 * @param nameRegion2 the name of the second region
 * @param reachableMode the reachable mode of the route
 */
case class RawRouteBuilder private (nameRegion1: Option[String], nameRegion2: Option[String], reachableMode: Option[ReachableMode]) extends ConfigurationBuilder[RawRoute]:
  private def copy(nameRegion1: Option[String] = nameRegion1,
                   nameRegion2: Option[String] = nameRegion2,
                   reachableMode: Option[ReachableMode] = reachableMode): RawRouteBuilder =
    RawRouteBuilder(nameRegion1, nameRegion2, reachableMode)

  /**
   * set the name of the first region
   * @param name the name of the first region
   * @return a new RawRouteBuilder with the nameRegion1 field set
   */
  def setNameRegion1(name: String): RawRouteBuilder = copy(nameRegion1 = Some(name))

  /**
   * set the name of the second region
   *
   * @param name the name of the second region
   * @return a new RawRouteBuilder with the nameRegion2 field set
   */
  def setNameRegion2(name: String): RawRouteBuilder = copy(nameRegion2 = Some(name))

  /**
   * set the reachability mode of the route
   * @param reachableMode the reachability mode of the route
   * @return a new RawRouteBuilder with the reachableMode field set
   */
  def setReachableMode(reachableMode: ReachableMode): RawRouteBuilder = copy(reachableMode = Some(reachableMode))

  /**
   * create, if possible a new RawRoute instance
   * @return a new RawRout if all the mandatory fields have been set, otherwise None
   */
  override def build(): Option[RawRoute] = this match
    case RawRouteBuilder(Some(_), Some(_), Some(_)) => Some(RawRoute(nameRegion1.get, nameRegion2.get, reachableMode.get))
    case _ => None

/**
 * allows to easily create a RawRoute through the builder pattern
 */
object RawRouteBuilder:
  /**
   * @return a new RawRouteBuilder with all the fields unset
   */
  def apply() = new RawRouteBuilder(None, None, None)

  /**
   * represents a route. Differently from a Route, a RawRoute does not contain references to the connected regions but
   * simply indicates their names
   * @param nameRegion1 the name of the first connected region
   * @param nameRegion2 the name of the second connected region
   * @param reachableMode the way on which the two regions are connected
   */
  case class RawRoute(nameRegion1: String, nameRegion2: String, reachableMode: ReachableMode)

