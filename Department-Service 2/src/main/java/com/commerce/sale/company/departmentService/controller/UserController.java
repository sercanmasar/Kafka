package com.commerce.sale.company.departmentService.controller;

import com.commerce.sale.company.departmentService.dto.UserRequestDto;
import com.commerce.sale.company.departmentService.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    private void saveUser(@RequestBody UserRequestDto userRequestDto) throws JsonProcessingException {
        userService.saveUser(userRequestDto);
    }
}
