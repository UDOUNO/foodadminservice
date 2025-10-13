package orderservice.service;

import lombok.RequiredArgsConstructor;
import orderservice.data.Meal;
import orderservice.data.Order;
import orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BinToOrderService {
    private final OrderRepository orderRepository;
    private static final Logger logger = LoggerFactory.getLogger(BinToOrderService.class);

    @KafkaListener(topics = "my-topic", groupId = "order")
    public void consumeWithHeaders(String message,
                                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                   @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                   @Header(KafkaHeaders.OFFSET) long offset) {
        logger.info("Received message from topic: {}, partition: {}, offset: {}: {}",
                topic, partition, offset, message);
    }

    @KafkaListener(topics = "order-topic", groupId = "order")
    public void consumeOrder(Order order) {
        logger.info("Received order: {}", order);
        processOrder(order);
    }


    private void processOrder(Order order) {
        order.setPrice(priceCounter(order));
        orderRepository.save(order);
    }

    private Double priceCounter(Order order) {
        double price = 0.0;
        for (Meal meal : order.getMeals()) {
            price += meal.getPrice();
        }
        return price;
    }
}
