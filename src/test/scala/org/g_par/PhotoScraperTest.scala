package org.g_par

import java.lang.{ IllegalArgumentException => IAE }
import org.junit.runner.RunWith
import org.specs2._
import org.specs2.runner.JUnitRunner
import org.slf4j.{ Logger => Slf4jLogger }
import org.slf4j.{ LoggerFactory => Slf4jLoggerFactory }
import java.lang.{ IllegalArgumentException => IAE }
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import java.net._
import java.io.File
import scala.xml._

@RunWith(classOf[JUnitRunner])
class PhotoScraperTest extends Specification {

  val photoPagesXhtml = <div>
                          <span class="spatie">-</span>
                          <span class="groen">1</span><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers2.html">2</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers3.html">3</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers4.html">4</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers5.html">5</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers6.html">6</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers7.html">7</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers8.html">8</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers9.html">9</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers10.html">10</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers11.html">11</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers12.html">12</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers13.html">13</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers14.html">14</a><span class="spatie">-</span>
                          <a href="http://www.boschfoto.nl/html/Wallpapers/wallpapers15.html">15</a><span class="spatie">-</span>
                        </div>

  val photoHrefsXhtml = <div id="rightcontainer">
                          <div id="wp1"><a href="wallpaperfotos/sierra.html" target="_blank"><img src="wallpaperfotos/tsierra.jpg" alt="wallpaper, berglandschap" border="0"/></a></div>
                          <div id="wp2"><a href="wallpaperfotos/meikever.html" target="_blank"><img src="wallpaperfotos/tmeikever.jpg" alt="achtergrond, meikever" border="0"/></a></div>
                          <div id="wp3"><a href="wallpaperfotos/aalscholver_1.html" target="_blank"><img src="wallpaperfotos/taalscholver_1.jpg" alt="vogelwallpaper, aalscholver" border="0"/></a></div>
                          <div id="wp4"><a href="wallpaperfotos/mantingerveld.html" target="_blank"><img src="wallpaperfotos/tmantingerveld.jpg" alt="wallpaper, landschap" border="0"/></a></div>
                          <div class="ondertitel1">
                            <a href="http://www.boschfoto.nl/html/Wallpapers/wallpaperfotos/sierra768.jpg">Download 1280 x 1025</a><br/>
                            <a href="http://www.boschfoto.nl/html/Wallpapers/wallpaperfotos/sierra1025.jpg">Wallpaper 1025 x 768</a><br/>
                          </div>
                          <div class="ondertitel2">
                            <a href="http://www.boschfoto.nl/html/Wallpapers/wallpaperfotos/meikever768.jpg">Download 1280 x 1025</a><br/>
                            <a href="http://www.boschfoto.nl/html/Wallpapers/wallpaperfotos/meikever1025.jpg">Wallpaper 1025 x 768</a><br/>
                          </div>
                          <div class="ondertitel2">
                            <a href="http://www.boschfoto.nl/html/Wallpapers/wallpaperfotos/aalscholver_1768.jpg">Download 1280 x 1025</a><br/>
                            <a href="http://www.boschfoto.nl/html/Wallpapers/wallpaperfotos/aalscholver_11025.jpg">Wallpaper 1025 x 768</a><br/>
                          </div>
                          <div class="ondertitel2">
                            <a href="http://www.boschfoto.nl/html/Wallpapers/wallpaperfotos/mantingerveld768.jpg">Download 1280 x 1025</a><br/>
                            <a href="http://www.boschfoto.nl/html/Wallpapers/wallpaperfotos/mantingerveld1025.jpg">Wallpaper 1025 x 768</a><br/>
                          </div>
                        </div>

  trait TagSoupDummy extends TagSoup {
    val xhtml: NodeSeq
    override def getAsTagSoup(pageUrl: String) = xhtml
  }
  val initialPageURL = "http://www.boschfoto.nl/html/Wallpapers/wallpapers1.html"
  val tmpFile = new File("/tmp/")

  "PhotoDownloader" should {
    "fetch all photo pages" in {
      val scraper = new PhotosScraper(initialPageURL, tmpFile) with TagSoupDummy { val xhtml = photoPagesXhtml };
      val pages = scraper.fetchPhotoPages()
      val expected = (1 to 15) map (i => "http://www.boschfoto.nl/html/Wallpapers/wallpapers%s.html" format (i))
      pages must beEqualTo(expected.toSet)
    }
    "fetch wallpaper urls of a particular page" in {
      val scraper = new PhotosScraper(initialPageURL, tmpFile) with TagSoupDummy { val xhtml = photoHrefsXhtml };
      val photos = scraper.fetchWallpaperURLsOfPage(initialPageURL)
      photos.size must_== 4
    }
  }

}

