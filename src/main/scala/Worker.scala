import akka.actor.Actor

import scalaj.http.Http

class Worker extends Actor {
  override def receive: Receive = {
    case FetchDelays(url) =>
      print('.')
      val id = url.split("=").last
      try {
        val rawJson = Http(url).asString.body
        parse(rawJson)

        if (id == 35640 ) print(rawJson)
      } catch {
        case e:java.net.SocketTimeoutException => println("BAD ID: " + id)
      }

  }
}
