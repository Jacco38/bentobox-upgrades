package org.jacco.upgrades.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.events.island.IslandCreateEvent;
import world.bentobox.bentobox.api.events.island.IslandDeletedEvent;
import world.bentobox.bentobox.api.events.island.IslandResetEvent;
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

    @EventHandler
    public void onIslandReset(IslandResetEvent event) {
        Island oldIsland = event.getOldIsland();
        Island newIsland = event.getIsland();

        try {

            if (!addon.getDatabase().getIsland(oldIsland)) {
                addon.getLogger().warning("No island found in database");
            } else {
                addon.getLogger().info("Island found in database");
            }

            addon.getDatabase().deleteIsland(oldIsland);
            addon.getDatabase().addIsland(newIsland);
        } catch (SQLException e) {
            addon.getLogger().warning("Failed to update island in database: " + e.getMessage());
        }

    }

    @EventHandler
    public void onIslandDelete(IslandDeletedEvent event) {
        Island island = event.getIsland();
        try {
            addon.getDatabase().deleteIsland(island);
        } catch (SQLException e) {
            addon.getLogger().warning("Failed to delete island from database: " + e.getMessage());
        }
    }

}
