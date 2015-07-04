package git

import org.eclipse.jgit.api.{Git, CommitCommand}
import org.eclipse.jgit.lib._
import org.eclipse.jgit.storage.file.FileRepositoryBuilder

import scala.collection.immutable.ListMap
import java.io.File

import scala.collection.JavaConverters._


case class Tree[T](value: T, children: ListMap[String, Tree[T]])


case class Repo(path: String) {
  val repo = FileRepositoryBuilder.create(new File(s"$path/.git"))
  val me = new PersonIdent("Roberto Bonvallet", "rbonvall@gmail.com")
  val inserter = repo.newObjectInserter
  val reader = repo.newObjectReader

  def insertBlob(content: String): ObjectId = {
    val id = inserter.insert(Constants.OBJ_BLOB, content.getBytes("utf-8"))
    inserter.flush()
    id
  }

  def readBlob(id: ObjectId): String = {
    val obj = reader.open(id)
    require(obj.getType == Constants.OBJ_BLOB)
    new String(obj.getBytes, "utf-8")
  }

  def insertTree(tree: Tree[String]): ObjectId = {
    val formatter = new TreeFormatter
    val blobId = insertBlob(tree.value)
    formatter.append("VALUE", FileMode.REGULAR_FILE, blobId)
    for {(n, t) ← tree.children} {
      val childId = insertTree(t)
      formatter.append(n, FileMode.TREE, childId)
    }
    val id = inserter.insert(formatter)
    inserter.flush()
    id
  }

  def commit(treeId: ObjectId, msg: String, parents: Seq[ObjectId] = Nil): ObjectId = {
    val headId = repo.resolve(Constants.HEAD + "^{commit}")
    val builder = new CommitBuilder
    builder.setTreeId(treeId)
    builder.setMessage(msg)
    builder.setAuthor(me)
    builder.setCommitter(me)
    if (headId != null)
      builder.setParentIds(Seq(headId).asJava)
    val id = inserter.insert(builder)
    inserter.flush()
    updateHead(id)
    id
  }

  def updateHead(commitId: ObjectId) = {
    // val commit = repo.parseCommit(commitId)
    val update = repo.updateRef(Constants.HEAD)
    update.setNewObjectId(commitId)
    update.setRefLogMessage(s"commit: YES, COMMIT!", false)
    update.forceUpdate()
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

