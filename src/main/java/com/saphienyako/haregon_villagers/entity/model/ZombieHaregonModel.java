package com.saphienyako.haregon_villagers.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.saphienyako.haregon_villagers.HaregonVillagers;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.jetbrains.annotations.NotNull;

public class ZombieHaregonModel <T extends ZombieVillager> extends HumanoidModel<T> implements VillagerHeadModel{
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation ZOMBIE_HAREGON_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID, "zombie_haregon"), "main");

    private final ModelPart root;
   // private final ModelPart head;
    private final ModelPart snout;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    private final ModelPart headwear;
    private final ModelPart headwear2;
    private final ModelPart rotation;
    private final ModelPart nose;
  //  private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart bodywear;
 //   private final ModelPart right_leg;
  //  private final ModelPart left_leg;
 //   private final ModelPart left_arm;
  //  private final ModelPart right_arm;

    public ZombieHaregonModel(ModelPart root) {
        super(root);

        this.root = root;
    //    this.head = root.getChild("head");
        this.snout = this.head.getChild("snout");
        this.left_ear = this.head.getChild("left_ear");
        this.right_ear = this.head.getChild("right_ear");
        this.headwear = root.getChild("headwear");
        this.headwear2 = root.getChild("headwear2");
        this.rotation = this.headwear2.getChild("rotation");
        this.nose = root.getChild("nose");
     //   this.body = root.getChild("body");
        this.tail = this.body.getChild("tail");
        this.bodywear = root.getChild("bodywear");
    //    this.right_leg = root.getChild("right_leg");
    //    this.left_leg = root.getChild("left_leg");
    //    this.left_arm = root.getChild("left_arm");
    //    this.right_arm = root.getChild("right_arm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(56, 4).addBox(4.0F, -5.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).mirror().addBox(-7.0F, -5.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(24, 3).addBox(-3.0F, -0.25F, -0.75F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.75F, -5.25F));

        PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -7.0F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -9.0F, 0.5F, 0.0F, 0.0F, 0.1309F));

        PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -7.0F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -9.0F, 0.5F, 0.0F, 0.0F, -0.1309F));

        PartDefinition headwear = partdefinition.addOrReplaceChild("headwear", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition headwear2 = partdefinition.addOrReplaceChild("headwear2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rotation = headwear2.addOrReplaceChild("rotation", CubeListBuilder.create().texOffs(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition nose = partdefinition.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(22, 38).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(54, 17).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 17).addBox(-2.0F, 2.0F, -1.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(32, 18).addBox(-2.0F, 1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 3.0F));

        PartDefinition bodywear = partdefinition.addOrReplaceChild("bodywear", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).addBox(2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(44, 22).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        //Is this needed?
        PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity,limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, entity.isAggressive(), this.attackTime, ageInTicks);

        this.headwear.yRot = this.head.yRot;
        this.headwear.xRot = this.head.xRot;
        this.headwear.zRot = this.head.zRot;

        this.headwear2.yRot = this.head.yRot;
        this.headwear2.xRot = this.head.xRot;
        this.headwear2.zRot = this.head.zRot;

    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        super.renderToBuffer(poseStack,vertexConsumer, packedLight, packedOverlay, color);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        headwear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        headwear2.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        bodywear.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   //     right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   //     left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   //     left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
   //     right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void hatVisible(boolean visible) {
        this.headwear.visible = visible;
        this.headwear2.visible = visible;
    }
}