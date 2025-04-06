package com.br.iceberg.config.handler

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageSource: MessageSource) {

    fun getMessage(code: String, args: Array<Any?>? = null): String {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale())
    }
}