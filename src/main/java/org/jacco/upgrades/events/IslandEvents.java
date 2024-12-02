package org.jacco.upgrades.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.events.island.IslandCreateEvent;
import world.bentobox.bentobox.database.objects.Island;

import java.sql.SQLException;


public class IslandEvents implements Listener {
    private final Upgrades addon;

    public IslandEvents(Upgrades addon) {
        this.addon = addon;
    }

    @EventHandler
    public void onIslandCreate(IslandCreateEvent event) {
        Island island = event.getIsland();
        try {
            addon.getDatabase().addIsland(island);
        } catch (SQLException e) {
            addon.getLogger().warning("Failed to add island to database: " + e.getMessage());
        }
    }

}
