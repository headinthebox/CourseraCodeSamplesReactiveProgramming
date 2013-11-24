package scala.imaginary.Http {

import scala.concurrent.Future

  object Request {
     def apply(bytes: Array[Byte]): Request = ???
  }

  trait Request{}

  object Response {
    def apply(bytes: Array[Byte]): Response = ???
  }

  trait Response{
    def isOK: Boolean
    def body: Array[Byte]
  }

  object Http {
   def apply(url: String, request: Request): Future[Response] = ???
  }

}
