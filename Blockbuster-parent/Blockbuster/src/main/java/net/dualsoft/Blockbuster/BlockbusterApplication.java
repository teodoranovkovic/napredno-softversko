package net.dualsoft.Blockbuster;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.dualsoft.Blockbuster.models.RentalRequestResponseReceiver;
import net.dualsoft.blockbuster.model.helper.RabbitUtil;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication(scanBasePackages = {"net.dualsoft"})
public class BlockbusterApplication extends SpringBootServletInitializer {

    UUID id = UUID.randomUUID();

    @Bean
    Queue rentalRequestResponseQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-expires", 60000);
        return new Queue(RabbitUtil.rentalRequestResponseQueueName + "-" + id, false,false,false,args);
    }

    @Bean
    TopicExchange rentalRequestResponseExchange() {
        return new TopicExchange(RabbitUtil.rentalRequestResponseTopicName);
    }

    @Bean
    Binding rentalRequestResponseBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("core.rr.response.#");
    }

    @Bean
    SimpleMessageListenerContainer rentalRequestResponseContainer(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitUtil.rentalRequestResponseQueueName + "-" + id);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RentalRequestResponseReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(BlockbusterApplication.class, args);
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("praktikant");
        dataSource.setPassword("praktikant");
        dataSource.setUrl("jdbc:postgresql://omega.dualsoft.net:5432/praksa");
        return dataSource;
    }
}
