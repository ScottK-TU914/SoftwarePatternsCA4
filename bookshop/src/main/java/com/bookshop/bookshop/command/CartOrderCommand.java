package com.bookshop.bookshop.command;

import com.bookshop.bookshop.services.OrderService;
//this class features the Command pattern.
public class CartOrderCommand implements OrderCommand {

    private final OrderService orderService; //service that will carry out the order
    private final Long bookId; //id of book being ordered
    private final String username; //username of buyer 
    private final int quantity; //amoutn of books being bought

    public CartOrderCommand(OrderService orderService, Long bookId, String username, int quantity) {
        this.orderService = orderService;
        this.bookId = bookId;
        this.username = username;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        //calls the service method with the given details
        orderService.placeOrder(bookId, username, quantity);
    }
}
