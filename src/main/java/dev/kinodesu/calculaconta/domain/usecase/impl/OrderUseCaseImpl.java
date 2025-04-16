package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.OrderDataProvider;
import dev.kinodesu.calculaconta.domain.UserDataProvider;
import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.domain.usecase.OrderUseCase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderDataProvider orderDataProvider;
    private final UserDataProvider userDataProvider;

    public OrderUseCaseImpl(OrderDataProvider orderDataProvider, UserDataProvider userDataProvider) {
        this.orderDataProvider = orderDataProvider;
        this.userDataProvider = userDataProvider;
    }

    @Override
    public void saveNewOrder(Order order) {
        log.info("save order");
        orderDataProvider.saveNewOrder(order);

        order.getUserList().forEach(user -> {
            double totalAmount =
                    Double.parseDouble(String.format("%.2f", user.getTotalAmount() + order.getPricePerPerson()));

            user.setTotalAmount(totalAmount);
            userDataProvider.updateTotalAmount(user.getUserId(), totalAmount);
        });

        orderDataProvider.sendNewOrderToClient(order);
    }

    @Override
    public List<Order> getAllOrdersByRoomCode(String roomCode) {
        return orderDataProvider.getAllOrdersByRoomCode(roomCode);
    }

    @Override
    public void deleteOrder(String orderId) {
        Order order = orderDataProvider.getOrderbyId(orderId);

        orderDataProvider.deleteOrder(order);

        order.getUserList().forEach(user -> {
            double totalAmount =
                    Double.parseDouble(String.format("%.2f", user.getTotalAmount() - order.getPricePerPerson()));

            user.setTotalAmount(totalAmount);
            userDataProvider.updateTotalAmount(user.getUserId(), totalAmount);
        });

        orderDataProvider.sendOrderDeletionToClient(order);
    }
}
