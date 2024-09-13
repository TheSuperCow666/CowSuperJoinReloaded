package com.CowSuperJoinReloaded;

import com.FileTool.ItemJoinMessage;
import com.FileTool.PermissionJoinMessage;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
  public boolean onCommand(CommandSender sender, org.bukkit.command.Command arg1, String arg2, String[] args) {
    if (args.length == 0) {
      Tool.sendListMessage(sender, Main.getinstance().getConfig().getStringList("Settings.HelpMessage"));
      return true;
    } 
    if (args[0].equals("reload")) {
      Tool.sendMessage(sender, String.valueOf(Main.prefix) + "&a配置重载成功！");
      Main.getinstance().saveDefaultConfig();
      Main.getinstance().reloadConfig();
      PermissionJoinMessage.loadConfig();
      PermissionJoinMessage.load();
      ItemJoinMessage.loadConfig();
      ItemJoinMessage.load();
    } else if (args[0].equals("test")) {
      Tool.sendMessage(sender, String.valueOf(Main.prefix) + PlaceholderAPI.setPlaceholders((Player)sender, args[1]));
    } else {
      Tool.sendListMessage(sender, Main.getinstance().getConfig().getStringList("Settings.HelpMessage"));
    } 
    return true;
  }
}
