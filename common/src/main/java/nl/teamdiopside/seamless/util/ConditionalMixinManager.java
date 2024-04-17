package nl.teamdiopside.seamless.util;

import java.io.IOException;
import java.util.List;

import nl.teamdiopside.seamless.annotation.mixin.ConditionalMixin;
import nl.teamdiopside.seamless.compat.Mods;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.Annotations;

public class ConditionalMixinManager {
  public static final Logger LOGGER = LoggerFactory.getLogger("Seamless/Mixin");

  public static boolean shouldApply(String className) {
    try {
      List<AnnotationNode> annotationNodes = MixinService.getService().getBytecodeProvider().getClassNode(className).visibleAnnotations;
      if (annotationNodes == null) return true;

      boolean shouldApply = true;
      for (AnnotationNode node : annotationNodes) {
        if (node.desc.equals(Type.getDescriptor(ConditionalMixin.class))) {
          List<Mods> mods = Annotations.getValue(node, "mods", true, Mods.class);
          boolean applyIfPresent = Annotations.getValue(node, "applyIfPresent", Boolean.TRUE);
          boolean anyModsLoaded = anyModsLoaded(mods);
          shouldApply = anyModsLoaded == applyIfPresent;

          LOGGER.debug("Mod(s) {} {}loaded. {} {}", mods, anyModsLoaded ? "" : "not ", shouldApply ? "Applying" : "Skipping", className);
        }
      }
      return shouldApply;
    } catch (ClassNotFoundException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean anyModsLoaded(List<Mods> mods) {
    for (Mods mod : mods) {
      if (mod.isLoaded) return true;
    }
    return false;
  }
}