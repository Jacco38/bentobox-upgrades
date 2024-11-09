package org.jacco.upgrades.ui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.panels.builders.PanelItemBuilder;
import world.bentobox.bentobox.database.objects.Island;

public class MainPanel extends Panel {

    public MainPanel(Upgrades addon, Island island) {
        super(addon, island, "Upgrades", 3);

        this.addItem(new PanelItemBuilder()
                .name("&rRange Upgrades")
                .icon(new ItemStack(Material.GLASS))
                .description("Upgrade your island range.\nCurrent level: 1\nNext level cost: 1000")
                .build(), addon.getSettings().getRangeSlot());

        this.addItem(new PanelItemBuilder()
                .name("&rMembers Upgrades")
                .icon(new ItemStack(Material.PLAYER_HEAD))
                .description("Upgrade your island members limit.")
                .build(), addon.getSettings().getMembersSlot());

        this.addItem(new PanelItemBuilder()
                .name("&rHopper Upgrades")
                .icon(new ItemStack(Material.HOPPER))
                .description("Upgrade your island hoppers limit.")
                .build(), addon.getSettings().getHoppersSlot());
    }
}
