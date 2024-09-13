package com.FileTool;

import com.CowSuperJoinReloaded.Main;
import com.CowSuperJoinReloaded.PJM;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class PermissionJoinMessage {
  private static List<PJM> PJMs;
  
  private static YamlConfiguration yaml;
  
  public static void loadConfig() {
    File file = new File(Main.getinstance().getDataFolder(), "PJM.yml");
    if (!file.exists())
      Main.getinstance().saveResource("PJM.yml", false); 
    yaml = YamlConfiguration.loadConfiguration(file);
  }
  
  public static void load() {
    ConfigurationSection section = yaml.getConfigurationSection("Settings");
    ArrayList<PJM> ar = new ArrayList<>();
    for (String key : section.getKeys(false))
      ar.add(new PJM(key)); 
    PJMs = ar;
  }
  
  public static YamlConfiguration getYaml() {
    return yaml;
  }
  
  public static List<PJM> getPJMs() {
    return PJMs;
  }
}
