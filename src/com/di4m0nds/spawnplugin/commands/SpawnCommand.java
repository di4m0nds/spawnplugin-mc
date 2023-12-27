package com.di4m0nds.spawnplugin.commands;

import com.di4m0nds.spawnplugin.SpawnPlugin;
import com.di4m0nds.spawnplugin.components.LoadingBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.Location;

/**
 * Command executor for the "/hub" command.
 */
public class SpawnCommand implements CommandExecutor {

    private final SpawnPlugin plugin;

    /**
     * Constructor for the SpawnCommand.
     *
     * @param plugin The main plugin instance.
     */
    public SpawnCommand(SpawnPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes the "/hub" command, teleporting the player to the spawn location with a loading bar.
     *
     * @param sender The command sender.
     * @param cmd    The command that was executed.
     * @param label  The alias of the command used.
     * @param args   The arguments provided with the command.
     * @return True if the command was executed successfully, otherwise false.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check if the command sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }

        // Cast the command sender to a player
        Player player = (Player) sender;


        // Check if the player is in cooldown
        if (plugin.getCooldownManager().hasCooldown(player.getName())) {
            long remainingCooldown = plugin.getCooldownManager().getRemainingCooldown(player.getName());
            player.sendMessage(
                "§c§l\u26A0 §rYou are in cooldown. Please wait §e§o" +
                (remainingCooldown / 1000) + " seconds §rbefore using this command again."
            );
            return true;
        }

        // Get the spawn location from the plugin configuration
        Location location = plugin.getConfig().getLocation("spawn");

        // Check if spawn location is set
        if (location == null) {
            sender.sendMessage("§c§l\u26D4 §rThere is no spawn point set. Use §a§l/setspawn §rto set it.");
            return true;
        }
        
        // Apply cooldown
        plugin.getCooldownManager().applyCooldown(player.getName(), 15000);

        // Create a loading bar and teleport the player with a delay
        LoadingBar loadingBar = new LoadingBar(plugin, player);
        loadingBar.teleportWithDelay(
                location,
                "§e§l\u2615 §rYou have been teleported to the §e§lHUB §rPoint.",
                BarColor.RED,
                BarStyle.SOLID
        );

        return true;
    }
}
