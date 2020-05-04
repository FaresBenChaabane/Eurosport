package fr.benchaabane.commons.tools

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

fun Long.toLocalDateTime(): LocalDateTime = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.UTC)

