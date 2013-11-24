package coursera.socket

object EmailMessage {
  def apply(from: String, to: String): EmailMessage = {
    new EmailMessage(from, to)
  }
}

class EmailMessage(val from: String, val to: String){}







