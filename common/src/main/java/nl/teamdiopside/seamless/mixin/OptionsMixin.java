package nl.teamdiopside.seamless.mixin;

import net.minecraft.client.Options;
import nl.teamdiopside.seamless.Seamless;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class OptionsMixin {
    @Inject(method = "processOptions", at = @At("HEAD"))
    private void processOptions(Options.FieldAccess fieldAccess, CallbackInfo ci) {
        if (!Seamless.modIds.contains("optifine")) {
            fieldAccess.process("fastSeamless", Seamless.fastOption);
        }
    }
}
