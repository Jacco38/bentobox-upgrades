package org.jacco.upgrades.ui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.panels.PanelItem;
import world.bentobox.bentobox.api.panels.builders.PanelItemBuilder;
import world.bentobox.bentobox.database.objects.Island;

public class RangeUpgradesMenu extends Panel{

    public RangeUpgradesMenu(Upgrades addon, Island island, Panel previousMenu) {
        super(addon, island, "Range Upgrades", 3);

        this.addItem(new PanelItemBuilder()
                .name("&r&cBack")
                .icon(new ItemStack(Material.OAK_DOOR))
                .description("Go back to the main menu.")
                .clickHandler(new PanelClick("openmenu", previousMenu))
                .build(), 25);

        this.addItem(new PanelItemBuilder()
                .name("&r&cClose")
                .icon(new ItemStack(Material.BARRIER))
                .description("Close the menu.")
                .clickHandler(new CloseUI())
                .build(), 26);

        for (int i = 0; i < addon.getSettings().getRangeUpgrades().size(); i++) {
            String name = "&r&eLevel " + addon.getSettings().getRangeUpgrades().get(i);

            //TODO: Current level of the upgrade

            PanelItem item = new PanelItemBuilder()
                    .name(name)
                    .icon(new ItemStack(Material.GLASS))
                    .description("Upgrade cost: " + addon.getConfig().getConfigurationSection("range-upgrades.levels." + addon.getSettings().getRangeUpgrades().get(i)).getInt("cost") + " Skycoins")
                    .build();

            if (i == 0) {
                item.setGlow(true);
                item.setName(item.getName() + " UNLOCKED");
            }

            this.addItem(item, i);
        }

    }

}
