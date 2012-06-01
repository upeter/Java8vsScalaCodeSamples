package org.forloops
import java.io.File
import org.intro.Photo

object FunctionsExamples {

  object PhotoHandlerRecursive {
    val formats = List("png", "jpg", "jpeg", "gif")

    /**
     * Functional
     */
    def doWithPhotos(file: File, callback: Photo => Unit): Unit =
      if (isPhoto(file)) callback(Photo(file.getAbsolutePath, -1))
      else Option(file.listFiles).map(p => p.foreach(f => doWithPhotos(f, callback)))

    /**
     * Imperative
     */
    private def load(file: File, collector: List[Photo]): List[Photo] = {
      if (isPhoto(file)) Photo(file.getAbsolutePath, -1) :: collector
      else {
        val other = Option(file.listFiles).map(_.toList).getOrElse(Nil)
        other.flatMap(f => load(f, collector))
      }
    }

    private def isPhoto(file: File) = formats.exists(ext => file.getPath.endsWith(ext))

  }

}