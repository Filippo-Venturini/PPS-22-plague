package model.dnapoints

import model.dnapoints.DnaPoints.Logic.*
import model.world.{Filters, Region, World}

object DnaPoints {

  /**
   * an entity that can be collected and used to purchase PowerUps. Each Dna point is associated to a region in order to
   * control their spawn
   */
  trait DnaPoint:
    /**
     * @return the region on which the DnaPoint is
     */
    def regionName: String

    /**
     * collect the DnaPoint
     */
    def collect(): Unit

  /**
   * Handle every aspect regarding DnaPoints, keeping the DnaPoint balance and the spawn logic.
   */
  trait DnaPointsHandler:
    /**
     * @return the amount of DnaPoint currently owned
     */
    def collectedPoints: Int

    /**
     * @param newAmount set the amount of DnaPoint currently owned
     */
    def collectedPoints_=(newAmount: Int): Unit

    /**
     * create a new DnaPoint on the given region
     * @param region the DnaPoint spawn region
     * @return a new DnaPoint
     */
    def spawnDnaPoint(region: Region): Option[DnaPoint]

    /**
     * add a new observer to the observers' list. Each observer is notified when a DnaPoint spawns.
     * @param observer the new observer
     */
    def addObserver(observer: DnaPointSpawnObserver): Unit
    /**
     * invoke the DnaPoint spawn logic. After this method's call new DnaPoint may be created depending on the used spawn
     * logic
     */
    def computeDnaPointSpawn(): Unit

  object DnaPointsHandler:
    /**
     * @param spawnLogic the DnaPoints' spawn logic
     * @return a DnaPointsHandler having the given spawn logic
     */
    def apply(spawnLogic: SpawnPointLogic): DnaPointsHandler = new BasicDnaPointsHandler(0, spawnLogic)

    private class BasicDnaPointsHandler(override var collectedPoints: Int, private val logic: SpawnPointLogic) extends DnaPointsHandler :
      import model.world.Filters.given_RegionFilter
      private var spawnedPoints: List[DnaPoint] = List()
      private var observers: List[DnaPointSpawnObserver] = List()
      private val spawnPointLogic: SpawnPointLogic = logic

      override def spawnDnaPoint(region: Region): Option[DnaPoint] = spawnedPoints.map(_.regionName).contains(region.name) match
        case true => None
        case _ =>
          spawnedPoints = BasicDnaPoint(this, region.name) +: spawnedPoints
          observers.foreach(_.onDnaPointSpawn(spawnedPoints.head))
          Some(spawnedPoints.head)

      override def computeDnaPointSpawn(): Unit =
        spawnPointLogic.evaluate().foreach(spawnDnaPoint)


      override def addObserver(observer: DnaPointSpawnObserver): Unit = observers = observer +: observers

      case class BasicDnaPoint(handler: BasicDnaPointsHandler, override val regionName: String) extends DnaPoint:
        private var collected = false
        def collect(): Unit = this.collected match
          case false =>
            this.collected = true
            handler.collectedPoints = handler.collectedPoints + 1
            handler.spawnedPoints = handler.spawnedPoints.filterNot(_ == this)
          case _ =>

  object Logic:
    /**
     * a logic that determinates whenever spawn a new DnaPoint and on which region
     */
    abstract class SpawnPointLogic:
      /**
       * compute the logic evaluation.
       * @return the list of the region on which spawn the dnaPoints
       */
      def evaluate(): Set[Region]

    /**
     * a Logic that always return an empty regions' set
     */
    class EmptyLogic extends SpawnPointLogic:
      /**
       *  @return always return an empty list
       */
      override def evaluate(): Set[Region] = Set()

    /**
     * a Logic that allows to spawn a dna on each newly infected region
     */
    trait OnNewInfectedRegions(private val world: World) extends SpawnPointLogic:
      private var infectedRegionsOnLastStep: List[Region] = List()

      /**
       *  @return the region that were not infected at the previous step
       */
      abstract override def evaluate(): Set[Region] =
        val infectedRegions = world.getRegions(using Filters.infectedRegions)
        val newlyInfectedRegions = infectedRegions.diff(infectedRegionsOnLastStep)
        infectedRegionsOnLastStep = infectedRegions
        newlyInfectedRegions.toSet ++ super.evaluate()

    /**
     * a Logic that allows to spawn a dna every X seconds
     */
    trait EveryXSeconds(private val world: World, private val spawnRate: Int) extends SpawnPointLogic:
      import utils.Iterables.getRandomElement
      private var lastStepTime = System.currentTimeMillis()
      private val secondsToMillis = 1000;

      /**
       *  @return an infected region if are elapsed at least X seconds from the last spawn
       */
      abstract override def evaluate(): Set[Region] = (System.currentTimeMillis()-lastStepTime) >= spawnRate*secondsToMillis match
        case true =>
          lastStepTime = System.currentTimeMillis()
          world.getRegions(using Filters.infectedRegions).getRandomElement().toSet ++ super.evaluate()
        case _ => super.evaluate()

    /**
     * a logic that implements OnNewInfectedRegions
     * @param world the world to use on dna points spawning
     */
    case class OnNewInfectedRegionsLogic(world: World) extends EmptyLogic with OnNewInfectedRegions(world)

    /**
     * a logic that implements EveryXSeconds
     * @param world the world to use on dna points spawning
     * @param spawnRate the DnaPoint spawn rate
     */
    case class EveryXSecondsLogic(world: World, spawnRate: Int) extends EmptyLogic with EveryXSeconds(world, spawnRate)

    /**
     * a logic that implements both EveryXSeconds and OnNewInfectedRegions
     * @param world     the world to use on dna points spawning
     * @param spawnRate the DnaPoint spawn rate
     */
    case class BasicLogic(world: World, spawnRate: Int) extends EmptyLogic with OnNewInfectedRegions(world) with EveryXSeconds(world, spawnRate)

  /**
   * a DnaPointSpawnObserver is notified at each DnaPoint spawn
   */
  trait DnaPointSpawnObserver:
    /**
     * call this method to trigger the observer reaction
     * @param dnaPoint the spawned dnaPoint
     */
    def onDnaPointSpawn(dnaPoint: DnaPoint): Unit
}