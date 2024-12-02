package org.jacco.upgrades.ui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jacco.upgrades.Settings;
import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.panels.builders.PanelItemBuilder;
import world.bentobox.bentobox.database.objects.Island;

public class MainPanel extends Panel {

    public MainPanel(Upgrades addon, Island island) {
        super(addon, island, "Upgrades", 3);

        Settings settings = addon.getSettings();

        int currentRangeLevel = settings.getCurrentLevel(island, "range");
        int currentMemberLevel = settings.getCurrentLevel(island, "member");
        int currentHopperLevel = settings.getCurrentLevel(island, "hopper");

        this.addItem(new PanelItemBuilder()
                .name("&r&eRange Upgrades")
                .icon(new ItemStack(Material.GLASS))
                .description("Upgrade your island hoppers limit.\nCurrent level: " + currentRangeLevel + "\nNext level cost: " + settings.getUpgradeCost(currentRangeLevel, "range"))
                .clickHandler(new PanelClick("openmenu", new RangeUpgradesMenu(addon, island, this)))
                .build(), addon.getSettings().getRangeSlot());

        this.addItem(new PanelItemBuilder()
                .name("&r&eMembers Upgrades")
                .icon(new ItemStack(Material.PLAYER_HEAD))
                .description("Upgrade your island hoppers limit.\nCurrent level: " + currentMemberLevel + "\nNext level cost: " + settings.getUpgradeCost(currentMemberLevel, "member"))
                .clickHandler(new PanelClick("openmenu", new MemberUpgradesMenu(addon, island, this)))
                .build(), addon.getSettings().getMembersSlot());

        this.addItem(new PanelItemBuilder()
                .name("&r&eHopper Upgrades")
                .icon(new ItemStack(Material.HOPPER))
                .description("Upgrade your island hoppers limit.\nCurrent level: " + currentHopperLevel + "\nNext level cost: " + settings.getUpgradeCost(currentHopperLevel, "hopper"))
                .clickHandler(new PanelClick("openmenu", new HopperUpgradesMenu(addon, island, this)))
                .build(), addon.getSettings().getHoppersSlot());

        this.addItem(new PanelItemBuilder()
                .name("&r&cClose")
                .icon(new ItemStack(Material.BARRIER))
                .description("Close the menu.")
                .clickHandler(new CloseUI())
                .build(), 26);
    }
}
