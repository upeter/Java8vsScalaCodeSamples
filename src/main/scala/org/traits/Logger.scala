package org.traits

import org.slf4j.{ Logger => Slf4jLogger }
import org.slf4j.{ LoggerFactory => Slf4jLoggerFactory }
import org.slf4j.LoggerFactory


trait Logger {
  self =>
  val LOG = LoggerFactory.getLogger(self.getClass);
  def debug[T](msg: => T): Unit = if(LOG.isDebugEnabled()) LOG.debug(msg.toString())
  def info[T](msg: => T): Unit = if(LOG.isInfoEnabled()) LOG.info(msg.toString())
}