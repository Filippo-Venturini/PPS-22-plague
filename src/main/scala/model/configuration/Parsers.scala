package model.configuration

import model.configuration.Builders.{RegionBuilder, VirusBuilder}
import model.world.Region
import model.world.RegionTypes.{BordersControl, Climate, Globalization, Population, Richness, *}
import model.configuration.Parsers.Cast.*
import model.infection.Virus

object Parsers:

  trait Parser[T]:
    def parse(line: String): Option[T]

  object Cast:
    val canBeInt: String => Boolean = _.replace("_","").toIntOption.isDefined
    val canBeBool: String => Boolean = _.toBooleanOption.isDefined
    val castInt: String => Int = _.replace("_","").toInt
    val castBool: String => Boolean = _.toBoolean

  object Region:
    private enum RegionConfigurationFileFormat(val castCondition: String => Boolean, val setter: (RegionBuilder, String) => RegionBuilder):
      case NAME extends RegionConfigurationFileFormat(_ => true, (b, s) => b.setName(s))
      case POPULATION extends RegionConfigurationFileFormat(canBeInt, (b, s) => b.setPopulation(castInt(s)))
      case DENSITY extends RegionConfigurationFileFormat(canBeInt, (b, s) => b.setPopulationDensity(castInt(s)))
      case CLIMATE extends RegionConfigurationFileFormat(canBeInt, (b, s) => b.setClimate(castInt(s)))
      case RICHNESS extends RegionConfigurationFileFormat(canBeInt, (b, s) => b.setRichness(castInt(s)))
      case BORDERS_CONTROL extends RegionConfigurationFileFormat(canBeInt, (b, s) => b.setBordersControl(castInt(s)))
      case GLOBALIZATION extends RegionConfigurationFileFormat(canBeInt, (b, s) => b.setGlobalization(castInt(s)))

    trait RegionParser extends Parser[Region]

    object RegionParser:
      def apply(): RegionParser = new SimpleRegionParser
      private class SimpleRegionParser extends RegionParser:
        def parse(line: String): Option[Region] =
          val params = line.split(",").toList
          RegionConfigurationFileFormat.values
            .foldLeft(RegionBuilder())((builder, field) => field.ordinal < params.size && field.castCondition.apply(params(field.ordinal)) match
              case true => field.setter.apply(builder, params(field.ordinal))
              case false => builder)
            .build()

  object Virus:
    private enum VirusConfigurationFileFormat(val castCondition: String => Boolean, val setter: (VirusBuilder, String) => VirusBuilder):
      case COLD_REGIONS_INF extends VirusConfigurationFileFormat(canBeInt, (b, s) => b.setColdRegionInfectivity(castInt(s)))
      case HOT_REGIONS_INF extends VirusConfigurationFileFormat(canBeInt, (b, s) => b.setWarmRegionInfectivity(castInt(s)))
      case RICH_REGIONS_INF extends VirusConfigurationFileFormat(canBeInt, (b, s) => b.setRichRegionsInfectivity(castInt(s)))
      case POOR_REGIONS_INF extends VirusConfigurationFileFormat(canBeInt, (b, s) => b.setPoorRegionsInfectivity(castInt(s)))
      case LOW_DENSITY_REGIONS_INF extends VirusConfigurationFileFormat(canBeInt, (b, s) => b.setLowDensityRegionsInfectivity(castInt(s)))
      case HIGH_DENSITY_REGIONS_INF extends VirusConfigurationFileFormat(canBeInt, (b, s) => b.setHighDensityRegionsInfectivity(castInt(s)))
      case VACCINE_RESISTANCE extends VirusConfigurationFileFormat(canBeInt, (b, s) => b.setVaccineResistance(castInt(s)))
      case AIRPORT_ENABLED extends VirusConfigurationFileFormat(canBeBool, (b, s) => b.setAirportEnabled(castBool(s)))
      case PORT_ENABLED extends VirusConfigurationFileFormat(canBeBool, (b, s) => b.setPortEnabled(castBool(s)))

    trait VirusParser extends Parser[Virus]

    object VirusParser:
      def apply(): VirusParser = new SimpleVirusParser

      private class SimpleVirusParser extends VirusParser :
        def parse(line: String): Option[Virus] =
          val params = line.split(",").toList
          VirusConfigurationFileFormat.values
            .foldLeft(VirusBuilder())((builder, field) => field.ordinal < params.size && field.castCondition.apply(params(field.ordinal)) match
              case true => field.setter.apply(builder, params(field.ordinal))
              case false => builder)
            .build()