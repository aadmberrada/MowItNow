package tondeuse

import scala.collection.mutable.ListBuffer
import scala.io.Source

object TestTondeuse {

  def main(args: Array[String]): Unit = {
    val source = "inputs/commandes.txt"

    val lien = Source.fromFile(source)
    val lines = lien.getLines.toList
    lien.close

    val limites_sup = lines(0).split(" ")
    var limites_inf = ListBuffer[Array[String]]()
    var ordres = ListBuffer[String]()
    for((line, i) <- lines.zipWithIndex){

      if(i > 0 && i%2 == 1){
        limites_inf += line.split(" ")
      } else if(i > 0 && i%2 == 0){
        ordres += line
      }
    }


    for((debut, ordre) <- limites_inf.zip(ordres)){

      val tondeuse = new Tondeuse(
        (debut(0).toInt, debut(1).toInt),
        debut(2).charAt(0),
        (limites_sup(0).toInt, limites_sup(1).toInt)
      )

      tondeuse.mouvement(ordre)

      println(tondeuse.toString())
    }
  }
}
