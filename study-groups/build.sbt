import com.github.play2war.plugin._

name := "study-groups"

version := "0.1-SNAPSHOT"

// Play2War
Play2WarPlugin.play2WarSettings
Play2WarKeys.servletVersion := "3.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

libraryDependencies ++= Seq(
  jdbc,
  javaJpa,
  cache,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
  "org.mindrot" % "jbcrypt" % "0.3m"
)     

// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java project. Don't expect Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.ManagedClasses  
EclipseKeys.withSource := true
EclipseKeys.withJavadoc := true
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
