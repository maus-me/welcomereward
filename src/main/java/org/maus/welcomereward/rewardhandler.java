package org.maus.welcomereward;

import org.bukkit.entity.Player;
import org.maus.welcomereward.commands.*;
import org.maus.welcomereward.listeners.MessageListener;

import java.util.ArrayList;

public class rewardhandler {
    private final ArrayList<Player> playerWelcomeList = new ArrayList();
    private long lastJoinedTime = 0L;
    private final ArrayList<Player> playerGGList = new ArrayList();
    private long lastGGTime = 0L;

    public rewardhandler() {
        welcomereward.getInstance().getDrink().register(new GGWaveCommand(), "ggwave");
        welcomereward.getInstance().getDrink().register(new EmptyCommand(), "wr", "welcomereward")
                        .registerSub(new TestCommand())
                        .registerSub(new GGWaveCommand())
                        .registerSub(new ReloadCommand());
        welcomereward.getInstance().getServer().getPluginManager().registerEvents(new MessageListener(), welcomereward.getInstance());
    }

    public void processNewJoin() {
        lastJoinedTime = System.currentTimeMillis();
        playerWelcomeList.clear();
    }

    public void processNewGGWave() {
        playerGGList.clear();
        lastGGTime = System.currentTimeMillis();
    }

    public ArrayList<Player> getPlayerWelcomeList() {
        return playerWelcomeList;
    }

    public long getLastJoinedTime() {
        return lastJoinedTime;
    }

    public ArrayList<Player> getPlayerGGList() {
        return playerGGList;
    }

    public long getLastGGTime() {
        return lastGGTime;
    }
}
