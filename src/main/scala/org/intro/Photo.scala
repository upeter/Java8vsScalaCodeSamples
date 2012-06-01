package org.intro
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
import org.traits.Logger


/**
 * - Class definition
 * - Default arguments
 * - Convert URI to URL to check whether URL is ok
 * - Add Method copyTo(...) (Java interopability)
 * - Add Method file with Option
 * - Show tuple method, maxAndMinRate
 * - Extract copy functionality to trait
 * - 
 */
case class Photo(path: String, val sizeKb: Int, val ratings: List[Int] = Nil) extends Copyable {
  //type inference
  override val url = new URI(path).toURL
  require( List("png", "jpg", "jpeg", "gif").exists(t => url.getFile.endsWith(t)), "Choose a valid image format")

  //  def copyTo(target: File) = {
  //    copyURLToFile(url, target)
  //  }

  lazy val file: Option[File] = catching(classOf[Exception]) opt new File(path)

  def maxAndMinRate = (ratings.max, ratings.min)
  
}

trait Copyable extends Logger{
  val url: URL
  def copyTo(target: File):File = {
    val to = if (target.isDirectory()) new File(target, url.getFile.split("/").last) else target
    debug("Thread %s copies %s to %s" format (currentThread.getName, url, to))
    copyURLToFile(url, to)
    to
  }
}

object Copyable {
  implicit def pimpCopyableCollection[T <: Copyable](copyable: {def foreach[U](fun:T => U):Unit}) = new {
    def copyToDir(dir: File) =
      if (dir.isDirectory) copyable.foreach(p => p.copyTo(dir))
      else throw new IllegalArgumentException("Dir %s is not a directory." format dir.getAbsolutePath)
  }
}

