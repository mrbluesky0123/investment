package com.exam.core.base

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory

abstract class BaseObject {
    protected final val log = LoggerFactory.getLogger(this::class.java) as Logger
}