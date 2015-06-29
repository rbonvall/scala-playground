scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
, "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.2"
, "org.apache.velocity"    % "velocity"                  % "1.6.2"
, "org.apache.poi"         % "poi"                       % "3.11"
, "org.apache.poi"         % "poi-ooxml"                 % "3.11"
, "org.scala-lang.modules" %% "scala-xml"                % "1.0.3"
, "org.eclipse.jgit"       % "org.eclipse.jgit"          % "4.0.0.201506090130-r"
)

