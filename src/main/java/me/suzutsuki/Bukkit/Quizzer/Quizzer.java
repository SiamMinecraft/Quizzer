package me.suzutsuki.Bukkit.Quizzer;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by Mizuki on 4/16/2014.
 */
public class Quizzer extends JavaPlugin {

    public static ArrayList<String> quiz = new ArrayList<String>();
    public static ArrayList<String> answer = new ArrayList<String>();
    public static String prefix = ChatColor.BLUE + "[Quiz]";

    public void onEnable()
    {
        getCommand("quiz").setExecutor(new QuizCE());
        getServer().getPluginManager().registerEvents(new ChatListener(this),this);
    }
}
