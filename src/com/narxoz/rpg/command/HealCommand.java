package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

import java.util.Objects;

public class HealCommand implements ActionCommand {
    private final ArenaFighter target;
    private final int healAmount;
    private int actualHealApplied;

    public HealCommand(ArenaFighter target, int healAmount) {
        this.target = Objects.requireNonNull(target, "target");
        this.healAmount = Math.max(0, healAmount);
    }

    @Override
    public void execute() {
        if(healAmount <= 0){
            actualHealApplied = 0;
            return;
        }
        if(target.getHealPotions() <= 0){
            actualHealApplied = 0;
            System.out.println("[Heal] No potions left for " + target.getName());
            return;
        }
        int before = target.getHealth();
        target.heal(healAmount);
        int after = target.getHealth();
        actualHealApplied = Math.max(0, after - before);

        System.out.println("[Heal] Healed " + target.getName() + " for " + actualHealApplied
                + " HP. HP=" + target.getHealth() + ", potions=" + target.getHealPotions());
    }

    @Override
    public void undo() {
        if(actualHealApplied <= 0){
            return;
        }
        target.takeDamage(actualHealApplied);
        System.out.println("[Heal:undo] Removed " + actualHealApplied + " HP from " + target.getName()
                + ". HP=" + target.getHealth());
    }

    @Override
    public String getDescription() {
        return "Heal for " + healAmount + " HP";
    }
}
