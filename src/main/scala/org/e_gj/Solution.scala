package org.e_gj
/**
 * http://code.google.com/codejam/contest/1460488/dashboard
 */
object Solution {
  val gc = "ejp mysljylc kd kxveddknmc re jsicpdrysi rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd de kr kd eoya kw aej tysr re ujdr lkgc jv" filterNot (_ == ' ')
  val en = "our language is impossible to understand there are twenty six factorial possibilities so it is okay if you want to just give up" filterNot (_ == ' ')
  val mapping = gc.zip(en).toSet + ('z' -> 'q') + ('q' -> 'z') + (' ' -> ' ') toMap
  val lines = scala.io.Source.fromFile(new java.io.File("A-small-practice-1.in")).getLines.toList.tail
  val translated = lines.map(line => line.map(c => mapping.getOrElse(c, '?')))
  translated foreach println

  //helper:
  val distinct = gc.zip(en).toSet
  distinct.groupBy(_._1).values.forall(_.size == 1)
  val partAbc = distinct.map(_._1).toList.sorted.mkString
  val abc = 97 to 122 map (_.toChar)
  abc.filterNot(a => partAbc.contains(a))

}