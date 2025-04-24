package com.bookshop.bookshop.command;

import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public void execute(OrderCommand command) {
        command.execute();
    }
}
