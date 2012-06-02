package org.g_par
import java.net._
import java.net.URL
import xml._
import dispatch.Http._
import dispatch._
import tagsoup.TagSoupHttp._
import java.io.FileOutputStream
import org.apache.commons.io.IOUtils
import java.io.InputStream
import scala.collection.parallel.ParSeq
import java.io.File
import org.a_intro._

/**
 * Downloads all images on:
 * http://www.boschfoto.nl/html/Wallpapers/wallpapers1.html
 * and subsequent pages. Saves it in tmp dir.
 */
trait TagSoup {
  def getAsTagSoup(pageUrl: String): NodeSeq = Http(url(pageUrl) as_tagsouped)
}
 
class PhotosScraper(initialPageURL: String, toDir: File) extends TagSoup {

  def scrapeAllWallpapers() = {
    val pages = fetchPhotoPages()
    pages.par.foreach { page =>
      val images = fetchWallpaperURLsOfPage(page)
      images.par.copyToDir(toDir)
    }
  }

  private[g_par] def fetchPhotoPages(): Set[String] = {
    val xhtml = getAsTagSoup(initialPageURL)
    val urls = ((xhtml \\ "div" \\ "a").filter(_.text.matches("""\d+""")) \\ "@href").map(_.text).toSet
    urls + initialPageURL
  }

  private[g_par] def fetchWallpaperURLsOfPage(page: String): Seq[Photo] = {
    val xhtml = getAsTagSoup(page)
    val imgHrefs = xhtml \\ "a" \\ "@href"
    imgHrefs.map(node => node.text)
      .filter(href => href.endsWith("1025.jpg"))
      .map(href => new Photo(href, -1))
  }
}

object PhotosScraper {

  def main(args: Array[String]) {
    val initialPageURL = "http://www.boschfoto.nl/html/Wallpapers/wallpapers1.html"
    val tmpFile = new File("/tmp/")
    val scraper = new PhotosScraper(initialPageURL, tmpFile);
    measure {
      scraper.scrapeAllWallpapers()
    }
  }

  def measure[T](func: => T): T = {
    val start = System.nanoTime()
    val result = func
    val elapsed = System.nanoTime() - start
    println("The execution of this call took: %s ns".format(elapsed))
    result
  }

}