package com.CowSuperJoinReloaded;

import com.FileTool.ItemJoinMessage;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;

public class IJM {
  private String item_name;
  
  private String item_lore;
  
  private List<String> run;
  
  private String name;
  
  public IJM(String name) {
    YamlConfiguration config = ItemJoinMessage.getYaml();
    setName(name);
    setItem_name(config.getString("Settings." + name + ".Item.name"));
    setItem_lore(config.getString("Settings." + name + ".Item.lore"));
    setRun(config.getStringList("Settings." + name + ".run"));
  }
  
  public List<String> getRun() {
    return this.run;
  }
  
  public void setRun(List<String> run) {
    this.run = run;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getItem_name() {
    return this.item_name;
  }
  
  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }
  
  public String getItem_lore() {
    return this.item_lore;
  }
  
  public void setItem_lore(String item_lore) {
    this.item_lore = item_lore;
  }
}
