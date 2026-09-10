package com.saphienyako.haregon_villagers;

import com.saphienyako.haregon_villagers.entity.model.HaregonModel;
import com.saphienyako.haregon_villagers.entity.model.ZombieHaregonModel;
import com.saphienyako.haregon_villagers.entity.renderer.HaregonRenderer;
import com.saphienyako.haregon_villagers.entity.renderer.ZombieHaregonRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = HaregonVillagers.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = HaregonVillagers.MOD_ID, value = Dist.CLIENT)
public class HaregonVillagersClient {

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        HaregonVillagers.LOGGER.info("A HAREGON GREETS YOU! WHAT DO YOU DO?");
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        if(Config.HAREGON_VILLAGERS.get()) {
            event.registerLayerDefinition(HaregonModel.HAREGON_LAYER, HaregonModel::createBodyLayer);
            event.registerLayerDefinition(ZombieHaregonModel.ZOMBIE_HAREGON_LAYER, ZombieHaregonModel::createBodyLayer);
        }
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

        if(Config.HAREGON_VILLAGERS.get()) {
            event.registerEntityRenderer(EntityType.VILLAGER, HaregonRenderer::new);
            event.registerEntityRenderer(EntityType.ZOMBIE_VILLAGER, ZombieHaregonRenderer::new);
        }
    }
}
