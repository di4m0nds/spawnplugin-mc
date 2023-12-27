package com.di4m0nds.spawnplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.di4m0nds.spawnplugin.SpawnPlugin;

import org.bukkit.entity.Player;

import org.bukkit.Location;

public class SpawnListeners implements Listener {

  private final SpawnPlugin plugin;

  /**
   * Constructor for the SpawnListeners class.
   *
   * @param plugin The SpawnPlugin instance.
   */
  public SpawnListeners(SpawnPlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * Handles the player join event.
   *
   * @param ev The PlayerJoinEvent instance.
   */
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent ev) {
    Player player = ev.getPlayer();

    // Check if player is joining for the first time
    if (!player.hasPlayedBefore()) {
      // Retrieve spawn location from plugin configuration
      Location location = plugin.getConfig().getLocation("spawn");

      // Teleport player to spawn location if available
      if (location != null) {
        player.teleport(location);
      }
    }
  }

  /**
   * Handles the player respawn event.
   *
   * @param ev The PlayerRespawnEvent instance.
   */
  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent ev) {
    // Retrieve spawn location from plugin configuration
    Location location = plugin.getConfig().getLocation("spawn");

    // Set respawn location to spawn if available
    if (location != null) {
      ev.setRespawnLocation(location);
    }
  }
}

