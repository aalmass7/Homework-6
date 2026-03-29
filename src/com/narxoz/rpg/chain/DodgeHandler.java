package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;
import java.util.Random;

public class DodgeHandler extends DefenseHandler {
    private final double dodgeChance;
    private final Random random;

    public DodgeHandler(double dodgeChance, long seed) {
        this.dodgeChance = Math.max(0.0, Math.min(1.0, dodgeChance));
        this.random = new Random(seed);
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        int damage = Math.max(0, incomingDamage);
        double roll = random.nextDouble();

        if (roll < dodgeChance) {
            System.out.println("[Dodge] Attack evaded! (roll=" + String.format("%.3f", roll)
                    + " < chance=" + String.format("%.3f", dodgeChance) + ")");
            return;
        }
        System.out.println("[Dodge] Missed dodge (roll=" + String.format("%.3f", roll)
                + " >= chance=" + String.format("%.3f", dodgeChance) + ")");

        passToNext(damage, target);

    }
}
