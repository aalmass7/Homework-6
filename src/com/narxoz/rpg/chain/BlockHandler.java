package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class BlockHandler extends DefenseHandler {
    private final double blockPercent;

    public BlockHandler(double blockPercent) {
        this.blockPercent = blockPercent;
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        int damage = Math.max(0, incomingDamage);
        double pct = Math.max(0.0, Math.min(1.0, blockPercent));
        int blocked = (int) (damage * pct);
        int remaining = Math.max(0, damage - blocked);

        System.out.println("[Block] Blocked " + blocked + " damage (" + (int) (pct * 100) + "%), remaining=" + remaining);
        passToNext(remaining, target);
    }
}
