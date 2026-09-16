package com.saphienyako.haregon_villagers.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.saphienyako.haregon_villagers.HaregonVillagers;
import com.saphienyako.haregon_villagers.entity.model.HaregonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.WanderingTrader;
import org.jetbrains.annotations.NotNull;

public class WanderingHaregonTraderRenderer  extends MobRenderer<WanderingTrader, HaregonModel<WanderingTrader>> {

    private static final ResourceLocation WANDERING_HAREGON_TRADER = ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID, "textures/entity/wandering_trader.png");

    public WanderingHaregonTraderRenderer(EntityRendererProvider.Context context) {
        super(context, new HaregonModel<>(context.bakeLayer(HaregonModel.HAREGON_LAYER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull WanderingTrader wanderingTrader) {
        return WANDERING_HAREGON_TRADER;
    }

    protected void scale(WanderingTrader livingEntity, PoseStack poseStack, float partialTickTime) {
        float f = 0.9375F * livingEntity.getAgeScale();
        poseStack.scale(f, f, f);
    }

    protected float getShadowRadius(@NotNull WanderingTrader entity) {
        float f = super.getShadowRadius(entity);
        return entity.isBaby() ? f * 0.5F : f;
    }
}
