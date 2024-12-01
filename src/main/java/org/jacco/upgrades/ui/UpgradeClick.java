package org.jacco.upgrades.ui;

import org.bukkit.event.inventory.ClickType;
import world.bentobox.bentobox.api.panels.Panel;
import world.bentobox.bentobox.api.panels.PanelItem;
import world.bentobox.bentobox.api.user.User;

public class UpgradeClick implements PanelItem.ClickHandler {

    private String upgrade;

    public UpgradeClick(String upgrade) {
        this.upgrade = upgrade;
    }

    @Override
    public boolean onClick(Panel panel, User user, ClickType clickType, int slot) {
        //TODO: Implement upgrade logic
        return false;
    }

}
