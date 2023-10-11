package com.svj.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svj.dto.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value(value="${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value="${spring.kafka.consumer.group-id}")
    private String groupId;

    @Autowired
    private ObjectMapper objectMapper;

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> orderKafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//
//        Map<String, String> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory(props, new StringDeserializer(), new JsonDeserializer<>(String.class, objectMapper));
//
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }
}
