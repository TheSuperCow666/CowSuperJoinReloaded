package com.CowSuperJoinReloaded;

import com.FileTool.ItemJoinMessage;
import com.FileTool.PermissionJoinMessage;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinEvent implements Listener {
  @EventHandler
  public void Join(PlayerJoinEvent e) {
    e.setJoinMessage(null);
    Player p = e.getPlayer();
    for (PJM pjm : PermissionJoinMessage.getPJMs()) {
      if (p.hasPermission(pjm.getPerm())) {
        CompletableFuture.supplyAsync(() -> {
              for (String s : pjm.getRun()) {
                final Location lo = p.getLocation().add(Double.parseDouble(Tool.getProperty("offx", s)), Double.parseDouble(Tool.getProperty("offy", s)), Double.parseDouble(Tool.getProperty("offz", s)));
                if (Tool.getPropertyType(s).equals("delay")) {
                  int delaytime = Integer.parseInt(Tool.getProperty("time", s));
                  try {
                    Thread.sleep(delaytime);
                  } catch (InterruptedException e1) {
                    Thread.currentThread().interrupt();
                  } 
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("sendTitle")) {
                  for (Player op : Bukkit.getOnlinePlayers())
                    Tool.sendFullTitle(op, Integer.valueOf(Integer.parseInt(Tool.getProperty("fadeIn", s))), Integer.valueOf(Integer.parseInt(Tool.getProperty("stay", s))), Integer.valueOf(Integer.parseInt(Tool.getProperty("fadeOut", s))), Tool.getProperty("maintitle", s).replace("%player%", p.getName()), Tool.getProperty("subtitle", s).replace("%player%", p.getName())); 
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("lightning")) {
                  p.getWorld().strikeLightningEffect(lo);
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("playSound")) {
                  for (Player op : Bukkit.getOnlinePlayers())
                    op.playSound(op.getLocation(), Sound.valueOf(Tool.getProperty("effect", s)), 1.0F, 1.0F); 
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("firework")) {
                  (new BukkitRunnable() {
                      public void run() {
                        p.getWorld().spawnEntity(lo, EntityType.FIREWORK);
                        cancel();
                      }
                    }).runTask((Plugin)Main.getinstance());
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("explode")) {
                  p.getWorld().createExplosion(lo, 0.0F, false);
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("sendMessage")) {
                  for (Player op : Bukkit.getOnlinePlayers())
                    Tool.sendMessage((CommandSender)op, Tool.getProperty("content", s).replace("%player%", p.getName())); 
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("particle"))
                  NMSUtil.spawnParticle(Tool.getProperty("effect", s), p, (float)lo.getX(), (float)lo.getY(), (float)lo.getZ(), Float.parseFloat(Tool.getProperty("speed", s)), Integer.parseInt(Tool.getProperty("count", s)), Boolean.parseBoolean(Tool.getProperty("allsee", s))); 
              } 
              return null;
            });
        return;
      } 
    } 
  }
  
  @EventHandler
  public void Join2(PlayerJoinEvent e) {
    e.setJoinMessage(null);
    Player p = e.getPlayer();
    for (IJM ijm : ItemJoinMessage.getIJMs()) {
      if (Tool.hasItem(p, ijm.getItem_name(), ijm.getItem_lore())) {
        CompletableFuture.supplyAsync(() -> {
              for (String s : ijm.getRun()) {
                final Location lo = p.getLocation().add(Double.parseDouble(Tool.getProperty("offx", s)), Double.parseDouble(Tool.getProperty("offy", s)), Double.parseDouble(Tool.getProperty("offz", s)));
                if (Tool.getPropertyType(s).equals("delay")) {
                  int delaytime = Integer.parseInt(Tool.getProperty("time", s));
                  try {
                    Thread.sleep(delaytime);
                  } catch (InterruptedException e1) {
                    Thread.currentThread().interrupt();
                  } 
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("sendTitle")) {
                  for (Player op : Bukkit.getOnlinePlayers())
                    Tool.sendFullTitle(op, Integer.valueOf(Integer.parseInt(Tool.getProperty("fadeIn", s))), Integer.valueOf(Integer.parseInt(Tool.getProperty("stay", s))), Integer.valueOf(Integer.parseInt(Tool.getProperty("fadeOut", s))), Tool.getProperty("maintitle", s).replace("%player%", p.getName()), Tool.getProperty("subtitle", s).replace("%player%", p.getName())); 
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("lightning")) {
                  p.getWorld().strikeLightningEffect(lo);
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("playSound")) {
                  for (Player op : Bukkit.getOnlinePlayers())
                    op.playSound(op.getLocation(), Sound.valueOf(Tool.getProperty("effect", s)), 1.0F, 1.0F); 
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("firework")) {
                  (new BukkitRunnable() {
                      public void run() {
                        p.getWorld().spawnEntity(lo, EntityType.FIREWORK);
                        cancel();
                      }
                    }).runTask((Plugin)Main.getinstance());
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("explode")) {
                  p.getWorld().createExplosion(lo, 0.0F, false);
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("sendMessage")) {
                  for (Player op : Bukkit.getOnlinePlayers())
                    Tool.sendMessage((CommandSender)op, Tool.getProperty("content", s).replace("%player%", p.getName())); 
                  continue;
                } 
                if (Tool.getPropertyType(s).equals("particle"))
                  NMSUtil.spawnParticle(Tool.getProperty("effect", s), p, (float)lo.getX(), (float)lo.getY(), (float)lo.getZ(), Float.parseFloat(Tool.getProperty("speed", s)), Integer.parseInt(Tool.getProperty("count", s)), Boolean.parseBoolean(Tool.getProperty("allsee", s))); 
              } 
              return null;
            });
        return;
      } 
    } 
  }
}
