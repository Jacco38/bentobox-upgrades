package org.jacco.upgrades;

import org.bukkit.Material;
import org.bukkit.World;
import org.jacco.upgrades.commands.UpgradesCommand;
import org.jacco.upgrades.events.PlayerEvents;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.addons.GameModeAddon;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.api.flags.clicklisteners.CycleClick;
import world.bentobox.bentobox.managers.RanksManager;
import world.bentobox.bentobox.util.Util;
import java.util.ArrayList;
import java.util.List;

public final class Upgrades extends Addon {

    private Settings settings;
    private Upgrades addon;
    List<GameModeAddon> hookedGameModes = new ArrayList<>();

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
        this.addon = this;
    }

    @Override
    public void onEnable() {

        // Plugin startup logic
        getPlugin().getAddonsManager().getGameModeAddons().stream()
                .filter(g -> !settings.getDisabledGameModes().contains(g.getDescription().getName()))
                .forEach(g -> {
                    if (g.getPlayerCommand().isPresent()) {

                        hookedGameModes.add(g);
                        Upgrades.UPGRADES_RANK_RIGHT.addGameModeAddon(g);

                        new UpgradesCommand(this, g.getPlayerCommand().get());

                    }
                });

        this.registerListener(new PlayerEvents(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onReload() {
        // Reload logic
        this.settings = new Settings(this);
        getLogger().info("Reloaded Upgrades config.");
    }

    public Settings getSettings() {
        return settings;
    }

    public boolean inGameWorld(World world) {
        return hookedGameModes.stream().anyMatch(gm -> gm.inWorld(Util.getWorld(world)));
    }

}
