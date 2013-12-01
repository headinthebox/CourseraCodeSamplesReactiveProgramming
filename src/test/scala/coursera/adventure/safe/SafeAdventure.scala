package coursera.adventure.safe

import coursera.adventure._
import scala.util.{Failure, Success, Try}

object Adventure {
  def apply(): Adventure = new Adventure(){
    var eatenByMonster: Boolean = true
    val treasureCost: Int = 42
  }
}

trait Adventure {

  var eatenByMonster: Boolean
  val treasureCost: Int

  def collectCoins(): Try[List[Coin]] = ???

  def buyTreasure(coins: List[Coin]): Try[Treasure] = ???

  def PlayI(): Unit = {
    val adventure = Adventure()
    val coins: Try[List[Coin]] = adventure.collectCoins()
    val treasure: Try[Treasure] = coins match {
      case Success(cs)          => adventure.buyTreasure(cs)
      case Failure(t)           => Failure(t)
    }
  }

  def PlayII(): Unit = {
    val adventure = Adventure()
    val coins: Try[List[Coin]] = adventure.collectCoins()
    val treasure: Try[Treasure] = coins.flatMap(cs => adventure.buyTreasure(cs))
  }

  def PlayIII(): Unit = {
    val adventure = Adventure()
    val treasure: Try[Treasure] = for {
      coins <- adventure.collectCoins()
      treasure <- buyTreasure(coins)
    } yield treasure
  }
}
