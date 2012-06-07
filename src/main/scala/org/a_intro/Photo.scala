package org.a_intro
import java.net.URI
import java.io.OutputStream


import org.apache.commons.io.IOUtils._
import java.io.File
import java.io.FileOutputStream
import org.apache.commons.io.FileUtils._

import java.net.URL
import java.util.Date
import scala.util.control.Exception.catching
import scala.collection.parallel.ParSeq
import org.slf4j.{ Logger => Slf4jLogger }
import org.slf4j.{ LoggerFactory => Slf4jLoggerFactory }

/**
 * - Class definition
 * - Default arguments
 * - Convert String to URL to check whether URL is ok
 * - Add Method file with Option
 * - Show tuple method, maxAndMinRate
 * - Extract copy functionality to trait
 * - Add Method copyTo(...) (Java interopability)
 * - Add Method copyToDir(...) implicits
 * - 
 */
case class Photo(path:String, val sizeKb:Int, val ratings:List[Int] = Nil) {
  val url = new URL(path)
}