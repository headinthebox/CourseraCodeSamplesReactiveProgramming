package coursera.socket.unsafe

object Socket {
  def apply(): Socket = new Socket(){
  }
}

trait Socket {

  def readFromMemory(): Array[Byte] = ???
  def sendToEurope(packet: Array[Byte]): Array[Byte] = ???

  def sendPacketToEuropeAndBack(): Unit = {
    val socket = Socket()
    val packet = socket.readFromMemory()
    val confirmation = socket.sendToEurope(packet)
  }

}

