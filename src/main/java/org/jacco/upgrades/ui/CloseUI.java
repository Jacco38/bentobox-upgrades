package org.jacco.upgrades.ui;

import org.bukkit.event.inventory.ClickType;
import world.bentobox.bentobox.api.panels.Panel;
import world.bentobox.bentobox.api.panels.PanelItem;
import world.bentobox.bentobox.api.user.User;

public class CloseUI implements PanelItem.ClickHandler {
    @Override
    public boolean onClick(Panel panel, User user, ClickType clickType, int slot) {
        user.closeInventory();
        return true;
    }
}
