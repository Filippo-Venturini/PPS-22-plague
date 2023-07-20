package model.configuration.builders

import model.infection.{BasicVirus, Virus, VirusConfiguration}
import model.world.RegionParameters.*
import model.world.*

/**
 * defines a builder structure
 * @tparam T
 */
trait ConfigurationBuilder[T]:
  /**
   * @return optionally a new object of type T.
   */
  def build(): Option[T]