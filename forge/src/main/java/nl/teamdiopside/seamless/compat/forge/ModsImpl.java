package nl.teamdiopside.seamless.compat.forge;

import net.minecraftforge.fml.loading.LoadingModList;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.util.List;

public class ModsImpl {
  public static boolean isModLoaded(String id) {
    List<ModInfo> mods = LoadingModList.get().getMods();

    for (ModInfo mod : mods) {
      if (mod.getModId().equals(id)) { return true; }
    }
    return false;
  }
}