package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

import java.util.Objects;

public class DefendCommand implements ActionCommand {
    private final ArenaFighter target;
    private final double dodgeBoost;

    public DefendCommand(ArenaFighter target, double dodgeBoost) {
        this.target = Objects.requireNonNull(target, "targer");
        this.dodgeBoost = dodgeBoost;
    }

    @Override
    public void execute() {
        target.modifyDodgeChance(dodgeBoost);
        System.out.println("[Defend] " + target.getName() + " increases dodge by "
                + String.format("%.3f", dodgeBoost) + ". New chance="
                + String.format("%.3f", target.getDodgeChance()));
    }

    @Override
    public void undo() {
        target.modifyDodgeChance(-dodgeBoost);
        System.out.println("[Defend:undo] " + target.getName() + " removes dodge boost "
                + String.format("%.3f", dodgeBoost) + ". New chance="
                + String.format("%.3f", target.getDodgeChance()));
    }

    @Override
    public String getDescription() {
        String sign = (dodgeBoost >= 0) ? "+" : "";
        return "Defend (dodge boost: " + sign + String.format("%2f", dodgeBoost) + ")";
    }
}
