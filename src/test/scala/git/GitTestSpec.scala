package git

import org.scalatest.FunSpec
import scala.collection.immutable.ListMap

class GitTestSpec extends FunSpec {
  describe("Git test") {
    it("works") {
      val tree1 =
        Tree("root", ListMap(
          "first" → Tree("abcde", ListMap(
            "a" → Tree("lorem", ListMap.empty),
            "b" → Tree("ipsum", ListMap.empty),
            "c" → Tree("dolor", ListMap(
              "cc" → Tree("sit", ListMap.empty)
            )),
            "d" → Tree("amet", ListMap.empty)
          )),
          "second" → Tree("defghi", ListMap(
            "x" → Tree("stuff", ListMap.empty),
            "y" → Tree("guareva", ListMap.empty)
          ))
        ))
      val tree2 =
        Tree("root", ListMap(
          "first" → Tree("abcde", ListMap(
            "a" → Tree("lorem", ListMap.empty),
            "c" → Tree("dolor", ListMap(
              "cc" → Tree("sit", ListMap(
                "second" → Tree("defghi", ListMap(
                  "x" → Tree("stuff", ListMap.empty),
                  "y" → Tree("guareva", ListMap.empty)
                ))
              ))
            )),
            "d" → Tree("amet", ListMap.empty)
          ))
        ))

      val path = "/tmp/gittest"
      GitTest.createRepo(path)
      val repo = Repo(path)
      val t1 = repo.insertTree(tree1)
      val c1 = repo.commit(t1, "First commit")
      println(s"First commit: $c1")
      val t2 = repo.insertTree(tree2)
      val c2 = repo.commit(t2, "Second commit", Seq(c1))
      println(s"Second commit: $c2")
    }
  }
}
