package com.CowSuperJoinReloaded;

import com.FileTool.PermissionJoinMessage;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;

public class PJM {
  private String perm;
  
  private List<String> run;
  
  private String name;
  
  public PJM(String name) {
    YamlConfiguration config = PermissionJoinMessage.getYaml();
    setName(name);
    setPerm(config.getString("Settings." + name + ".perm"));
    setRun(config.getStringList("Settings." + name + ".run"));
  }
  
  public String getPerm() {
    return this.perm;
  }
  
  public void setPerm(String perm) {
    this.perm = perm;
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
}
