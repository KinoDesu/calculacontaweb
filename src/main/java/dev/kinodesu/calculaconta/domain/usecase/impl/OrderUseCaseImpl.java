package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.domain.usecase.OrderUseCase;
import dev.kinodesu.calculaconta.infra.dataprovider.OrderDataProvider;
import dev.kinodesu.calculaconta.infra.dataprovider.UserDataProvider;
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
    }

    @Override
    public List<Order> getAllOrdersByRoomCode(String roomCode) {
        return orderDataProvider.getAllOrdersByRoomCode(roomCode);
    }
}
