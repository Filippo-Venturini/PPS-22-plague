package model.powerUp

/**
 * Represent a single Power Up instance. It contains the its type of Power Up and if is already bought
 */
trait PowerUp:
  def powerUpType: PowerUpType
  var hasBeenBought: Boolean = false

object PowerUp:
  /**
   * Create a Power Up instance with determined characteristic
   * @param powerUpType represent the specific kind of Power Up
   * @return the Power Up instance in base of the Power Ups type passed
   */
  def apply(powerUpType: PowerUpType): PowerUp =
    PowerUpImpl(powerUpType)

  /**
   * Private class to implement the Power Up type
   * @param powerUpType represent the specific kind of Power Up
   */
  private class PowerUpImpl(override val powerUpType: PowerUpType) extends PowerUp
