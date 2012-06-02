package org.f_collections
import org.a_intro.Photo;

object CollectionExamples {
  val dataset = List(
    Photo("file:///tmp/sunset.png", 200, List(7, 4, 5, 6, 4, 5, 6)),
    Photo("file:///tmp/mountain.png", 12302, List(9)),
    Photo("file:///tmp/duke.png", 50, List(6, 9, 7)));

  val U = dataset.map(p => p.copy(ratings = p.ratings.sorted.reverse))
    .sortBy(p => p.url.toString.length)

  val P = dataset.exists(p => p.sizeKb > 1000)

  val N = dataset.groupBy(p => p.ratings.sum / p.ratings.size)

  val F = dataset.filter(p => p.sizeKb > 1000)
    .map(p => (p.url.toString, p.sizeKb))
    .toMap


}