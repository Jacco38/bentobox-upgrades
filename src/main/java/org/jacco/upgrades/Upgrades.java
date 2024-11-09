package org.jacco.upgrades;

import org.bukkit.Material;
import org.jacco.upgrades.commands.UpgradesCommand;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.api.flags.clicklisteners.CycleClick;
import world.bentobox.bentobox.managers.RanksManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Upgrades extends Addon {

    private Settings settings;
    private Upgrades addon;

    public final static Flag UPGRADES_RANK_RIGHT =
            new Flag.Builder("UPGRADES_RANK_RIGHT", Material.GOLD_INGOT)
                    .type(Flag.Type.PROTECTION)
                    .mode(Flag.Mode.BASIC)
                    .clickHandler(new CycleClick("UPGRADES_RANK_RIGHT", RanksManager.MEMBER_RANK, RanksManager.OWNER_RANK))
                    .defaultRank(RanksManager.MEMBER_RANK)
                    .build();

    @Override
    public void onLoad() {
        super.onLoad();
        this.settings = new Settings(this);
    }

    @Override
    public void onEnable() {

        List<String> hookedGameModes = new ArrayList<String>();

        // Plugin startup logic
        getPlugin().getAddonsManager().getGameModeAddons().stream()
                .filter(g -> !settings.getDisabledGameModes().contains(g.getDescription().getName()))
                .forEach(g -> {
                    if (g.getPlayerCommand().isPresent()) {

                        hookedGameModes.add(g.getDescription().getName());
                        Upgrades.UPGRADES_RANK_RIGHT.addGameModeAddon(g);

                        new UpgradesCommand(this, g.getPlayerCommand().get());

                    }
                });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onReload() {
        // Reload logic
        this.settings = new Settings(this);
        getLogger().info("Reloaded Upgrades settings.");
    }

    public Settings getSettings() {
        return settings;
    }
}
