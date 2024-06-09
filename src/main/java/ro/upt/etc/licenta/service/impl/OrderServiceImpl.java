package ro.upt.etc.licenta.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.OrderRepository;
import ro.upt.etc.licenta.repository.dto.OrderRequestDTO;
import ro.upt.etc.licenta.repository.dto.OrderResponseDTO;
import ro.upt.etc.licenta.repository.entity.Order;
import ro.upt.etc.licenta.service.OrderService;
import ro.upt.etc.licenta.service.exception.ResourceNotFoundException;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = modelMapper.map(orderRequestDTO, Order.class);
        order = orderRepository.save(order);
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderRequestDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        modelMapper.map(orderRequestDTO, existingOrder);
        existingOrder = orderRepository.save(existingOrder);
        return modelMapper.map(existingOrder, OrderResponseDTO.class);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        orderRepository.delete(order);
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    @Override
    public List<OrderResponseDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(order -> modelMapper.map(order, OrderResponseDTO.class))
                .collect(Collectors.toList());
    }
}
