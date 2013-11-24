package coursera.iterable

import scala.io.Source

class Blocking {

  def ReadLinesFromDisk(path: String): Iterator[String] = {
    Source.fromFile(path).getLines()
  }

  def Example(): Unit = {
    val lines = ReadLinesFromDisk("\\c:\\tmp.txt")

    for(line <- lines) {
      ???
    }
  }
}
