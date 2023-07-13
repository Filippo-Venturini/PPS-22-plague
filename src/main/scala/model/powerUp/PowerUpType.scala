package model.powerUp

import model.powerUp.PowerUpType.*
import model.powerUp.PowerUpLogics.*
import model.powerUp.PowerUpsInformation.coldResistanceIInformation

/**
 * Represent the Power Up type. For each possible Power Up is determinate the price and a list of mandatory prerequisite
 * for to get it.
 * The list might contain other PowerUpTypes, or it might be empty if it has no prerequisites
 */
enum PowerUpType(val price: Int, val prerequisite: List[PowerUpType], val logic: PowerUpLogic, val information: PowerUpInformation):
  case ColdResistanceI extends PowerUpType(2, List(), coldResistanceI, coldResistanceIInformation)
  case ColdResistanceII extends PowerUpType(2, List(ColdResistanceI), coldResistanceII, coldResistanceIInformation)
  case HotResistanceI extends PowerUpType(2, List(), hotResistanceI, coldResistanceIInformation)
  case HotResistanceII extends PowerUpType(2, List(HotResistanceI), hotResistanceII, coldResistanceIInformation)
  case BacterialResistance extends PowerUpType(2, List(ColdResistanceII, HotResistanceII), bacterialResistance, coldResistanceIInformation)
  case AirportEnablement extends PowerUpType(2, List(), airportEnablement, coldResistanceIInformation)
  case PortEnablement extends PowerUpType(2, List(), portEnablement, coldResistanceIInformation)
  case InfectedDrinkingWater extends PowerUpType(2, List(), infectedDrinkingWater, coldResistanceIInformation)
  case InfectionThroughAnimals extends PowerUpType(2, List(InfectedDrinkingWater), infectionThroughAnimals, coldResistanceIInformation)
  case MedicinesResistance extends PowerUpType(2, List(), medicinesResistance, coldResistanceIInformation)
  case InfectionThroughRespiratoryTract extends PowerUpType(2, List(MedicinesResistance), infectionThroughRespiratoryTract, coldResistanceIInformation)
  case AlphaMutations extends PowerUpType(2, List(), alphaMutations, coldResistanceIInformation)
  case BetaMutations extends PowerUpType(2, List(AlphaMutations), betaMutations, coldResistanceIInformation)
  case GammaMutations extends PowerUpType(2, List(BetaMutations), gammaMutations, coldResistanceIInformation)
  case OmegaMutations extends PowerUpType(2, List(GammaMutations), omegaMutations, coldResistanceIInformation)
