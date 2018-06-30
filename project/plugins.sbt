logLevel := Level.Warn

resolvers += "Flyway" at "https://flywaydb.org/repo"

addSbtPlugin("com.geirsson"     % "sbt-scalafmt"        % "0.4.10")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.5")