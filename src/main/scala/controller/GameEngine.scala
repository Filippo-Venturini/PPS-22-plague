package controller

class GameEngine():
  val refreshTime: Int = 200

  def start(): Void =
    //Load configurations
    gameLoop()

  def gameLoop(): Void =
    val startTime: Long = System.currentTimeMillis()
    println("loop")
    //Compute Internal Infection
    //Compute External Infection
    //Compute Vaccine
    Thread.sleep(refreshTime - (System.currentTimeMillis() - startTime))
    gameLoop()
