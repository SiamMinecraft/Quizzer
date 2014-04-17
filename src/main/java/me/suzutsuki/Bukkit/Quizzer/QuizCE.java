package me.suzutsuki.Bukkit.Quizzer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

/**
 * Created by Mizuki on 4/16/2014.
 */
public class QuizCE implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("quiz"))
        {
            if(args.length == 0)
            {
                showHelp(commandSender);
            }
            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("lines"))
                {
                    if(Quizzer.quiz.size() > 0)
                    {
                        int i = 0;
                        commandSender.sendMessage(Quizzer.prefix + "Showing Current quiz.");
                        for(String lines : Quizzer.quiz)
                        {
                            commandSender.sendMessage(Quizzer.prefix + Integer.toString(i) + " : " + ChatColor.GRAY + lines);
							i++;
                        }
                    }
                    else
                    {
                        commandSender.sendMessage(Quizzer.prefix + "There are no quiz set.");
                    }
                    return true;
                }
                if(args[0].equalsIgnoreCase("answers"))
                {
                    if(Quizzer.answer.size() > 0)
                    {
                        int i = 0;
                        commandSender.sendMessage(Quizzer.prefix + "Showing Current possible answers.");
                        for(String lines : Quizzer.answer)
                        {
                            commandSender.sendMessage(Quizzer.prefix + Integer.toString(i) + " : " + ChatColor.GRAY + lines);
							i++;
                        }
                    }
                    else
                    {
                        commandSender.sendMessage(Quizzer.prefix + "There are no answers set.");
                    }
                    return true;
                }
                if(args[0].equalsIgnoreCase("new") || args[0].equalsIgnoreCase("reset"))
                {
                    Quizzer.quiz = new ArrayList<String>();
                    Quizzer.answer = new ArrayList<String>();
                    commandSender.sendMessage(Quizzer.prefix + "Quiz reset.");
                    return true;
                }
                if(args[0].equalsIgnoreCase("bcast"))
                {
                    if(Quizzer.quiz.size() > 0)
                    {
                        commandSender.sendMessage(Quizzer.prefix + "Broadcasting current quiz.");
                        for(String lines : Quizzer.quiz)
                        {
                            Bukkit.broadcastMessage(Quizzer.prefix + " " + ChatColor.GRAY + lines);
                        }
                    }
                    else
                    {
                        commandSender.sendMessage(Quizzer.prefix + "There are no quiz set.");
                    }
                    return true;
                }
                else
                {
                    showHelp(commandSender);
                    return true;
                }
            }
            if(args.length > 1)
            {
                if(args[0].equalsIgnoreCase("addline"))
                {
                    StringBuilder stringBuilder = new StringBuilder();
                    boolean skipFirst = false;
                    for(String msg : args)
                    {
                        if(!skipFirst)
                        {
                            skipFirst = true;
                        }
                        else
                        {
                            stringBuilder.append(msg + " ");
                        }
                    }
                    Quizzer.quiz.add(stringBuilder.toString().trim());
                    commandSender.sendMessage(Quizzer.prefix + "This message has been added into the quiz.");
                    return true;
                }
                if(args[0].equalsIgnoreCase("delline"))
                {
                    int i = 0;
                    try
                    {
                        i = Integer.parseInt(args[1]);
                    }
                    catch (NumberFormatException e)
                    {
                        commandSender.sendMessage(Quizzer.prefix + "Invalid number format. (Essentially this is not a number)");
                        return true;
                    }
                    try
                    {
                        Quizzer.quiz.remove(i);
                        commandSender.sendMessage(Quizzer.prefix + "Line removed.");
                    }catch (ArrayIndexOutOfBoundsException e)
                    {
                        commandSender.sendMessage(Quizzer.prefix + "Invalid line index. Line not found.");
                        return true;
                    }
                }
                if(args[0].equalsIgnoreCase("addans"))
                {
                    StringBuilder stringBuilder = new StringBuilder();
                    boolean skipFirst = false;
                    for(String msg : args)
                    {
                        if(!skipFirst)
                        {
                            skipFirst = true;
                        }
                        else
                        {
                            stringBuilder.append(msg + " ");
                        }
                    }
                    Quizzer.answer.add(stringBuilder.toString().trim());
                    commandSender.sendMessage(Quizzer.prefix + "This regex has been added into the answer list.");
                    return true;
                }
                if(args[0].equalsIgnoreCase("delans"))
                {
                    int i = 0;
                    try
                    {
                        i = Integer.parseInt(args[1]);
                    }
                    catch (NumberFormatException e)
                    {
                        commandSender.sendMessage(Quizzer.prefix + "Invalid number format. (Essentially this is not a number)");
                        return true;
                    }
                    try
                    {
                        Quizzer.answer.remove(i);
                        commandSender.sendMessage(Quizzer.prefix + "Answer removed.");
                    }catch (ArrayIndexOutOfBoundsException e)
                    {
                        commandSender.sendMessage(Quizzer.prefix + "Invalid line index. Line not found.");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void showHelp(CommandSender commandSender)
    {
        commandSender.sendMessage(ChatColor.AQUA + "/quiz [new,reset] - New quiz");
        commandSender.sendMessage(ChatColor.AQUA + "/quiz lines - Show current quiz");
        commandSender.sendMessage(ChatColor.AQUA + "/quiz answers - Show current possible answers");
        commandSender.sendMessage(ChatColor.AQUA + "/quiz addline <text> - Add a line of quiz");
        commandSender.sendMessage(ChatColor.AQUA + "/quiz delline <index> - Remove a line of quiz");
        commandSender.sendMessage(ChatColor.AQUA + "/quiz addans <regex> - Add possible answer to detect using Regular Expressions (regex).");
        commandSender.sendMessage(ChatColor.AQUA + "/quiz delans <index> - Delete an answer");
        commandSender.sendMessage(ChatColor.AQUA + "/quiz bcast - Broadcast the quiz");
    }
}
