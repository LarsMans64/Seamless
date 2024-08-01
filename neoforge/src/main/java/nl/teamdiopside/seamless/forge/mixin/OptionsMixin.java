package nl.teamdiopside.seamless.forge.mixin;

import net.minecraft.client.Options;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import nl.teamdiopside.seamless.Seamless;
import nl.teamdiopside.seamless.forge.SeamlessForgeClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Mixin(Options.class)
public abstract class OptionsMixin {

    @Shadow public List<String> resourcePacks;

    @Inject(method = "load(Z)V", at = @At("RETURN"))
    private void update(CallbackInfo ci) {
        if (!SeamlessForgeClient.file.exists() && !resourcePacks.contains(Seamless.RESOURCE_PACK_FORGE)) {
            resourcePacks.add(Seamless.RESOURCE_PACK_FORGE);
        }
    }

    @Inject(method = "updateResourcePacks", at = @At("HEAD"))
    private void update(PackRepository arg, CallbackInfo ci) {
        for (Pack pack : arg.getSelectedPacks()) {
            if (pack.getId().equals(Seamless.RESOURCE_PACK_FORGE)) {
                SeamlessForgeClient.file.delete();
            } else {
                try {
                    SeamlessForgeClient.file.createNewFile();
                    FileWriter writer = new FileWriter(SeamlessForgeClient.file);
                    writer.write("This file makes sure the seamless default resource pack is disabled for you. You probably want to delete this file.");
                    writer.close();
                }
                catch (IOException ignored) {}
            }
        }
    }
}
