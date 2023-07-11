package utils

import scala.util.Random

object Iterables {

  extension [T](l: List[T])
    def getRandomElement(): Option[T] = l.isEmpty match
      case true => None
      case _ => Some(l(Random.nextInt(l.size)))

}
