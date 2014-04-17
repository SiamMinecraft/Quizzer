package me.suzutsuki.Bukkit.Quizzer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Mizuki on 4/16/2014.
 */
public class ChatListener implements Listener {

    Quizzer pl;
    public ChatListener(Quizzer plugin)
    {
        pl=plugin;
    }
    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event)
    {
        final String message = event.getMessage();
        for(String regex : Quizzer.answer)
        {
            if(message.matches(regex))
            {
                Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
                    @Override
                    public void run() {
                        for(Player p : Bukkit.getOnlinePlayers())
                        {
                            if(p.hasPermission("quizzer.admin"))
                            {
                                p.sendMessage(Quizzer.prefix + event.getPlayer().getName() + " got potentially correct answer.");
                                p.sendMessage(Quizzer.prefix + "The answer (s)he answered is " + message);
                            }
                        }
                    }
                });
            }
        }
    }
}
