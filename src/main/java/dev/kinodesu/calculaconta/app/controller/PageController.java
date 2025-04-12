package dev.kinodesu.calculaconta.app.controller;

import org.springframework.ui.Model;

public interface PageController {
    String index();

    String createRoom(Model model);

    String enterRoom(Model model);

    String roomHome(String code, String userId, Model model);

    String makeOrder(String code, String userId, Model model);

    String payBill(String code, String userId, Model model);
}
