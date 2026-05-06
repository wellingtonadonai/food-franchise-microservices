package com.wellington.order_service.service;

import com.wellington.order_service.dto.OrderItemRequestDTO;
import com.wellington.order_service.dto.OrderRequestDTO;
import com.wellington.order_service.entity.Order;
import com.wellington.order_service.entity.OrderItem;
import com.wellington.order_service.entity.Product;
import com.wellington.order_service.repository.OrderRepository;
import com.wellington.order_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order registrarPedido(OrderRequestDTO request) {
        Order novoPedido = new Order();
        novoPedido.setClienteNome(request.clienteNome());
        novoPedido.setStatus("PENDENTE");

        List<OrderItem> itensDoPedido = new ArrayList<>();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (OrderItemRequestDTO itemRequest : request.itens()) {

            Product produtoOriginal = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado no cache: " + itemRequest.productId()));

            OrderItem item = new OrderItem();
            item.setProductId(produtoOriginal.getId());
            item.setNome(produtoOriginal.getNome());
            item.setPrecoUnitario(produtoOriginal.getPreco());
            item.setQuantidade(itemRequest.quantidade());


            BigDecimal subtotalItem = produtoOriginal.getPreco()
                    .multiply(new BigDecimal(itemRequest.quantidade()));

            valorTotal = valorTotal.add(subtotalItem);
            itensDoPedido.add(item);
        }
        novoPedido.setItens(itensDoPedido);
        novoPedido.setValorTotal(valorTotal);
        return orderRepository.save(novoPedido);
    }
}