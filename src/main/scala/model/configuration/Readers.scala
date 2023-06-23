package model.configuration

import model.configuration.Builders.RegionBuilder
import model.world.Region
import model.world.RegionTypes.{BordersControl, Climate, Globalization, Population, Richness, *}

object Readers:

  trait Reader

  object Region:
    private enum RegionConfigurationFileFormat(val castCondition: String => Boolean, val setter: (RegionBuilder, String) => RegionBuilder):
      case NAME extends RegionConfigurationFileFormat(_ => true, (b, s) => b.setName(s))
      case POPULATION extends RegionConfigurationFileFormat(_.toIntOption.isDefined, (b, s) => b.setPopulation(s.toInt))
      case DENSITY extends RegionConfigurationFileFormat(_.toIntOption.isDefined, (b, s) => b.setPopulationDensity(s.toInt))
      case CLIMATE extends RegionConfigurationFileFormat(_.toIntOption.isDefined, (b, s) => b.setClimate(s.toInt))
      case RICHNESS extends RegionConfigurationFileFormat(_.toIntOption.isDefined, (b, s) => b.setRichness(s.toInt))
      case BORDERS_CONTROL extends RegionConfigurationFileFormat(_.toIntOption.isDefined, (b, s) => b.setBordersControl(s.toInt))
      case GLOBALIZATION extends RegionConfigurationFileFormat(_.toIntOption.isDefined, (b, s) => b.setGlobalization(s.toInt))

    trait RegionReader extends Reader :
      def read(line: String): Option[Region]

    object RegionReader:
      def apply(): RegionReader = new SimpleRegionReader
      private class SimpleRegionReader extends RegionReader:
        def read(line: String): Option[Region] =
          val params = line.split(",").toList
          RegionConfigurationFileFormat.values
            .foldLeft(RegionBuilder())((builder, field) => field.ordinal < params.size && field.castCondition.apply(params(field.ordinal)) match
              case true => field.setter.apply(builder, params(field.ordinal))
              case false => builder)
            .build()






