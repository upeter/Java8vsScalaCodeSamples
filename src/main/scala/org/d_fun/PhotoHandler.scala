package org.d_fun
import java.io.File

import scala.collection.JavaConversions._

import org.a_intro.Photo;

object PhotoHandler extends App {
  private val formats = List("png", "jpg", "jpeg", "gif")

   def functionalStyleFilter() = {
   val dir = new File("/tmp")
   val fileFilter = (f:File) => isPhoto(f)
   dir.listFiles().filter(f => isPhoto(f))
  }
    
  def isPhoto(file:File) = 
    formats.exists((ext:String) => file.getPath.endsWith(ext))
  
 
}
object PhotoHandler_OLD {
  private val formats = List("png", "jpg", "jpeg", "gif")

  def doWithPhotos(dir: File, callback: Photo => Any): Unit =
    if (dir.isDirectory())
      Option(dir.listFiles).map { files =>
        files.filter(isPhoto).map(f => Photo(f.toURI.toString, -1))
          .foreach(p => callback(p))
      }

  def doWithPhotos2(dir: File, callback: Photo => Any): Unit =
    if (dir.isDirectory()) {
      for (file <- dir.listFiles(); if isPhoto(file)) {
        file.canExecute()
        callback(Photo(file.toURI.toString, -1))
      }
    }

  private def isPhoto(file: File) =
    formats.exists(ext => file.getPath.endsWith(ext))

  //=====================
  //callbacks
  //=====================

  val printCallback = (p: Photo) => print(p)
  val copyCallback = (p: Photo) => p.copyTo(new File("/tmp/tmp2"))
  val deleteCallback = (p: Photo) => p.file.get.delete

}