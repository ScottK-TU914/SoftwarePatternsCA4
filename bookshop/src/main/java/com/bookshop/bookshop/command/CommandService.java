package com.bookshop.bookshop.command;

import org.springframework.stereotype.Service;
//This class acts as an invoker in the Command Pattern
@Service
public class CommandService {

    public void execute(OrderCommand command) {
    	
        //calls the execute method on the given command object
        command.execute();
    }
}
