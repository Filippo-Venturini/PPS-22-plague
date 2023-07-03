package model.powerUp
import model.infection.Virus

trait PowerUpLogic:
  def applyTo(virus: Virus): Unit
/*
object PowerUpLogics:
  val coldResistanceI: PowerUpLogic = new PowerUpLogic:
    override def applyTo(virus: Any): Unit = ???

  val ColdResistanceII: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val HotResistanceI: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val HotResistanceII: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val BacterialResistance: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val AirportEnablement: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val PortEnablement: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val InfectionThroughAnimals: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val InfectionThroughRespiratoryTract: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val MedicinesResistance: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val InfectedDrinkingWater: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???

  val SpontaneousMutations: PowerUpLogic = new PowerUpLogic :
    override def applyTo(virus: Any): Unit = ???
*/