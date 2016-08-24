package xml

import org.scalatest.FunSpec
import Populate.XsplittableString

class PopulateSpec extends FunSpec {

  val tree =
    <a>
      <b>
        <c>
          <g>10</g>
          <h>20</h>
        </c>
      </b>
      <m>
        <n>30</n>
        <o>40</o>
      </m>
    </a>

  describe("XsplittableString") {
    it("makes strings splittable into Xpath pieces") {
      assert("".x            === List())
      assert("abc".x         === List("abc"))
      assert("abc/def/ghi".x === List("abc", "def", "ghi"))
    }
  }

  describe("Populate.containsPath") {
    it("checks whether the path leads to an existing element of the tree") {
      assert( Populate.containsPath(tree, Nil))
      assert( Populate.containsPath(tree, "a".x))
      assert( Populate.containsPath(tree, "a/b".x))
      assert( Populate.containsPath(tree, "a/m".x))
      assert( Populate.containsPath(tree, "a/m/n".x))
      assert(!Populate.containsPath(tree, "x".x))
      assert(!Populate.containsPath(tree, "b/c".x))
      assert(!Populate.containsPath(tree, "a/b/c/h/h".x))
    }
  }

  describe("Populate.prefixes") {
    it("creates all the prefixes for a given list") {
      val input = List(55, 44, 33, 22)
      val expected = List(
        List(55),
        List(55, 44),
        List(55, 44, 33),
        List(55, 44, 33, 22)
      )
      assert(Populate.prefixes(input) === expected)
    }
  }

  describe("Populate.splits") {
    it("creates all the possible splits of a given list") {
      val input = List(55, 44, 33, 22)
      val expected = List(
        (List(), List(55, 44, 33, 22)),
        (List(55),   List(44, 33, 22)),
        (List(55, 44),   List(33, 22)),
        (List(55, 44, 33),   List(22)),
        (List(55, 44, 33, 22), List())
      )
      assert(Populate.splits(input) === expected)
    }
  }

  describe("Populate.splitPathAtInsertionPoint") {
    it("works") {
      assert(Populate.splitPathAtInsertionPoint(tree, "a/b"      .x) === ("a/b"  .x, ""     .x))
      assert(Populate.splitPathAtInsertionPoint(tree, "a/b/c/d/e".x) === ("a/b/c".x, "d/e"  .x))
      assert(Populate.splitPathAtInsertionPoint(tree, "x/y/z"    .x) === (""     .x, "x/y/z".x))
    }
  }

  describe("Populate.createElem") {
    it("creates a simple element for an xpath") {
      assert(Populate.createElem("x/y/z".x, 123) === <x><y><z>123</z></y></x>)
    }
  }

  describe("Populate") {

    it("inserts an element into a tree in the given location") {
      val path = "a/b/c/d/e/f"
      val result =
        <a>
          <b>
            <c>
              <g>10</g>
              <h>20</h>
              <d>
                <e>
                  <f>999</f>
                </e>
              </d>
            </c>
          </b>
          <m>
            <n>30</n>
            <o>40</o>
          </m>
        </a>
      assert(Populate.insert(tree, path, 999) === result)

    }
  }

}

