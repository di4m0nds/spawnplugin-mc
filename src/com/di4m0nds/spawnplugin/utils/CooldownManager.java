package com.di4m0nds.spawnplugin.utils;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private final Map<String, Long> cooldowns = new HashMap<>();
    private final long defaultCooldownMillis = 5000; // 5 seconds default cooldown

    /**
     * Checks if a player is currently in cooldown.
     *
     * @param playerName The name of the player.
     * @return True if the player is in cooldown, false otherwise.
     */
    public boolean hasCooldown(String playerName) {
        return cooldowns.containsKey(playerName) && System.currentTimeMillis() < cooldowns.get(playerName);
    }

    /**
     * Gets the remaining cooldown time for a player.
     *
     * @param playerName The name of the player.
     * @return The remaining cooldown time in milliseconds.
     */
    public long getRemainingCooldown(String playerName) {
        return cooldowns.getOrDefault(playerName, 0L) - System.currentTimeMillis();
    }

    /**
     * Applies a cooldown to a player.
     *
     * @param playerName         The name of the player.
     * @param setCooldownMillis  The duration of the cooldown in milliseconds.
     */
    public void applyCooldown(String playerName, long setCooldownMillis) {
        long cooldownEndTime = System.currentTimeMillis() + setCooldownMillis;
        cooldowns.put(playerName, cooldownEndTime);
    }

    /**
     * Applies the default cooldown to a player.
     *
     * @param playerName The name of the player.
     */
    public void applyDefaultCooldown(String playerName) {
        applyCooldown(playerName, defaultCooldownMillis);
    }
}
