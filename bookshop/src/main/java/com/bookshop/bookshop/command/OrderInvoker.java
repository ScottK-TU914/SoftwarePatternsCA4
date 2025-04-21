package com.bookshop.bookshop.command;

public class OrderInvoker {
    private OrderCommand command;

    public void setCommand(OrderCommand command) {
        this.command = command;
    }

    public void placeOrder() {
        if (command != null) {
            command.execute();
        }
    }
}
