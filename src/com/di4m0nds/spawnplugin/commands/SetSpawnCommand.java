package com.di4m0nds.spawnplugin.commands;

import com.di4m0nds.spawnplugin.SpawnPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

/**
 * Command executor for the "/setspawn" command.
 */
public class SetSpawnCommand implements CommandExecutor {

    private final SpawnPlugin plugin;

    /**
     * Constructor for the SetSpawnCommand.
     *
     * @param plugin The main plugin instance.
     */
    public SetSpawnCommand(SpawnPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes the "/setspawn" command, setting the spawn location to the player's current location.
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

        // Get the player's current location
        Location location = player.getLocation();

        // Set spawn location in the plugin configuration
        setSpawnLocation(location);

        // Send a confirmation message to the player
        player.sendMessage("§a§l\u2705 §rSpawn location set successfully!");

        return true;
    }

    /**
     * Sets the spawn location in the plugin configuration.
     *
     * @param location The location to set as the spawn.
     */
    private void setSpawnLocation(Location location) {
        // Set spawn location in the plugin config
        plugin.getConfig().set("spawn", location);

        // Save the updated configuration
        plugin.saveConfig();
    }
}

// package com.di4m0nds.spawnplugin.commands;
// 
// import com.di4m0nds.spawnplugin.SpawnPlugin;
// 
// import org.bukkit.command.Command;
// import org.bukkit.command.CommandExecutor;
// import org.bukkit.command.CommandSender;
// 
// import org.bukkit.entity.Player;
// 
// import org.bukkit.Location;
// 
// public class SetSpawnCommand implements CommandExecutor{
// 
//   private final SpawnPlugin plugin;
// 
//   public SetSpawnCommand(SpawnPlugin plugin) {
//     this.plugin = plugin;
//   }
// 
//   @Override
//   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//     if (!(sender instanceof Player)) {
//       sender.sendMessage("Only players can use that command!");
//       return true;
//     }
// 
//     Player player = (Player) sender;
//     Location location = player.getLocation();
// 
//     // Manually (one by one)
//       // plugin.getConfig().set("spawn.x", location.getX());
//       // plugin.getConfig().set("spawn.y", location.getY());
//       // plugin.getConfig().set("spawn.z", location.getZ());
//       // plugin.getConfig().set("spawn.world.name", location.getWorld().getName());
// 
//     plugin.getConfig().set("spawn", location);
//     plugin.saveConfig();
// 
//     player.sendMessage("§a§l[*] §rSpawn location setted successfully!");
// 
//     return true;
//   }
// }
