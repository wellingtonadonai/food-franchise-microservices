package com.wellington.order_service.controller;

import com.wellington.order_service.dto.OrderRequestDTO;
import com.wellington.order_service.entity.Order;
import com.wellington.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> fecharPedido(@RequestBody OrderRequestDTO request) {
        Order novoPedido = orderService.registrarPedido(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }
}