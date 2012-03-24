package misc
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
object PicturesDownload {

  protected val executer = new Http with thread.Safety

  def main(args: Array[String]) {
    measure {
      scrapeAllWallpapers("wallpapers1.html", "/tmp/")
    }
  }

  def scrapeAllWallpapers(startFromPage: String, toDir: String) = {
    val pages = getImagePages(startFromPage)
    pages.par.foreach { page =>
      val images = getWallpaperImgsOfPage(page)
      images.par.foreach { img =>
        download(img)(writeToDisk(toDir + img))
      }
    }
  }

  def download[T](img: String)(func: InputStream => T) = {
    executer(:/("www.boschfoto.nl") / "html/Wallpapers/wallpaperfotos" / img >> func)
  }

  def getImagePages(firstPage: String): Seq[String] = {
    doWithPageContent(firstPage) { html =>
      val hrefsInArrowContainerDiv = (html \\ "_" filter attributeValueEquals("pijlcontainer")) \\ "a" \\ "@href"
      val pageNames = hrefsInArrowContainerDiv.map(_.text).filter(_.endsWith("html")).map(_.split("/").last)
      pageNames
    }
  }

  def getWallpaperImgsOfPage(page: String): Seq[String] = {
    doWithPageContent(page) { html =>
      val hrefsInRightContainerDiv = (html \\ "_" filter attributeValueEquals("rightcontainer")) \\ "a" \\ "@href"
      val imgNames = hrefsInRightContainerDiv.map(_.text).filter(_.endsWith("jpg")).map(_.split("/").last)
      imgNames
    }
  }

  def measure[T](func: => T): T = {
    val start = System.nanoTime()
    val result = func
    val elapsed = System.nanoTime() - start
    println("The execution of this call took: %s ns".format(elapsed))
    result
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

}