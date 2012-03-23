package org.traits

 class Person(val name:String, val age:Int) extends Ordered[Person] {
  
  	def work() = {
  	  //debug("work called")
  	  //debug("just did some serious work")
  	}
  	
  	def compare(p:Person) =  age - p.age
  	
}

