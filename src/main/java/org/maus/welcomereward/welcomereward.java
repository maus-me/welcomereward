package org.maus.welcomereward;

import com.jonahseguin.drink.CommandService;
import com.jonahseguin.drink.Drink;
import org.bukkit.plugin.java.JavaPlugin;

public final class welcomereward extends JavaPlugin {
    private static welcomereward instance;
    CommandService drink;
    private rewardhandler rewardHandler;
    private boolean active;

    public welcomereward() {
    }

    public static welcomereward getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        drink = Drink.get(this);
        loadHandlers();
        drink.registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
        instance = null;
    }

    private void loadHandlers() {
        rewardHandler = new rewardhandler();
        active = getConfig().getBoolean("enabled");
    }

    public CommandService getDrink() {
        return drink;
    }

    public rewardhandler getRewardHandler() {
        return rewardHandler;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
