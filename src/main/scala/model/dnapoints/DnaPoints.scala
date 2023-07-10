package model.dnapoints

object DnaPoints {

  trait DnaPointsHandler:
    def collectedPoints: Int
    def collectedPoints_= (newAmount: Int): Unit

  object DnaPointsHandler:
    def apply(): DnaPointsHandler = new SimpleDnaPointsHandler(0)

    private class SimpleDnaPointsHandler(var collectedPoints: Int) extends DnaPointsHandler

}