package org.jacco.upgrades;

import java.util.ArrayList;
import java.util.HashSet;

public class Settings {

    private HashSet<String> disabledGameModes;
    private Upgrades addon;

    public Settings(Upgrades addon) {
        this.addon = addon;
        this.addon.saveDefaultConfig();

        this.disabledGameModes = new HashSet<>(this.addon.getConfig().getStringList("disabled-gamemodes"));
    }

    public HashSet<String> getDisabledGameModes() {
        return disabledGameModes;
    }

    public int getRangeSlot() {
        return this.addon.getConfig().getInt("range-upgrades.slot");
    }

    public int getMembersSlot() {
        return this.addon.getConfig().getInt("member-upgrades.slot");
    }

    public int getHoppersSlot() {
        return this.addon.getConfig().getInt("hopper-upgrades.slot");
    }

    public ArrayList<String> getRangeUpgrades() {
        ArrayList<String> upgrades = new ArrayList<>();

        for (String key : this.addon.getConfig().getConfigurationSection("range-upgrades.levels").getKeys(false)) {
            upgrades.add(key);
        }

        return upgrades;
    }

    public ArrayList<String> getMemberUpgrades() {
        ArrayList<String> upgrades = new ArrayList<>();

        for (String key : this.addon.getConfig().getConfigurationSection("member-upgrades.levels").getKeys(false)) {
            upgrades.add(key);
        }

        return upgrades;
    }

    public ArrayList<String> getHopperUpgrades() {
        ArrayList<String> upgrades = new ArrayList<>();

        for (String key : this.addon.getConfig().getConfigurationSection("hopper-upgrades.levels").getKeys(false)) {
            upgrades.add(key);
        }

        return upgrades;
    }

}
