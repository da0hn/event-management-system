package br.com.event.management.system.email.service.application.configuration

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitmqConfiguration {

  @Bean
  fun objectMapper(): ObjectMapper {
    val objectMapper = ObjectMapper()
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS)
    objectMapper.registerModule(JavaTimeModule())
      .registerModule(ParameterNamesModule())
      .registerModule(Jdk8Module())
    return objectMapper
  }

  @Bean
  fun jsonMessageConverter(objectMapper: ObjectMapper?): MessageConverter {
    return Jackson2JsonMessageConverter(objectMapper!!)
  }

}
