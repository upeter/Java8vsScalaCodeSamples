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
case class Photo(path:String, val sizeKb:Int, val ratings:List[Int] = Nil) extends Copyable {
  //type inference
  val url = new URL(path)

  //  def copyTo(target: File) = copyURLToFile(url, target)

  lazy val file: Option[File] = catching(classOf[Exception]) opt new File(url.toURI())

  def maxAndMinRate = if(ratings.isEmpty) None else Some((ratings.max, ratings.min))
}

case class TextDocument(url:URL) extends Copyable

trait Copyable extends Logger{
  val url: URL
  def copyTo(target: File):File = {
    val to = 
      if (target.isDirectory()) 
    	  new File(target, url.getFile.split("/").last) 
      else target
    debug("%s copies %s to %s" format (currentThread.getName, url, to))
    copyURLToFile(url, to)
    to
  }
}

trait Logger {
  self =>
  val LOG = Slf4jLoggerFactory.getLogger(self.getClass);
  def debug[T](msg: => T): Unit = if(LOG.isDebugEnabled()) LOG.debug(msg.toString())
  def info[T](msg: => T): Unit = if(LOG.isInfoEnabled()) LOG.info(msg.toString())
}

object Copyable {
  implicit def pimpCopyableCollection2[T <: Copyable](copyable: {def foreach[U](fun:T => U):Unit}) = new {
    def copyToDir(dir: File) =
      if (dir.isDirectory) copyable.foreach(p => p.copyTo(dir))
      else throw new IllegalArgumentException("Dir %s is not a directory." format dir.getAbsolutePath)
  }
  
  implicit def pimpCopyableCollection1(copyable: Seq[Copyable]) = new {
    def copyToDir(dir: File) =
      if (dir.isDirectory) copyable.foreach(p => p.copyTo(dir))
      else throw new IllegalArgumentException("Dir %s is not a directory." format dir.getAbsolutePath)
  }
  
}


 




