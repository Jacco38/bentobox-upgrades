package org.jacco.upgrades.ui;

import org.bukkit.event.inventory.ClickType;
import world.bentobox.bentobox.api.panels.Panel;
import world.bentobox.bentobox.api.panels.PanelItem.ClickHandler;
import world.bentobox.bentobox.api.user.User;

public class PanelClick implements ClickHandler {

    private String openmenu;
    private org.jacco.upgrades.ui.Panel menu;

    public PanelClick(String openmenu, org.jacco.upgrades.ui.Panel menu) {
        this.openmenu = openmenu;
        this.menu = menu;
    }

    @Override
    public boolean onClick(Panel panel, User user, ClickType clickType, int slot) {
        if (openmenu.equals("openmenu")) {
            user.closeInventory();
            menu.showPanel(user);
        }
        return true;
    }

}
