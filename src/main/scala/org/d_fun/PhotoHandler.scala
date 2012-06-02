package org.d_fun
import java.io.File

import scala.collection.JavaConversions._

import org.a_intro.Photo;

class PhotoHandler {
  private val formats = List("png", "jpg", "jpeg", "gif")

  def doWithPhotos(dir: File, callback: Photo => Any): Unit =
    if (dir.isDirectory())
      Option(dir.listFiles).map(files =>
        files.filter(isPhoto)
          .foreach(f => callback(Photo(f.toURI.toString, -1))))

  private def isPhoto(file: File) = 
    formats.exists(ext => file.getPath.endsWith(ext))

  //=====================
  //callbacks
  //=====================

  val printCallback = (p: Photo) => print(p)
  val copyCallback = (p: Photo) => p.copyTo(new File("/tmp/tmp2"))
  val deleteCallback = (p: Photo) => p.file.get.delete

}