package com.saphienyako.haregon_villagers.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.saphienyako.haregon_villagers.HaregonVillagers;
import com.saphienyako.haregon_villagers.entity.model.HaregonModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;

public class HaregonRenderer extends MobRenderer<Villager, HaregonModel<Villager>> {
    private static final ResourceLocation BROWN_HAREGON = ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID, "textures/entity/villager/haregon_brown.png" );
    private static final ResourceLocation BLACK_HAREGON = ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID, "textures/entity/villager/haregon_black.png" );

    private static final ResourceLocation DUSTY_HAREGON = ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID, "textures/entity/villager/haregon_dusty.png" );

    private static final ResourceLocation GOLD_HAREGON = ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID, "textures/entity/villager/haregon_gold.png" );

    private static final ResourceLocation SALT_HAREGON = ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID, "textures/entity/villager/haregon_salt.png" );

    public static final ResourceLocation[] HAREGON_VARIANTS = { BROWN_HAREGON, BLACK_HAREGON, DUSTY_HAREGON, GOLD_HAREGON, SALT_HAREGON};

    public HaregonRenderer(EntityRendererProvider.Context context) {
        super(context, new HaregonModel<>(context.bakeLayer(HaregonModel.HAREGON_LAYER)), 0.5F);
        this.addLayer(new CustomHeadLayer(this, context.getModelSet(), context.getItemInHandRenderer()));
        this.addLayer(new VillagerProfessionLayer(this, context.getResourceManager(), "villager"));
        this.addLayer(new CrossedArmsItemLayer(this, context.getItemInHandRenderer()));
    }

    public ResourceLocation getTextureLocation(Villager entity) {
        return HAREGON_VARIANTS[Math.floorMod(entity.getUUID().hashCode(), HAREGON_VARIANTS.length)];
    }

    protected void scale(Villager livingEntity, PoseStack poseStack, float partialTickTime) {
        float f = 0.9375F * livingEntity.getAgeScale();
        poseStack.scale(f, f, f);
    }

    protected float getShadowRadius(Villager entity) {
        float f = super.getShadowRadius(entity);
        return entity.isBaby() ? f * 0.5F : f;
    }
}
