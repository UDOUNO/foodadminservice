package orderservice.mapper;

import orderservice.data.Reservation;
import orderservice.dto.OrderDto;

public class OrderMapper {
    public static Reservation mapOrderDtoToOrder(OrderDto order) {
        return Reservation.builder()
                .operatorId(order.getOperatorId())
                .status(order.getStatus())
                .payWay(order.getPayWay())
                .meals(order.getMeals())
                .clientId(order.getClientId())
                .phoneNumber(order.getPhoneNumber())
                .comment(order.getComment())
                .declineReason(order.getDeclineReason())
                .price(order.getPrice())
                .address(order.getAddress())
                .build();
    }
}