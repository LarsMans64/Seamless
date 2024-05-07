package nl.teamdiopside.seamless.compat;

import dev.architectury.injectables.annotations.ExpectPlatform;

/**
 * For compatibility with and without another mod present, we have to define load conditions of the specific code
 */
public enum Mods {
  UPGRADE_AQUATIC("upgrade_aquatic"),
  SODIUM("sodium"),
  RUBIDIUM("rubidium"),
  EMBEDDIUM("embeddium");

  public final boolean isLoaded;
  public final String modId;

  Mods(String modId) {
    this.modId = modId;
    this.isLoaded = isModLoaded(modId);
  }

  @ExpectPlatform
  public static boolean isModLoaded(String id) {
    throw new AssertionError();
  }
}