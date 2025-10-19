package orderservice.mapper;

import orderservice.data.PayWay;
import orderservice.data.Reservation;
import orderservice.data.Status;
import orderservice.dto.OrderDto;

public class OrderMapper {
    public static Reservation mapOrderDtoToOrder(OrderDto order) {
        return Reservation.builder()
                .status(Status.COOKING)
                .payWay(PayWay.valueOf(order.getPaymentMethod()))
                .meals(order.getItems())
                .price(order.getTotal())
                .clientId(order.getUserId())
                .phoneNumber(order.getPhoneNumber())
                .comment(order.getComment())
                .address(order.getAddress())
                .build();
    }
}