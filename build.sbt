import com.typesafe.sbt.SbtNativePackager.packageArchetype
import com.typesafe.sbt.packager.docker._

name := """codacy-engine-jshint"""

version := "1.0"

val metaVersion = "0.1.0-SNAPSHOT"

val languageVersion = "2.11.7"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

scalaVersion := languageVersion

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.8"

libraryDependencies += "com.typesafe.play" %% "play-ws" % "2.3.8"

libraryDependencies ++= Seq(
  "org.scalameta" %% "scalameta" % metaVersion,
  "org.scalameta" %% "scalameta-foundation" % metaVersion,
 // "org.scalameta" % s"scalahost_$languageVersion" % metaVersion,
  "org.scala-lang" % "scala-reflect" % languageVersion,
  "org.scala-lang" % "scala-compiler" % languageVersion,
  "org.scala-lang" % "scala-library" % languageVersion,
  "org.apache.spark" %% "spark-hive" % "1.4.0",
  "commons-io" % "commons-io" % "2.4"
)


enablePlugins(JavaAppPackaging)

enablePlugins(DockerPlugin)

val JAVA_VERSION_MAJOR = 8

val JAVA_VERSION_MINOR = 45

val JAVA_VERSION_BUILD = 14

val JAVA_PACKAGE       = "server-jre"

val installAll = """apk-install bash curl ca-certificates nodejs python make gcc g++ libc-dev && npm install -g jshint && cd /tmp && curl -o glibc-2.21-r2.apk "https://circle-artifacts.com/gh/andyshinn/alpine-pkg-glibc/6/artifacts/0/home/ubuntu/alpine-pkg-glibc/packages/x86_64/glibc-2.21-r2.apk" && apk add --allow-untrusted glibc-2.21-r2.apk && curl -o glibc-bin-2.21-r2.apk "https://circle-artifacts.com/gh/andyshinn/alpine-pkg-glibc/6/artifacts/0/home/ubuntu/alpine-pkg-glibc/packages/x86_64/glibc-bin-2.21-r2.apk" && apk add --allow-untrusted glibc-bin-2.21-r2.apk && /usr/glibc/usr/bin/ldconfig /lib /usr/glibc/usr/lib && curl -L -O -H "Cookie: oraclelicense=accept-securebackup-cookie" -k "http://download.oracle.com/otn-pub/java/jdk/""" + JAVA_VERSION_MAJOR + """u""" + JAVA_VERSION_MINOR + """-b""" + JAVA_VERSION_BUILD + """/""" + JAVA_PACKAGE + """-""" + JAVA_VERSION_MAJOR + """u""" + JAVA_VERSION_MINOR + """-linux-x64.tar.gz" && gunzip -c """ + JAVA_PACKAGE + """-""" + JAVA_VERSION_MAJOR + """u""" + JAVA_VERSION_MINOR + """-linux-x64.tar.gz | tar -xf - && mv jdk1.""" + JAVA_VERSION_MAJOR + """.0_""" + JAVA_VERSION_MINOR + """/jre /jre && rm /jre/bin/jjs && rm /jre/bin/keytool && rm /jre/bin/orbd && rm /jre/bin/pack200 && rm /jre/bin/policytool && rm /jre/bin/rmid && rm /jre/bin/rmiregistry && rm /jre/bin/servertool && rm /jre/bin/tnameserv && rm /jre/bin/unpack200 && rm /jre/lib/ext/nashorn.jar && rm /jre/lib/jfr.jar && rm -rf /jre/lib/jfr && rm -rf /jre/lib/oblique-fonts && rm -rf /tmp/* /var/cache/apk/* && echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf """

dockerCommands := Seq(
  Cmd("FROM","gliderlabs/alpine:3.2"),
  Cmd("RUN", installAll),
  Cmd("ENV","JAVA_HOME /jre"),
  Cmd("ENV", "PATH ${PATH}:${JAVA_HOME}/bin"),
  Cmd("ENV", "ENV LANG C.UTF-8")
) ++ dockerCommands.value.filterNot{ case Cmd("FROM",_) => true; case _ => false}