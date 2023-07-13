package model.powerUp

import model.powerUp.PowerUpType.*
import model.powerUp.PowerUpLogics.*
import model.powerUp.PowerUpsInformation.*

/**
 * Represent the Power Up type. For each possible Power Up is determinate the price and a list of mandatory prerequisite
 * for to get it.
 * The list might contain other PowerUpTypes, or it might be empty if it has no prerequisites
 */
enum PowerUpType(val price: Int, val prerequisite: List[PowerUpType], val logic: PowerUpLogic, val information: PowerUpInformation):
  case ColdResistanceI extends PowerUpType(2, List(), coldResistanceI, coldResistanceIInformation)
  case ColdResistanceII extends PowerUpType(2, List(ColdResistanceI), coldResistanceII, coldResistanceIIInformation)
  case HotResistanceI extends PowerUpType(2, List(), hotResistanceI, hotResistanceIInformation)
  case HotResistanceII extends PowerUpType(2, List(HotResistanceI), hotResistanceII, hotResistanceIIInformation)
  case BacterialResistance extends PowerUpType(2, List(ColdResistanceII, HotResistanceII), bacterialResistance, bacterialResistanceInformation)
  case AirportEnablement extends PowerUpType(2, List(), airportEnablement, airportEnablementInformation)
  case PortEnablement extends PowerUpType(2, List(), portEnablement, portEnablementInformation)
  case InfectedDrinkingWater extends PowerUpType(2, List(), infectedDrinkingWater, infectedDrinkingWaterInformation)
  case InfectionThroughAnimals extends PowerUpType(2, List(InfectedDrinkingWater), infectionThroughAnimals, infectionThroughAnimalsInformation)
  case MedicinesResistance extends PowerUpType(2, List(), medicinesResistance, medicinesResistanceInformation)
  case InfectionThroughRespiratoryTract extends PowerUpType(2, List(MedicinesResistance), infectionThroughRespiratoryTract, infectionThroughRespiratoryTractInformation)
  case AlphaMutations extends PowerUpType(2, List(), alphaMutations, alphaMutationsInformation)
  case BetaMutations extends PowerUpType(2, List(AlphaMutations), betaMutations, betaMutationsInformation)
  case GammaMutations extends PowerUpType(2, List(BetaMutations), gammaMutations, gammaMutationsInformation)
  case OmegaMutations extends PowerUpType(2, List(GammaMutations), omegaMutations, omegaMutationsInformation)
