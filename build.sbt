name := "scalatrading"
version := "1.0.0"
scalaVersion := "2.12.1"
scalacOptions := Seq("-target:jvm-1.8", "-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

resolvers += Resolver.typesafeRepo("releases")

libraryDependencies ++= {

  val akkaV       = "2.5.19"
  val akkaHttpV   = "10.1.7"
  val macwireV    = "2.3.0"
  val scalaTestV  = "3.0.1"
  val scalaMockV  = "3.5.0"
  val enumeratumV = "1.5.12"
  val scalaCsv    = "1.3.5"
  val kebsV       = "1.4.1"
  val bcryptV     = "3.0"

  lazy val akkaBase = Seq(
    "com.typesafe.akka" %% "akka-actor"  % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j"  % akkaV
  )

  lazy val akkaHttp = Seq(
    "com.typesafe.akka" %% "akka-http-core"       % akkaHttpV,
    "com.typesafe.akka" %% "akka-http"            % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpV % "test"
  )

  lazy val macwire = Seq(
    "com.softwaremill.macwire" %% "macros" % macwireV % "provided",
    "com.softwaremill.macwire" %% "util"   % macwireV,
    "com.softwaremill.macwire" %% "proxy"  % macwireV
  )

  lazy val test = Seq(
    "org.scalatest" %% "scalatest"                   % scalaTestV % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockV % "test"
  )

  lazy val others = Seq(
    "com.beachape"         %% "enumeratum"      % enumeratumV,
    "com.github.tototoshi" %% "scala-csv"       % scalaCsv,
    "pl.iterators"         %% "kebs-spray-json" % kebsV,
    "com.github.t3hnar"    %% "scala-bcrypt"    % bcryptV
  )

  akkaBase ++ akkaHttp ++ macwire ++ test ++ others
}
