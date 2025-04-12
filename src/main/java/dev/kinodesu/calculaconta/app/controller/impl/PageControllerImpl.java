package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.PageController;
import dev.kinodesu.calculaconta.app.service.RoomService;
import dev.kinodesu.calculaconta.app.service.UserService;
import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;

@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
public class PageControllerImpl implements PageController {

    private final RoomService roomService;
    private final UserService userService;

    @Override
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @Override
    @GetMapping("/create")
    public String createRoom(Model model) {
        model.addAttribute("c", 1);
        return "pages/signup";
    }

    @Override
    @GetMapping("/enter")
    public String enterRoom(Model model) {
        model.addAttribute("c", 0);
        return "pages/signup";
    }

    @Override
    @GetMapping("/room/{code}")
    public String roomHome(@PathVariable String code,
                           @CookieValue(value = "userId", required = false) String userId,
                           Model model) {

        try {
            Room room = roomService.findByCode(code);

            if (userId == null || userId.isBlank() || userId.equalsIgnoreCase("undefined")) {
                return "redirect:/";
            }

            User user = userService.getUserById(userId);

            if (!user.getRoom().getRoomId().equals(room.getRoomId())) {

                return "redirect:/";
            }

            model.addAttribute("roomCode", code);
            return "pages/home";
        } catch (NoSuchElementException ex) {
            return "redirect:/";
        }
    }

    @Override
    @GetMapping("order/{roomCode}")
    public String makeOrder(@PathVariable String roomCode,
                            @CookieValue(value = "userId", required = false) String userId, Model model) {

        try {
            Room room = roomService.findByCode(roomCode);

            if (userId == null || userId.isBlank()) {
                return "redirect:/";
            }

            User user = userService.getUserById(userId);

            if (!user.getRoom().getRoomId().equals(room.getRoomId())) {

                return "redirect:/";
            }

            model.addAttribute("roomCode", roomCode);
            return "pages/order";
        } catch (NoSuchElementException ex) {
            return "redirect:/";
        }
    }

    @Override
    @GetMapping("billing/{roomCode}")
    public String payBill(@PathVariable String roomCode,
                          @CookieValue(value = "userId", required = false) String userId,
                          Model model) {

        try {
            Room room = roomService.findByCode(roomCode);

            if (userId == null || userId.isBlank()) {
                return "redirect:/";
            }

            User user = userService.getUserById(userId);

            if (!user.getRoom().getRoomId().equals(room.getRoomId())) {

                return "redirect:/";
            }

            model.addAttribute("roomCode", roomCode);
            return "pages/payment";
        } catch (NoSuchElementException ex) {
            return "redirect:/";
        }
    }
}
