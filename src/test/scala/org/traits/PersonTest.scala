/*
 * Copyright 2011 Typesafe Inc.
 * 
 * This work is based on the original contribution of WeigleWilczek.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.traits


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

@RunWith(classOf[JUnitRunner])
class PersonSolutionTest extends Specification {

  "Person" should {
    
    "debug log in SPerson" in {
      val person = new Person("Sandra", 29) //with TEDLogCounter

      person.work()
      //person.counter must_== 2
      "A Long" must_== "A Long"

    }
    "Compare correctly" in {
      
      new Person("Sandra", 29) < new Person("Jack", 67)  must_== true

    }

  
  }
}

