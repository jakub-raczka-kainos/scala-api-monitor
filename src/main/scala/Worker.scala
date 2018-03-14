import java.time.Instant
import akka.actor.Actor
import scalaj.http.Http

class Worker extends Actor {
  override def receive: Receive = {
    case FetchDelays(url) =>
      val id = url.split("=").last
      val timeInMillis = System.currentTimeMillis()
      val instant = Instant.ofEpochMilli(timeInMillis)

      try {

        val rawJson = Http(url).asString.body

        val wrapper = s"""{
          | "url":"${url}",
          | "date":"${instant}",
          | "content":${rawJson}
         }""".stripMargin

        println(wrapper)

      } catch {
        case e:java.net.SocketTimeoutException => println("BAD ID: " + id)
      }

  }
}
