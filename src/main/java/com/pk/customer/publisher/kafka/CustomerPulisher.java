package com.pk.customer.publisher.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.pk.customer.publisher.dto.CustomerDto;

@Component("customerProducer")
public class CustomerPulisher {

	@Value(value = "${kafkaserver}")
	private String boostrapServer;

	@Value(value = "${topic}")
	private String topic;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Bean
	public NewTopic createCustomerTopic() {
		return TopicBuilder.name(topic).build();
	}

	
	@Bean
	public ProducerFactory<String, Object> producerFactory() {

		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<String, Object>(config);
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	public String sendJsonToConsumer(CustomerDto customerDto) {
		SendResult<String, Object> meaasge = null;
		ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send(topic, customerDto);
		try {
			meaasge = listenableFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		kafkaTemplate.flush();
		return meaasge.getRecordMetadata().toString();

	}

}
