package org.par
import java.net._
import java.net.URL
import xml._
import dispatch.Http._
import dispatch._
import tagsoup.TagSoupHttp._
import java.io.FileOutputStream
import org.apache.commons.io.IOUtils
import java.io.InputStream
/**
 * Downloads all images on:
 * http://www.boschfoto.nl/html/Wallpapers/wallpapers1.html
 * and subsequent pages. Saves it in tmp dir.
 */

import util._
object PictureDownload {
  val executer = new Http with thread.Safety

  def main(args: Array[String]) {
    measure {
      scrapeWallpapers("wallpapers1.html", "/Users/urs/Desktop/tmp/")
    }
  }

  def scrapeWallpapers(fromPage: String, toDir: String) = {
    val imgNames = fetchWallpaperImgsOfPage(fromPage)
    imgNames.par.foreach(img => download(img, writeToDisk(toDir + img)))
  }

  def fetchWallpaperImgsOfPage(page: String): Seq[String] = {
    val xhtml = executer(:/("www.boschfoto.nl") / "html/Wallpapers" / page as_tagsouped)
    val imgHrefs = ((xhtml \\ "div").filter(node => node.attributes.exists(_.value.text == "rightcontainer"))) \\ "a" \\ "@href"
    val imgNames = imgHrefs.map(node => node.text)

      .filter(href => href.endsWith("jpg"))

      .map(href => href.split("/").last)
    imgNames
  }

  def download[T](img: String, func: InputStream => T): T = {
    executer(:/("www.boschfoto.nl") / "html/Wallpapers/wallpaperfotos" / img >> func)
  }

  def writeToDisk(path: String)(is: InputStream) = {
    val fos = new FileOutputStream(path)
    IOUtils.copy(is, fos)
    IOUtils.closeQuietly(fos)
  }

}

package object util {
  def measure[T](func: => T): T = {
    val start = System.nanoTime()
    val result = func
    val elapsed = System.nanoTime() - start
    println("The execution of this call took: %s ns".format(elapsed))
    result
  }
}


