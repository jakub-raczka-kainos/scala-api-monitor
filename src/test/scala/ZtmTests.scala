
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}


class ZtmTests() extends TestKit(ActorSystem("system")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "An Ztm actor" must {

    "send back messages \"Started Task\"" in {

      val ztm = system.actorOf(Props[Ztm])
      ztm ! StartTask("http://91.244.248.30/dataset/c24aa637-3619-4dc2-a171-a23eec8f2172/resource/cd4c08b5-460e-40db-b920-ab9fc93c1a92/download/stops.json")

      expectMsgType[String]
    }
  }
}