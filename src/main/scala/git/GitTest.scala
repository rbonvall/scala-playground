package git

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib._
import org.eclipse.jgit.storage.file.FileRepositoryBuilder

import scala.collection.immutable.ListMap
import java.io.File

import scala.collection.JavaConverters._


case class Tree[T](value: T, children: ListMap[String, Tree[T]])


case class Repo(path: String) {
  val repo = FileRepositoryBuilder.create(new File(s"$path/.git"))
  val me = new PersonIdent("Roberto Bonvallet", "rbonvall@gmail.com")

  def insertBlob(content: String): ObjectId = {
    val inserter = repo.newObjectInserter
    val id = inserter.insert(Constants.OBJ_BLOB, content.getBytes("utf-8"))
    inserter.flush()
    id
  }

  def readBlob(id: ObjectId): String = {
    val loader = repo.newObjectReader.open(id)
    require(loader.getType == Constants.OBJ_BLOB)
    new String(loader.getBytes, "utf-8")
  }

  def insertTree(tree: Tree[String]): ObjectId = {
    val formatter = new TreeFormatter
    val blobId = insertBlob(tree.value)
    formatter.append("VALUE", FileMode.REGULAR_FILE, blobId)
    for {(n, t) ← tree.children} {
      val childId = insertTree(t)
      formatter.append(n, FileMode.TREE, childId)
    }
    val inserter = repo.newObjectInserter
    val id = inserter.insert(formatter)
    inserter.flush()
    id
  }

  def commit(treeId: ObjectId, msg: String, parents: Seq[ObjectId] = Nil): ObjectId = {
    val builder = new CommitBuilder
    builder.setTreeId(treeId)
    builder.setMessage(msg)
    builder.setAuthor(me)
    builder.setCommitter(me)
    builder.setParentIds(parents.asJava)
    val inserter = repo.newObjectInserter
    val id = inserter.insert(builder)
    inserter.flush()
    id
  }


}

object GitTest {
  def main(args: Array[String]) = {
  }

  def createRepo(path: String) = {
    val dir = new File(path)
    dir.delete()

    Git.init.setDirectory(dir).call

    val f = new File(dir.getAbsolutePath, ".git")
    val repo = FileRepositoryBuilder.create(f)

    println(s"Created new repo at ${repo.getDirectory}")
    repo.close()
  }

}

