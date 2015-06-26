package git

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder

object GitTest {
  def main(args: Array[String]) {
    val builder = new FileRepositoryBuilder
    val repo = builder.setGitDir(new java.io.File("/tmp/gittest"))
      .readEnvironment()
      .findGitDir()
      .build()

    val head = repo.resolve("HEAD")
    val HEAD = repo.getRef("refs/heads/master")
  }
}

