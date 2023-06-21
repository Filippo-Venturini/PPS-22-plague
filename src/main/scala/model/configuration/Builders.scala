package model.configuration

import model.world.Region
import model.world.BasicRegion

object Builders {

  trait RegionBuilder():
    def setName(name: String): RegionBuilder
    def build(): Option[Region]

  class SimpleRegionBuilder() extends RegionBuilder:
    private var name: Option[String] = None

    override def setName(name: String): RegionBuilder = {this.name = Some(name); this}

    override def build(): Option[Region] = name match
      case name if name.isEmpty => None
      case _ => Some(new BasicRegion(name.get, 0, 0, 0, 0, 0, 0, 0))

}
