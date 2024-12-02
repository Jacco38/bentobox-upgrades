package org.jacco.upgrades.ui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.panels.PanelItem;
import world.bentobox.bentobox.api.panels.builders.PanelItemBuilder;
import world.bentobox.bentobox.database.objects.Island;

public class MemberUpgradesMenu extends Panel{

    public MemberUpgradesMenu(Upgrades addon, Island island, Panel previousMenu) {
        super(addon, island, "Members Upgrades", 3);

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

        int currentLevel = addon.getSettings().getCurrentLevel(island, "member");

        for (int i = 0; i < addon.getSettings().getMemberUpgrades().size(); i++) {
            String name = "&r&eLevel " + addon.getSettings().getMemberUpgrades().get(i);

            //TODO: Current level of the upgrade

            PanelItem item = new PanelItemBuilder()
                    .name(name)
                    .icon(new ItemStack(Material.PLAYER_HEAD))
                    .description("Upgrade cost: " + addon.getConfig().getConfigurationSection("member-upgrades.levels." + addon.getSettings().getMemberUpgrades().get(i)).getInt("cost") + " Skycoins")
                    .build();

            if (i < currentLevel) {
                item.setGlow(true);
                item.setName(item.getName() + " UNLOCKED");
            } else {
                item.setClickHandler(new UpgradeClick(addon, "member", i + 1, island));
            }

            this.addItem(item, i);
        }

    }

}
