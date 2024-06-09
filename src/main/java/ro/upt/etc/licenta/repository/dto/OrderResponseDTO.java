package ro.upt.etc.licenta.repository.dto;

import lombok.Data;
import ro.upt.etc.licenta.repository.entity.OrderItem;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private Date date;
    private String username;
    private List<OrderItemResponseDTO> orderItems;
    private String state;
}
