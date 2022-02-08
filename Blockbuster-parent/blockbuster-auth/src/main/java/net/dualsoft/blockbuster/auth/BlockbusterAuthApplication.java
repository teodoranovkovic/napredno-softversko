package net.dualsoft.blockbuster.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.dualsoft.blockbuster.auth.recievers.Receiver;
import net.dualsoft.blockbuster.model.helper.RabbitUtil;
import oracle.jrockit.jfr.tools.ConCatRepository;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication(scanBasePackages = {"net.dualsoft"}, exclude = {SecurityAutoConfiguration.class})
public class BlockbusterAuthApplication extends SpringBootServletInitializer {

    UUID id = UUID.randomUUID();

    @Bean
    Queue rentalRequestQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-expires", 60000);
        return new Queue(RabbitUtil.rentalRequestQueueName + "-" + id, false, false, false, args);
    }

    @Bean
    TopicExchange rentalRequestExchange() {
        return new TopicExchange(RabbitUtil.rentalRequestTopicName);
    }

    @Bean
    Binding rentalRequestBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("auth.rr.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitUtil.rentalRequestQueueName + "-" + id);
        container.setMessageListener(listenerAdapter);
        return container;

    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    public static void main(String[] args) {
        SpringApplication.run(BlockbusterAuthApplication.class, args);
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
