package ro.upt.etc.licenta.repository.dto;

import lombok.Data;
import ro.upt.etc.licenta.repository.entity.Order;
import ro.upt.etc.licenta.repository.entity.OrderItem;
import ro.upt.etc.licenta.repository.entity.OrderState;

import java.util.Date;
import java.util.List;

@Data
public class OrderRequestDTO {
    private Date dateFrom;
    private Date dateTo;
    private Long userId;
    private OrderState orderState;
    private List<OrderItemResponseDTO> orderItems;
}
