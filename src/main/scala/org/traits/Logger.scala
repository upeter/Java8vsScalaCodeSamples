package org.traits

import org.slf4j.{ Logger => Slf4jLogger }
import org.slf4j.{ LoggerFactory => Slf4jLoggerFactory }


trait TEDLogger {
  def debug[T](msg: => T): Unit
}