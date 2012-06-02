package org.b_forloops

object ForLoopExamples extends App{
  def largestPalindromOfTwoDigitNumber: Int = {
    val palindromes = for {
      n <- 100 to 999
      m <- n to 999
      product = n * m
      if product.toString == product.toString.reverse
    } yield product
    palindromes.max
  }
  
  println(largestPalindromOfTwoDigitNumber)
}