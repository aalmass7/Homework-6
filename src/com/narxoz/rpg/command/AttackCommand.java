package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaOpponent;

import java.util.Objects;

public class AttackCommand implements ActionCommand {
    private final ArenaOpponent target;
    private final int attackPower;
    private int damageDealt;

    public AttackCommand(ArenaOpponent target, int attackPower) {
        this.target = Objects.requireNonNull(target, "target");
        this.attackPower = Math.max(0, attackPower);
    }

    @Override
    public void execute() {
        int before = target.getHealth();
        target.takeDamage(attackPower);
        int after = target.getHealth();
        damageDealt = Math.max(0, before - after);

        System.out.println("[Attack] Dealt " + damageDealt + " damage to " + target.getName()
                + ". Opponent HP=" + target.getHealth());
    }

    @Override
    public void undo() {
        if(damageDealt <= 0){
            return;
        }
        target.restoreHealth(damageDealt);
        System.out.println("[Attack:undo] Restored " + damageDealt + " HP to " + target.getName()
                + ". Opponent HP=" + target.getHealth());
    }

    @Override
    public String getDescription() {
        return "Attack for " + attackPower + " damage";
    }
}
