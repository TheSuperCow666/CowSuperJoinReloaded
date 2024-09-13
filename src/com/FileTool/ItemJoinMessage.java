package com.FileTool;

import com.CowSuperJoinReloaded.IJM;
import com.CowSuperJoinReloaded.Main;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemJoinMessage {
  private static List<IJM> IJMs;
  
  private static YamlConfiguration yaml;
  
  public static void loadConfig() {
    File file = new File(Main.getinstance().getDataFolder(), "IJM.yml");
    if (!file.exists())
      Main.getinstance().saveResource("IJM.yml", false); 
    yaml = YamlConfiguration.loadConfiguration(file);
  }
  
  public static void load() {
    ConfigurationSection section = yaml.getConfigurationSection("Settings");
    ArrayList<IJM> ar = new ArrayList<>();
    for (String key : section.getKeys(false))
      ar.add(new IJM(key)); 
    IJMs = ar;
  }
  
  public static YamlConfiguration getYaml() {
    return yaml;
  }
  
  public static List<IJM> getIJMs() {
    return IJMs;
  }
}
