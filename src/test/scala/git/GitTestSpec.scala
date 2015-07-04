package git

import org.scalatest.FunSpec
import scala.collection.immutable.ListMap

class GitTestSpec extends FunSpec {
  describe("Git test") {
    it("works") {
      val ø = ListMap.empty[String, Tree[String]]
      val tree1 =
        Tree("root", ListMap(
          "first" → Tree("abcde", ListMap(
            "a" → Tree("lorem", ø),
            "b" → Tree("ipsum", ø),
            "c" → Tree("dolor", ListMap(
              "cc" → Tree("sit", ø)
            )),
            "d" → Tree("amet", ø)
          )),
          "second" → Tree("defghi", ListMap(
            "x" → Tree("stuff", ø),
            "y" → Tree("guareva", ø)
          ))
        ))
      val tree2 =
        Tree("root", ListMap(
          "first" → Tree("abcde", ListMap(
            "a" → Tree("lorem", ø),
            "c" → Tree("dolor", ListMap(
              "cc" → Tree("sit", ListMap(
                "second" → Tree("defghi", ListMap(
                  "x" → Tree("stuff", ø),
                  "y" → Tree("guareva", ø)
                ))
              ))
            )),
            "d" → Tree("amet", ø)
          ))
        ))

      val path = "/tmp/gittest"
      GitTest.createRepo(path)
      val repo = Repo(path)
      val t1 = repo.insertTree(tree1)
      val c1 = repo.commit(t1, "First commit")
      println(s"First commit:  ${c1.name()}")
      val t2 = repo.insertTree(tree2)
      val c2 = repo.commit(t2, "Second commit", Seq(c1))
      println(s"Second commit: ${c2.name()}")
    }
  }
}
