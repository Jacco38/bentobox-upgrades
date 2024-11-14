package org.jacco.upgrades.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.listeners.JoinLeaveListener;

import java.util.Optional;

public class PlayerEvents implements Listener {

    private final Upgrades addon;

    public PlayerEvents(Upgrades addon) {
        this.addon = addon;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        if (!addon.inGameWorld(location.getWorld())) {
            return;
        }

        addon.getIslands().getIslandAt(location).ifPresent(i -> {

            if (!i.onIsland(location)) {
                event.setCancelled(true);
            }

            Location center = i.getCenter();

            double centerX = center.getX();
            double centerZ = center.getZ();

            double locationX = location.getX();
            double locationZ = location.getZ();

            if (Math.abs(centerX - locationX) > addon.getSettings().getIslandRange(i) || Math.abs(centerZ - locationZ) > addon.getSettings().getIslandRange(i)) {
                event.setCancelled(true);
            }

        });
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (!addon.inGameWorld(from.getWorld())) {
            return;
        }

        addon.getIslands().getIslandAt(from).ifPresent(i -> {

            if (!i.onIsland(to)) {
                event.setCancelled(true);
            }

            Location center = i.getCenter();

            double centerX = center.getX();
            double centerZ = center.getZ();

            double toX = to.getX();
            double toZ = to.getZ();

            if (Math.abs(centerX - toX) > (addon.getSettings().getIslandRange(i) + 10) || Math.abs(centerZ - toZ) > (addon.getSettings().getIslandRange(i) + 10)) {
                event.setCancelled(true);
            }

        });
    }

}
