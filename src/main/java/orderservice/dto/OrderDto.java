package orderservice.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import orderservice.data.Meal;
import orderservice.data.PayWay;
import orderservice.data.Status;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderDto {
    private UUID clientId;
    private String address;
    private String phoneNumber;
    private String comment;
    private double price;
    private String declineReason;
    private UUID operatorId;
    private Status status;
    private PayWay payWay;
    private List<Meal> meals;
}
