import akka.actor.Actor

import scalaj.http.Http

class Worker extends Actor {
  override def receive: Receive = {
    case FetchDelays(url) =>
      println(url)
      val rawJson = Http(url).asString.body
      val id = url.split("=").last
      if (id == 35640 ) print(rawJson)
  }
}
