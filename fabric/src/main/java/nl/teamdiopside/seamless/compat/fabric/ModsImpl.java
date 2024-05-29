package nl.teamdiopside.seamless.compat.fabric;


import net.fabricmc.loader.api.FabricLoader;

public class ModsImpl {
  public static boolean isModLoaded(String id) {
    return FabricLoader.getInstance().isModLoaded(id);
  }
}