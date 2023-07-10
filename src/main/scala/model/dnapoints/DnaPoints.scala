package model.dnapoints

object DnaPoints {

  trait DnaPoint:
    def collect(): Unit

  trait DnaPointsHandler:
    def collectedPoints: Int

    def collectedPoints_=(newAmount: Int): Unit

    def spawnDnaPoint(): DnaPoint

  object DnaPointsHandler:
    def apply(): DnaPointsHandler = new BasicDnaPointsHandler(0)

    private class BasicDnaPointsHandler(override var collectedPoints: Int) extends DnaPointsHandler :
      private var spawnedPoints: List[DnaPoint] = List()

      override def spawnDnaPoint(): DnaPoint =
        spawnedPoints = BasicDnaPoint(this) +: spawnedPoints
        spawnedPoints.head

      case class BasicDnaPoint(handler: BasicDnaPointsHandler) extends DnaPoint:
        private var collected = false
        def collect(): Unit = this.collected match
          case false =>
            this.collected = true
            handler.collectedPoints = handler.collectedPoints + 1
            handler.spawnedPoints = handler.spawnedPoints.filterNot(_ == this)
          case _ =>
}