package org.a_intro

import java.net.{ MalformedURLException => MUE}
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
      Photo(validURL, 23, List(2,3,4)).ratings must beEqualTo(List(2,3,4))
      Photo(validURL, 23).ratings must beEmpty
    }
    "Validate a given URL" in {
      Photo(validURL, 23, Nil) must not(throwA[Exception])
      Photo(invalidURL, 23, Nil) must throwA[MUE]
    }
    "return None if URL can be converted to file" in {
      Photo(validFileURL, 23, Nil).file must beSome
      Photo(validURL, 23, Nil).file must beNone
    }
    "return max and min rating" in {
      val p =  Photo(validFileURL, 23, List(9, 5, 6, 3, 8))
      p.maxAndMinRate must_== (9, 3)
    }
    "copy to tmp dir" in {
      val p = Photo(validURL, 23)
      val to = p.copyTo(tmpDir)
      to exists
    }
    "copy all to tmp dir" in {
      List(Photo(validURL, 23), Photo(validFileURL, 45)).copyToDir(tmpDir)
      tmpDir.list.filter(_.endsWith("png")).size must beGreaterThan(0)
    }

  }

}

