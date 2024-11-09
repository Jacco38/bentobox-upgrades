package org.jacco.upgrades.ui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.panels.builders.PanelItemBuilder;
import world.bentobox.bentobox.database.objects.Island;

public class MemberUpgradesMenu extends Panel{

    public MemberUpgradesMenu(Upgrades addon, Island island, Panel previousMenu) {
        super(addon, island, "Members Upgrades", 3);

        this.addItem(new PanelItemBuilder()
                .name("&rBack")
                .icon(new ItemStack(Material.OAK_DOOR))
                .description("Go back to the main menu.")
                .clickHandler(new PanelClick("openmenu", previousMenu))
                .build(), 25);

        this.addItem(new PanelItemBuilder()
                .name("&rClose")
                .icon(new ItemStack(Material.BARRIER))
                .description("Close the menu.")
                .clickHandler(new CloseUI())
                .build(), 26);

    }

}
