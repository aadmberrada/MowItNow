package tondeuse

class Tondeuse(var positions:(Int, Int),
               var direction:Char,
               val max_positions:(Int, Int)) {


  private val directionMap = Map('N' -> ('W', 'E'), 'E' -> ('N', 'S'), 'S' -> ('E', 'W'), 'W' -> ('S', 'N'))

  override def toString: String = {
    this.positions._1 + " " + this.positions._2 + " " + this.direction
  }

  private def avancer(): Unit ={
    this.positions = this.direction match {
      case 'N' if this.positions._2 + 1 <= this.max_positions._2 =>  (this.positions._1, this.positions._2 + 1)
      case 'W' if this.positions._1 - 1 >= 0 => (this.positions._1 - 1, this.positions._2)
      case 'S' if this.positions._2 - 1 >= 0 => (this.positions._1, this.positions._2 - 1)
      case 'E' if this.positions._1 + 1 <= this.max_positions._2 => (this.positions._1 + 1, this.positions._2)
      case _ => this.positions
    }
  }

  private def deplacer(ordre:Char):Unit = {
    ordre match {
      case 'G' => this.direction = this.directionMap(this.direction)._1
      case 'D' => this.direction = this.directionMap(this.direction)._2
      case 'A' => this.avancer()
      case _ => throw new IllegalArgumentException()
    }
  }

  def mouvement(ordres:String): Unit ={
    for(ordre <- ordres){
      try{
        this.deplacer(ordre)
      } catch {
        case _: IllegalArgumentException => throw new IllegalArgumentException("'" + ordre + "'" +
          "ordre in input file is not supported.")
      }
    }
  }
}
