package com.saphienyako.haregon_villagers.entity.renderer;

import com.saphienyako.haregon_villagers.HaregonVillagers;
import com.saphienyako.haregon_villagers.entity.layer.HaregonProfessionLayer;
import com.saphienyako.haregon_villagers.entity.model.ZombieHaregonModel;
import net.minecraft.client.model.ZombieVillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.ZombieVillager;

public class ZombieHaregonRenderer extends HumanoidMobRenderer<ZombieVillager, ZombieHaregonModel<ZombieVillager>> {
    private static final ResourceLocation ZOMBIE_VILLAGER_LOCATION = ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID,"textures/entity/zombie_villager/zombie_haregon.png");

    public ZombieHaregonRenderer(EntityRendererProvider.Context contex) {
        super(contex, new ZombieHaregonModel<>(contex.bakeLayer(ZombieHaregonModel.ZOMBIE_HAREGON_LAYER)), 0.5F);
        this.addLayer(new HumanoidArmorLayer(this, new ZombieVillagerModel(contex.bakeLayer(ModelLayers.ZOMBIE_VILLAGER_INNER_ARMOR)), new ZombieVillagerModel(contex.bakeLayer(ModelLayers.ZOMBIE_VILLAGER_OUTER_ARMOR)), contex.getModelManager()));
        this.addLayer(new HaregonProfessionLayer<>(this, contex.getResourceManager(), "villager"));
    }

    public ResourceLocation getTextureLocation(ZombieVillager entity) {
    return ZOMBIE_VILLAGER_LOCATION;
}

    protected boolean isShaking(ZombieVillager entity) {
    return super.isShaking(entity) || entity.isConverting();
}

}
