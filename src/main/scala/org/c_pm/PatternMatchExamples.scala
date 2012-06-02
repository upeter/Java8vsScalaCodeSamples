package org.c_pm

object PatternMatchExamples {

  val MailRgex = """([^@]+)@(.+)""".r

  def matchItAll(aParam: Any): Any = {
    aParam match {
      case 5 		  	  => "(E) Give me 5"
	  case x: Int 		  => "(P) An Integer: " + x
	  case "Scala" 		  => "(C) A String with value Scala"
	  case s: String if (s.length > 10) => "(O) I'm long " + s
	  case List(_, 3, _*) => "(L) Long list, the 2nd el is '3'."
	  case head :: tail   => "(R) Head  " + head + " & tail " + tail
	  case MailRgex(to, host) => "(O) mail for " + to + " @ " + host 
	  case _ 			  => "(L) The default"
    }
  }
 

}