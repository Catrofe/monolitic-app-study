package com.br.iceberg.config.handler

    import org.springframework.context.MessageSource
    import org.springframework.context.annotation.Bean
    import org.springframework.context.annotation.Configuration
    import org.springframework.context.support.ReloadableResourceBundleMessageSource
    import java.nio.charset.StandardCharsets
    import java.util.Locale

    @Configuration
    class MessageConfig {

        @Bean
        fun messageSource(): MessageSource {
            val messageSource = ReloadableResourceBundleMessageSource()
            messageSource.setBasename("classpath:messages")
            messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name())
            messageSource.setUseCodeAsDefaultMessage(true)
            messageSource.setFallbackToSystemLocale(false)
            messageSource.setDefaultLocale(Locale.forLanguageTag("pt-BR"))
            return messageSource
        }
    }