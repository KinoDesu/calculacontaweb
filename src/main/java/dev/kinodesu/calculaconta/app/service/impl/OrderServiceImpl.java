package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import dev.kinodesu.calculaconta.app.service.OrderService;
import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.domain.usecase.OrderUseCase;
import dev.kinodesu.calculaconta.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderUseCase orderUseCase;
    private final UserUseCase userUseCase;

    @Override
    public void saveNewOrder(OrderRequestDTO orderRequestDTO) {

        List<User> userList =
                orderRequestDTO.getUserList().stream().map(userUseCase::getUserById).toList();

        double totalPrice = orderRequestDTO.getUnitPrice() * orderRequestDTO.getQuantity();
        double pricePerPerson = totalPrice / userList.size();

        Order order = Order.builder()
                .name(orderRequestDTO.getName())
                .unitPrice(orderRequestDTO.getUnitPrice())
                .quantity(orderRequestDTO.getQuantity())
                .totalPrice(totalPrice)
                .userList(userList)
                .pricePerPerson(pricePerPerson)
                .build();

        orderUseCase.saveNewOrder(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderUseCase.getAllOrders();
    }
}
