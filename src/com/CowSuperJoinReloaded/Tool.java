package com.CowSuperJoinReloaded;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tool {
  public static void sendConsoleMessage(String message) {
    Bukkit.getConsoleSender().sendMessage(message.replace("&", "§"));
  }
  
  public static void sendMessage(CommandSender s, String message) {
    s.sendMessage(message.replace("&", "§"));
  }
  
  public static void sendListMessage(CommandSender s, List<String> ListMessage) {
    for (String m : ListMessage)
      sendMessage(s, m); 
  }
  
  public static String getProperty(String name, String s) {
    String regex = String.valueOf(name) + "=(.*?);";
    Pattern pa = Pattern.compile(regex);
    Matcher ma = pa.matcher(s);
    if (ma.find()) {
      String result = ma.group().replace(";", "").replace(String.valueOf(name) + "=", "");
      return result;
    } 
    return "0";
  }
  
  public static String getPropertyType(String s) {
    String regex = "(.*?)\\{";
    Pattern pa = Pattern.compile(regex);
    Matcher ma = pa.matcher(s);
    if (ma.find()) {
      String result = ma.group().replace("{", "");
      return result;
    } 
    return "0";
  }
  
  public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
    NMSUtil.sendTitle(player, fadeIn, stay, fadeOut, message, null);
  }
  
  public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
    NMSUtil.sendTitle(player, fadeIn, stay, fadeOut, null, message);
  }
  
  public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
    NMSUtil.sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
  }
  
  public static boolean hasItem(Player p, String name, String lore) {
    for (int o = 0; o < p.getInventory().getSize(); o++) {
      if (p.getInventory().getItem(o) != null) {
    	if(p.getInventory().getItem(o).hasItemMeta()) {
        	if(p.getInventory().getItem(o).getItemMeta().hasDisplayName()) {
                if (p.getInventory().getItem(o).getItemMeta().getDisplayName().contains(name)) {
                    return true; 
                  }		
        	}
    	}
        if (p.getInventory().getItem(o).getItemMeta().hasLore())
          for (String s : p.getInventory().getItem(o).getItemMeta().getLore()) {
            if (s.contains(lore))
              return true; 
          }  
      } 
    } 
    return false;
  }
}
