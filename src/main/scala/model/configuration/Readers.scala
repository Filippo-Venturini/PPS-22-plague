package model.configuration

object Readers:

  object TypeChecker:
    def checkTypes(values: List[String], castingOperations: List[String => Any]): Boolean =
      values.zipWithIndex
        .filter((_, i) => i < castingOperations.size)
        .map((s, i) => castingOperations(i).apply(s))
        .filter(cv => cv match
          case None => true
          case _ => false)
        .size == 0




