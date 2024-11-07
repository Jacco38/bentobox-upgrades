package org.jacco.upgrades;

import world.bentobox.bentobox.api.addons.Addon;

public final class Upgrades extends Addon {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Upgrades staat aan! :) yippeeee");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
