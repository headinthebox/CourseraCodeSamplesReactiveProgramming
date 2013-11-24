package coursera.socket.safe

import scala.util.{Failure, Success}
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import scala.collection.immutable.Queue
import akka.serialization._
import coursera.socket.EmailMessage
import scala.imaginary.Http.{Http, Request}

object Socket {
  def apply(): Socket = new Socket(){}
}

trait Socket {

  val serialization: Serialization = ???

  val memory = Queue[EmailMessage](
    EmailMessage(from = "Erik", to = "Roland"),
    EmailMessage(from = "Martin", to = "Erik"),
    EmailMessage(from = "Roland", to = "Martin")
  )

  def readFromMemory(): Future[Array[Byte]] = {
    Future {
      val email = memory.dequeue
      val serializer = serialization.findSerializerFor(email)
      serializer.toBinary(email)
    }
  }

  def sendToEurope(packet: Array[Byte]): Future[Array[Byte]] = {
    Http("mail.server.eu", Request(packet)).filter(_.isOK).map(_.body)
  }

  def sendTo(url: String, packet: Array[Byte]): Future[Array[Byte]] =
    Http(url, Request(packet)).filter(_.isOK).map(_.body)

  def sendToSafeI(packet: Array[Byte]): Future[Array[Byte]] =
    sendTo("...europe...", packet) recoverWith {
      case europeError ⇒ sendTo("...usa...", packet) recover {
        case usaError  ⇒ usaError.getMessage.getBytes
      }
    }

  def sendToSafeII(packet: Array[Byte]): Future[Array[Byte]] =
    sendTo("...europe...", packet) fallbackTo { sendTo("...usa...", packet) } recover {
      case europeError => europeError.getMessage.getBytes
    }

  def sendPacketToEuropeAndBackI(): Unit = {

    val socket = Socket()

    val packet: Future[Array[Byte]] = socket.readFromMemory()

    val confirmation: Unit /* Future[Array[Byte]] */ =
      packet onComplete {
        case Success(p) => socket.sendToEurope(p)
        case Failure(t) => ???
      }

  }

  def sendPacketToEuropeAndBackII(): Unit = {
    val socket = Socket()

    val packet: Future[Array[Byte]] =
      socket.readFromMemory()

    packet onComplete {
      case Success(p) => {
        val confirmation: Future[Array[Byte]] = socket.sendToEurope(p)
        ???
      }
      case Failure(t) => ???
    }
  }

  def sendPacketToEuropeAndBackIII(): Unit = {
    val socket = Socket()
    val packet: Future[Array[Byte]] = socket.readFromMemory()
    val confirmation: Future[Array[Byte]] = packet.flatMap(socket.sendToEurope(_))
  }

  def sendPacketToEuropeAndBackIV(): Unit = {
    val socket = Socket()
    val confirmation: Future[Array[Byte]] = for {
      packet       <- socket.readFromMemory()
      confirmation <- socket.sendToSafeII(packet)
    } yield confirmation
  }

  def sendToAndBackUp(packet: Array[Byte]):Future[(Array[Byte], Array[Byte])] = {
    val europeConfirm = sendTo("...europe...", packet)
    val usaConfirm = sendTo("...usa...", packet)
    europeConfirm.zip(usaConfirm)
  }

}

