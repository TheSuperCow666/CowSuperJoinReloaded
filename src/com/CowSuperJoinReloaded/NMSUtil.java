package com.CowSuperJoinReloaded;

import java.lang.reflect.Constructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NMSUtil {
  public static void sendPacket(Player player, Object packet) {
    try {
      Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
      Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
      playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public static Class<?> getNMSClass(String name) {
    String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    try {
      return Class.forName("net.minecraft.server." + version + "." + name);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public static Class<?> getCraftClass(String name) {
    String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    try {
      return Class.forName("net.minecraft.craftbukkit." + version + "." + name);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public static String getNMSName(String name) {
    String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    return "net.minecraft.server." + version + "." + name;
  }
  
  public static void spawnParticle(String particle, Player p, float x, float y, float z, float speed, int count, boolean allsee) {
    Class<?> clazz = getNMSClass("PacketPlayOutWorldParticles");
    Class<?> clazz2 = getNMSClass("EnumParticle");
    try {
      Object enumparticle = clazz2.getDeclaredMethod("a", new Class[] { String.class }).invoke(p, new Object[] { particle.toLowerCase() });
      Object par = clazz.getConstructor(new Class[] { 
            clazz2, 
            boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class, 
            int[].class }).newInstance(new Object[] { 
            enumparticle, Boolean.valueOf(false), Float.valueOf(x), Float.valueOf(y), Float.valueOf(z), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Float.valueOf(speed), Integer.valueOf(count), 
            new int[1] });
      if (allsee) {
        for (Player ep : Bukkit.getOnlinePlayers())
          sendPacket(ep, par); 
      } else {
        sendPacket(p, par);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
    try {
      if (title != null) {
        title = ChatColor.translateAlternateColorCodes('&', title);
        title = title.replaceAll("%player%", player.getDisplayName());
        Object e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
        Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
        Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class });
        Object titlePacket = subtitleConstructor.newInstance(new Object[] { e, chatTitle, fadeIn, stay, fadeOut });
        sendPacket(player, titlePacket);
        e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
        chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
        subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent") });
        titlePacket = subtitleConstructor.newInstance(new Object[] { e, chatTitle });
        sendPacket(player, titlePacket);
      } 
      if (subtitle != null) {
        subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
        subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
        Object e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
        Object chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
        Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class });
        Object subtitlePacket = subtitleConstructor.newInstance(new Object[] { e, chatSubtitle, fadeIn, stay, fadeOut });
        sendPacket(player, subtitlePacket);
        e = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
        chatSubtitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
        subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class });
        subtitlePacket = subtitleConstructor.newInstance(new Object[] { e, chatSubtitle, fadeIn, stay, fadeOut });
        sendPacket(player, subtitlePacket);
      } 
    } catch (Exception var11) {
      var11.printStackTrace();
    } 
  }
}
