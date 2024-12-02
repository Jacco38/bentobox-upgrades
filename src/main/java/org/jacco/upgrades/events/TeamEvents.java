package org.jacco.upgrades.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.events.team.TeamInviteEvent;
import world.bentobox.bentobox.database.objects.Island;

import java.util.Map;
import java.util.UUID;

public class TeamEvents implements Listener {

    private final Upgrades addon;

    public TeamEvents(Upgrades addon) {
        this.addon = addon;
    }

    @EventHandler
    public void onTeamInvite(TeamInviteEvent event) {
        UUID playerUUID = event.getPlayerUUID();
        Player player = Bukkit.getPlayer(playerUUID);

        Island island = event.getIsland();

        Map<UUID, Integer> members = island.getMembers();

        if (members.size() >= addon.getSettings().getMemberLimit(island)) {
            event.setCancelled(true);
        }

    }

}
