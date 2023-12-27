package com.di4m0nds.spawnplugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.di4m0nds.spawnplugin.commands.SetSpawnCommand;
import com.di4m0nds.spawnplugin.commands.SpawnCommand;
import com.di4m0nds.spawnplugin.listeners.SpawnListeners;
import com.di4m0nds.spawnplugin.utils.CooldownManager;

public class SpawnPlugin extends JavaPlugin {

    private static SpawnPlugin instance;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        getLogger().fine("SpawnPlugin v" + getDescription().getVersion() + " has been enabled!");

        // Set the plugin instance for access from other classes
        instance = this;

        // Initialize your cooldown manager
        cooldownManager = new CooldownManager();

        // Register commands
        SetSpawnCommand setSpawnCommand = new SetSpawnCommand(this);
        SpawnCommand spawnCommand = new SpawnCommand(this);

        getCommand("setspawn").setExecutor(setSpawnCommand);
        getCommand("hub").setExecutor(spawnCommand);

        // Register event listeners
        getServer().getPluginManager().registerEvents(new SpawnListeners(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("SpawnPlugin v" + getDescription().getVersion() + " has been disabled!");
    }

    /**
     * Retrieve the instance of the CooldownManager.
     *
     * @return The instance of the CooldownManager.
     */
    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    /**
     * Retrieve the instance of the SpawnPlugin.
     *
     * @return The instance of the SpawnPlugin.
     */
    public static SpawnPlugin getInstance() {
        return instance;
    }
}
