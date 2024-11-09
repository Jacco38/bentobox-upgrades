package org.jacco.upgrades.commands;

import org.jacco.upgrades.Upgrades;
import world.bentobox.bentobox.api.commands.CompositeCommand;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;

import java.util.List;
import java.util.logging.Logger;

public class UpgradesCommand extends CompositeCommand {

    public UpgradesCommand(Upgrades addon, CompositeCommand cmd) {
        super(addon, cmd, "upgrades");
        Logger logger = Logger.getLogger("Minecraft");
        logger.info("Upgrades command loaded.");
    }

    @Override
    public void setup() {
        this.setDescription("Upgrade your island with new features.");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(User user, String label, List<String> args) {

        if (args.size() == 0) {
            Island island = getIslands().getIsland(this.getWorld(), user);

            if (island == null) {
                user.sendMessage("general.errors.no-island");
                return false;
            }

            if (!island.onIsland(user.getLocation())) {
                user.sendMessage("upgrades.error.notonisland");
                return false;
            }

            user.sendMessage("Opening Upgrades Menu...");
            return true;
        }
        this.showHelp(this, user);
        return false;
    }

    @Override
    public boolean canExecute(User user, String label, List<String> args) {

        Island island = getIslands().getIsland(this.getWorld(), user);

        if (island == null) {
            user.sendMessage("You must be on an island to use this command.");
            return false;
        }

        if (!island.onIsland(user.getLocation())) {
            user.sendMessage("You must be on your island to use this command.");
            return false;
        }

        if (!island.isAllowed(user, Upgrades.UPGRADES_RANK_RIGHT)) {
            user.sendMessage("You are not allowed to use this command on this island.");
            return false;
        }

        return true;
    }
}
