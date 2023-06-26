package model.configuration

import Parsers.Parser

import scala.io.Source
import scala.util.{Success, Failure, Using}

object File:
  def readLines(path: String): Iterable[String] =
    Using(Source.fromFile(path))(_.getLines()) match
      case Success(value) => value.toSeq
      case Failure(_) => Seq()