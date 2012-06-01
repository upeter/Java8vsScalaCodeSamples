package org.forloops

object ForLoopExamples {
  def largestPalindromOfTwoDigitNumber: Int = {
    val palindromes = for {
      n <- 100 to 999
      m <- n to 999
      product = n * m
      if product % 2 == 0
      if product.toString == product.toString.reverse
    } yield product
    palindromes.max
  }
}