package mpde.vector.test

import dsl.la.norep._
import dsl.la._
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/*
 * This tests shows the mechanics of the Rep[T] based approach.
 */
@RunWith(classOf[JUnitRunner])
class NoRepSpec extends FlatSpec with ShouldMatchers {

  //  "A deep embedding of la without Rep types" should "compile" in {
  //    val x = new VectorDSL {
  //      def main = {
  //        val v1 = DenseVector(liftTerm(1))
  //        val res = (v1 + (DenseVector(liftTerm(3), liftTerm(4), liftTerm(5)) * SparseVector[Int](liftTerm(6), liftTerm(7), liftTerm(8))))
  //        val mappedRes = res.map(_ + liftTerm(1))
  //        mappedRes
  //      }
  //    }
  //  }
  //
  //  "Baby steps first: No code at all. Just return a constant!!" should "compile" in {
  //    val out = 1
  //    var varOut = 2
  //    new VectorDSL {
  //      def main = { val x = DenseVector(out, varOut, 3)(null, null); var y = x; (); }
  //    }
  //  }

  it should "lift Vector to NoRep" in {

    val x = dsl.la.laLiftNoRep {
      //val a: Vector[Int] = DenseVector(1, 2)
      //val a: Vector[Double] = ???
      //val a = DenseVector(1, 2)
      //a.map(_ + 2)

      val a = DenseVector(1, 2)
      //val b = a
      //val c: Vector[Int] = DenseVector(1, 2, 3)
      //a.map(x ⇒ x + 2)
      //b.map(_ + 2)

      a.reconstruct((x, y) ⇒ x + y)
      a.reconstruct(_ + _)

      //problem to transform
      //==> def testing[T >: Nothing <: Any](a: Int, b: T): Nothing = scala.this.Predef.???
      //def testing[T](a: Int, b: T) = ???
    }

    ()
  }

  it should "test ascription lift" in {

    val x = dsl.la.laLiftNoRep {
      DenseVector(1, 2): Vector[Int]
      //DenseVector(1, 2): Any
      //1
      //1: Int
      //1: Double
      //1: Any
      //1: Vector[Int]

      //val a: Vector[Int] = ???
      //val a: Int = 1
      //val a: Vector[Int] = ???
      //a: Any
      //a: Double
    }

    ()
  }

  it should "test __ifThenElse" in {

    val x = dsl.la.laLiftNoRep {
      if (true) 1: Int else 2.0: Double
    }

    ()
  }

  it should "test function type lift" in {

    val x = dsl.la.laLiftNoRep {
      val a = (x: Int) ⇒ x + 5
      val b: Int ⇒ Int ⇒ Double = ???
      val c: (Int, Int) ⇒ Double = ???
      val d: Vector[Int] ⇒ Vector[Double] = ???
      val e: Array[Int ⇒ Int] = ???
    }

    ()
  }
}
