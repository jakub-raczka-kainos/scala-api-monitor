import akka.actor.Actor

class Worker extends Actor {
  override def receive: Receive = {
    case StartTask(url) => {
      val json = scala.io.Source.fromURL(url)
      println(json)
    }
  }
}
