package coursera.adventure

class Coin(val Value: Integer){}

case class Gold() extends Coin(Value = 200) {}
case class Silver() extends Coin(Value = 100) {}

