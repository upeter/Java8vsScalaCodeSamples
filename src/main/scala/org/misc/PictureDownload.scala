package org.par
import java.io.File
import java.net.URL

import org.apache.commons.io.FileUtils.copyURLToFile
import Thread._
import dispatch._
import dispatch.tagsoup.TagSoupHttp._
import util.measure
object PhotoScraper {

  def main(args: Array[String]) {
    measure {
      scrapeWallpapers("wallpapers1.html", "/tmp/")
    }
  }

  def scrapeWallpapers(fromPage: String, toDir: String) = {
    val imgURLs = fetchWallpaperImgNamesOfPage(fromPage)
    imgURLs.par.foreach(url => copyToDir(url, toDir))
  }

  private def fetchWallpaperImgNamesOfPage(page: String): Seq[URL] = {
    val xhtml =  Http(:/("www.boschfoto.nl") / "html" / "Wallpapers" / page as_tagsouped)
    val imgHrefs = xhtml \\ "a" \\ "@href"
    imgHrefs.map(node => node.text)
      .filter(href => href.endsWith("1025.jpg"))
      .map(href => new URL(href))
  }

  private def copyToDir(url: URL, toDir: String) = {
    println("Thread %s downloads %s to %s" format (currentThread.getName, url, toDir))
    copyURLToFile(url, new File(toDir, url.getFile.split("/").last))
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


