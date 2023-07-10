package model.dnapoints

object DnaPoints {

  trait DnaPointsHandler:
    def collectedPoints: Int

    def collectedPoints_=(newAmount: Int): Unit

    def spawnDnaPoint(): DnaPoint

  object DnaPointsHandler:
    def apply(): DnaPointsHandler = new SimpleDnaPointsHandler(0)

    private class SimpleDnaPointsHandler(override var collectedPoints: Int) extends DnaPointsHandler :
      override def spawnDnaPoint(): DnaPoint = DnaPoint(this)

  case class DnaPoint(handler: DnaPointsHandler):
    def collect(): Unit = handler.collectedPoints = handler.collectedPoints + 1
}