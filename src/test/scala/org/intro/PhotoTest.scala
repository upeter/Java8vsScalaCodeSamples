/*
 * Copyright 2011 Typesafe Inc.
 * 
 * This work is based on the original contribution of WeigleWilczek.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intro

import java.lang.{ IllegalArgumentException => IAE }
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
  val validFileURI = "file:///tmp/zonsondergang_HDR1025.jpg"
  val invalidURI = "bla"
  val tmpDir = new File("/tmp/")

  "A Photo" should {
    "Validate a given URI" in {
      new Photo(validURL, 23, Nil) must not(throwA[Exception])
      new Photo(invalidURI, 23, Nil) must throwA[IAE]
    }
    "return File if present" in {
      new Photo(validFileURI, 23, Nil).file must beSome
      new Photo(validURL, 23, Nil).file must beNone
    }
    "return max and min rating" in {
      val p = new Photo(validFileURI, 23, List(9, 5, 6, 3, 8))
      p.maxAndMinRate must_== (9, 3)
    }
    "copy to tmp dir" in {
      val p = new Photo(validURL, 23)
  val tmpDir2 = new File("/Users/urs/Desktop/tmp")
//      new PhotoHandler().doWithPhotos(tmpDir2, p => println(p))
      //      val to = p.copyTo(tmpDir)
//      to exists
      todo
    }

  }

}
