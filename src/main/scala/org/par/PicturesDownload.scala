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
object PhotosScraper {

  protected val executer = new Http with thread.Safety

  def main(args: Array[String]) {
    measure {
      scrapeAllWallpapers("wallpapers1.html", "/tmp/")
    }
  }
//10919848000
//23748137000
  def scrapeAllWallpapers(startFromPage: String, toDir: String) = {
    val pages = fetchImagePages(startFromPage)
    pages.par.foreach { page =>
      val images = fetchWallpaperImgsOfPage(page)
      images.par.foreach { img =>
        download(img)(writeToDisk(toDir + img))
      }
    }
  }

  def download[T](img: String)(func: InputStream => T) = {
    executer(:/("www.boschfoto.nl") / "html/Wallpapers/wallpaperfotos" / img >> func)
  }

  def fetchImagePages(firstPage: String): Seq[String] = {
    doWithPageContent(firstPage) { html =>
      val hrefsInArrowContainerDiv = (html \\ "_" filter attributeValueEquals("pijlcontainer")) \\ "a" \\ "@href"
      val pageNames = hrefsInArrowContainerDiv.map(_.text).filter(_.endsWith("html")).map(_.split("/").last)
      pageNames
    }
  }

  def fetchWallpaperImgsOfPage(page: String): Seq[String] = {
    doWithPageContent(page) { html =>
      val hrefsInRightContainerDiv = (html \\ "_" filter attributeValueEquals("rightcontainer")) \\ "a" \\ "@href"
      val imgNames = hrefsInRightContainerDiv.map(_.text).filter(_.endsWith("jpg")).map(_.split("/").last)
      imgNames
    }
  }

  private def doWithPageContent[T](page: String)(func: NodeSeq => T) = {
    val html = executer(:/("www.boschfoto.nl") / "html/Wallpapers" / page as_tagsouped)
    func(html)
  }

  private def writeToDisk(path: String)(is: InputStream) = {
    println("writing " + path);
    val fos = new FileOutputStream(path)
    IOUtils.copy(is, fos)
    IOUtils.closeQuietly(fos)
  }

  private def attributeValueEquals(value: String)(node: Node) = {
    node.attributes.exists(_.value.text == value)
  }

    def measure[T](func: => T): T = {
    val start = System.nanoTime()
    val result = func
    val elapsed = System.nanoTime() - start
    println("The execution of this call took: %s ns".format(elapsed))
    result
  }


}