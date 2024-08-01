package nl.teamdiopside.seamless.forge;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import nl.teamdiopside.seamless.Reload;
import nl.teamdiopside.seamless.Seamless;

import java.io.File;
import java.util.Map;
import java.util.function.Consumer;

@Mod(value = Seamless.MOD_ID, dist = Dist.CLIENT)
public class SeamlessForgeClient {

    public static File file = new File(Minecraft.getInstance().gameDirectory, "seamless.txt");

    public SeamlessForgeClient() {

        Consumer<AddReloadListenerEvent> reloadListener = addReloadListenerEvent -> addReloadListenerEvent.addListener(new SimpleJsonResourceReloadListener(new Gson(), "nothing") {
            @Override
            protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager arg, ProfilerFiller arg2) {
                reloadOutlines();
            }
        });

        Consumer<TagsUpdatedEvent> tags = tagsUpdatedEvent -> {
            if (tagsUpdatedEvent.getUpdateCause() == TagsUpdatedEvent.UpdateCause.SERVER_DATA_LOAD) {
                reloadOutlines();
            }
        };

        NeoForge.EVENT_BUS.addListener(reloadListener);
        NeoForge.EVENT_BUS.addListener(tags);

//        NeoForge.EVENT_BUS.register(SeamlessForgeClientModEvents.class);

        Seamless.init();
    }

    public static void reloadOutlines() {
        Reload.reload(Minecraft.getInstance().getResourceManager());
    }

}

