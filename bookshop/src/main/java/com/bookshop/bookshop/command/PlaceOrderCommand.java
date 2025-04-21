package com.bookshop.bookshop.command;

import com.bookshop.bookshop.services.OrderService;

public class PlaceOrderCommand implements OrderCommand {

    private final OrderService orderService;
    private final Long bookId;
    private final String username;

    public PlaceOrderCommand(OrderService orderService, Long bookId, String username) {
        this.orderService = orderService;
        this.bookId = bookId;
        this.username = username;
    }

    @Override
    public void execute() {
        orderService.placeOrder(bookId, username);
    }
}
