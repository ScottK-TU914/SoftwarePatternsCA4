package com.bookshop.bookshop.command;

import org.springframework.stereotype.Component;

@Component
public class OrderInvoker {

    public void executeCommand(OrderCommand command) {
        command.execute();
    }
}
