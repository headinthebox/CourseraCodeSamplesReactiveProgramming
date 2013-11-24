package coursera.adventure

class Coin(val value: Int){}

case class Gold() extends Coin(value = 200) {}
case class Silver() extends Coin(value = 100) {}

