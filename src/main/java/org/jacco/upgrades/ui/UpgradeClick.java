package org.jacco.upgrades.ui;

import org.bukkit.event.inventory.ClickType;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.panels.Panel;
import world.bentobox.bentobox.api.panels.PanelItem;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;

import java.sql.SQLException;
import java.util.HashMap;

public class UpgradeClick implements PanelItem.ClickHandler {

    private String upgrade;
    private int level;
    private Island island;
    private Upgrades addon;

    public UpgradeClick(Upgrades addon, String upgrade, int level, Island island) {
        this.upgrade = upgrade;
        this.level = level;
        this.island = island;
        this.addon = addon;
    }

    @Override
    public boolean onClick(Panel panel, User user, ClickType clickType, int slot) {
        //TODO: Implement upgrade logic

        try {
            HashMap<String, Integer> levels = addon.getDatabase().getIslandLevels(island);

            if (level != levels.get(this.upgrade) + 1) {
                user.sendMessage("You can't upgrade to that level yet.");
            } else {
                addon.getDatabase().updateLevel(island, this.upgrade, level);
                user.sendMessage("Upgraded to level " + level + "!");
            }
            user.closeInventory();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

}
