package nl.teamdiopside.seamless.mixin;

import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.VideoSettingsScreen;
import net.minecraft.network.chat.Component;
import nl.teamdiopside.seamless.Seamless;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoSettingsScreen.class)
public abstract class VideoSettingsScreenMixin extends OptionsSubScreen {

    public VideoSettingsScreenMixin(Screen screen, Options options, Component component) {
        super(screen, options, component);
    }

    @Inject(method = "addOptions", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        list.addSmall(Seamless.fastOption);
    }
}
