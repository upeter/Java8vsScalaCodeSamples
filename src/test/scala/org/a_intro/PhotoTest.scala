package org.a_intro

import java.net.{ MalformedURLException => MUE }
import org.junit.runner.RunWith
import org.specs2._
import org.specs2.runner.JUnitRunner
import org.slf4j.{ Logger => Slf4jLogger }
import org.slf4j.{ LoggerFactory => Slf4jLoggerFactory }
import java.lang.{ IllegalArgumentException => IAE }
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import java.net._
import java.io.File
import scala.xml._
import scala.util.control.Exception._

@RunWith(classOf[JUnitRunner])
class PhotoTest extends Specification {

  val validURL = "http://www.scala-lang.org/sites/default/files/newsflash_logo.png"
  val validFileURL = "file:///tmp/newsflash_logo.png"
  val invalidURL = "bla"
  val tmpDir = new File("/tmp/")

  "A Photo" should {
    "Use auxiliary constructor" in {
      todo
    }
    "Validate a given URL" in {
      todo
    }
    "return None if URL can be converted to file" in {
      todo
    }
    "return max and min rating" in {
      todo
    }
    "copy to tmp dir" in {
      todo
    }
    "copy all to tmp dir" in {
      todo
    }

  }

}

