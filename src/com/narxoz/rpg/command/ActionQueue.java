package com.narxoz.rpg.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActionQueue {
    private final List<ActionCommand> queue = new ArrayList<>();

    public void enqueue(ActionCommand cmd) {
        queue.add(Objects.requireNonNull(cmd, "cmd"));
    }

    public void undoLast() {
        if(queue.isEmpty()){
            return;
        }
        queue.remove(queue.size() - 1);
    }

    public void executeAll() {
        for(ActionCommand cmd : queue){
            cmd.execute();
        }
        queue.clear();
    }

    public List<String> getCommandDescriptions() {
        List<String> description = new ArrayList<>(queue.size());
        for(ActionCommand cmd : queue){
            description.add(cmd.getDescription());
        }
        return description;
    }
}
