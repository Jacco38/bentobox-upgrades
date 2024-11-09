package org.jacco.upgrades.ui;

import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.panels.PanelItem;
import world.bentobox.bentobox.api.panels.builders.PanelBuilder;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;
import java.util.HashMap;

public class Panel {

    private Upgrades addon;
    private Island island;
    private String title;
    private int size;
    private HashMap<Integer, PanelItem> items = new HashMap();

    public Panel(Upgrades addon, Island island, String title, int size) {
        super ();
        this.addon = addon;
        this.island = island;
        this.title = title;
        this.size = size;
    }

    public void showPanel(User user) {
        PanelBuilder panel = new PanelBuilder().name(title).size(size*9);

        for (int i : this.items.keySet()) {
            panel.item(i, this.items.get(i));
        }

        panel.user(user).build();
    }

    public void addItem(PanelItem item, int slot) {
        this.items.put(slot, item);
    }
}