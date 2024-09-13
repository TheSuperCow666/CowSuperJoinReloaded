package com.CowSuperJoinReloaded;

import com.FileTool.ItemJoinMessage;
import com.FileTool.PermissionJoinMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  public static JavaPlugin instance;
  
  public static String prefix = "&7<&fCowSuperJoin&7> ";
  
  public void onEnable() {
    instance = this;
    Tool.sendConsoleMessage(String.valueOf(prefix) + "&a插件重载成功！");
    Tool.sendConsoleMessage(String.valueOf(prefix) + "&a插件作者：Cow");
    Tool.sendConsoleMessage(String.valueOf(prefix) + "&aQQ2529019087");
    Bukkit.getPluginCommand("CowSuperJoin").setExecutor(new Command());
    getinstance().saveDefaultConfig();
    getinstance().reloadConfig();
    PermissionJoinMessage.loadConfig();
    PermissionJoinMessage.load();
    ItemJoinMessage.loadConfig();
    ItemJoinMessage.load();
    Bukkit.getPluginManager().registerEvents(new JoinEvent(), (Plugin)this);
  }
  
  public void onLoad() {
    Tool.sendConsoleMessage(String.valueOf(prefix) + "&6插件重载中...");
  }
  
  public void onDisable() {
    Tool.sendConsoleMessage(String.valueOf(prefix) + "&c插件卸载成功！");
  }
  
  public static JavaPlugin getinstance() {
    return instance;
  }
}
