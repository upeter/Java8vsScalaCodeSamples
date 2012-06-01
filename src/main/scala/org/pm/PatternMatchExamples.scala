package org.forloops

object PatternMatchExamples {
  def matchItAll(aParam: Any): Any = {
    aParam match {
      case 5 				=> "(C) It's mambo number ..."
      case x: Int 			=> "(K) An Integer: " + x
      case "Scala" 			=> "(R) A String with value Scala"
      case x: String if (x.length > 10) => "(E) long String with value " + x
      case List(_, 3, _*) 	=> "(P) Long list, the 2nd el is '3'."
      case head :: tail 	=> "(L) Head is " + head + " and tail " + tail
      case (firstEl, _) 	=> "(S) A two-tuple, 1. element being " + firstEl
      case Request("GET", path) => "(O) Matched case class with path " + path
      case MailRegexp(to, host) => "(E) Send mail to " + to + " via: " + host 
      case Some(s) 			=> "(T) Matched Some with value " + s
      case None 			=> "(Z) Matched None"
      case _ 				=> "(S) The default"
    }
  }

  val MailRegexp = """([^@]+)@(.+)""".r
  //REPLROCKS
  case class Request(method:String, path:List[String])
 
}