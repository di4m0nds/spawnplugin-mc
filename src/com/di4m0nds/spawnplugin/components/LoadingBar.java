package com.di4m0nds.spawnplugin.components;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.Particle;

public class LoadingBar {

    private final JavaPlugin plugin;
    private final Player player;

    /**
     * Constructs a new LoadingBar instance.
     *
     * @param plugin The JavaPlugin instance associated with the plugin.
     * @param player The Player for whom the loading bar is displayed.
     */
    public LoadingBar(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    /**
     * Teleports the player to a specified location with a delay, displaying a loading bar during the teleportation sequence.
     *
     * @param location The target location to which the player will be teleported.
     * @param msg The message to be sent to the player upon successful teleportation.
     * @param barColor The color of the loading bar.
     * @param barStyle The style of the loading bar.
     *
     * @description
     * Creates and displays a boss bar with a specified color and style.
     * Updates the boss bar's progress every second until completion.
     * Teleports the player to the specified location, plays a sound, and shows a message upon completion.
     * Additionally, spawns portal particles during teleportation.
     *
     * @example
     * ```java
     * LoadingBar loadingBar = new LoadingBar(plugin, player);
     * loadingBar.teleportWithDelay(targetLocation, "§e§l\u2705 §rYou have been teleported.", BarColor.RED, BarStyle.SEGMENTED_20);
     * ```
     *
     * @note
     * The `20L` parameter in `runTaskTimer(plugin, 0L, 20L)` represents a tick interval (1 tick = 1/20th of a second).
     * Adjust as needed for the desired update frequency.
     */
    public void teleportWithDelay(Location location, String msg, BarColor barColor, BarStyle barStyle) {
        BossBar bossBar = Bukkit.createBossBar("§e§k00 §r§cTeleportation Sequence Initiated §e§k00", barColor, barStyle);
        bossBar.setProgress(1.0); // Set the initial progress (1.0 is 100%)

        bossBar.addPlayer(player);

        // Schedule a task to update the boss bar every second
        new BukkitRunnable() {
            double progress = 1.0;

            @Override
            public void run() {
                if (progress <= 0.0) {
                    // Boss bar completed
                    bossBar.removePlayer(player);
                    bossBar.removeAll();

                    cancel();

                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
                    player.teleport(location);
                    player.sendMessage(msg);
                    player.spawnParticle(Particle.PORTAL, player.getLocation(), 100, 0.5, 1.0, 0.5);
                } else {
                    // Update the boss bar progress
                    bossBar.setProgress(progress);
                    progress -= 0.1; // Adjust the decrement as needed
                }
            }
        }.runTaskTimer(plugin, 0L, 20L); // 20L = 1 second (adjust as needed)
    }
}
