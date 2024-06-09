package ro.upt.etc.licenta.service;

import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.dto.OrderRequestDTO;
import ro.upt.etc.licenta.repository.dto.OrderResponseDTO;

import java.util.List;

@Service
public interface OrderService {
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO);
    void deleteOrder(Long id);
    OrderResponseDTO getOrderById(Long id);
    List<OrderResponseDTO> getOrdersByUserId(Long userId);
}
