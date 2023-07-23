package model.powerUp

import model.powerUp.PowerUpType.*
import model.powerUp.PowerUpLogics.*
import model.powerUp.PowerUpsInformation.*

/**
 * Represent the Power Up type. For each possible Power Up is determinate the price, a list of mandatory prerequisite for to get it,
 * its logic and its informations.
 * The list of mandatory prerequisite contain other PowerUpTypes, or it might be empty if it has no prerequisites
 */
enum PowerUpType(val price: Int, val prerequisite: List[PowerUpType], val logic: PowerUpLogic, val information: PowerUpInformation):
  case ColdResistanceI extends PowerUpType(5, List(), coldResistanceI, coldResistanceIInformation)
  case ColdResistanceII extends PowerUpType(5, List(ColdResistanceI), coldResistanceII, coldResistanceIIInformation)
  case HotResistanceI extends PowerUpType(5, List(), hotResistanceI, hotResistanceIInformation)
  case HotResistanceII extends PowerUpType(5, List(HotResistanceI), hotResistanceII, hotResistanceIIInformation)
  case BacterialResistance extends PowerUpType(5, List(ColdResistanceII, HotResistanceII), bacterialResistance, bacterialResistanceInformation)
  case AirportEnablement extends PowerUpType(3, List(), airportEnablement, airportEnablementInformation)
  case PortEnablement extends PowerUpType(3, List(), portEnablement, portEnablementInformation)
  case InfectedDrinkingWater extends PowerUpType(5, List(), infectedDrinkingWater, infectedDrinkingWaterInformation)
  case InfectionThroughAnimals extends PowerUpType(5, List(InfectedDrinkingWater), infectionThroughAnimals, infectionThroughAnimalsInformation)
  case MedicinesResistance extends PowerUpType(5, List(), medicinesResistance, medicinesResistanceInformation)
  case InfectionThroughRespiratoryTract extends PowerUpType(5, List(MedicinesResistance), infectionThroughRespiratoryTract, infectionThroughRespiratoryTractInformation)
  case AlphaMutations extends PowerUpType(1, List(), alphaMutations, alphaMutationsInformation)
  case BetaMutations extends PowerUpType(2, List(AlphaMutations), betaMutations, betaMutationsInformation)
  case GammaMutations extends PowerUpType(4, List(BetaMutations), gammaMutations, gammaMutationsInformation)
  case OmegaMutations extends PowerUpType(6, List(GammaMutations), omegaMutations, omegaMutationsInformation)
