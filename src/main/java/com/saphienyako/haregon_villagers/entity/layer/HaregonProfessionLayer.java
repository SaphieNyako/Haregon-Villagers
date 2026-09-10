package com.saphienyako.haregon_villagers.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.saphienyako.haregon_villagers.HaregonVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.VillagerHeadModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.metadata.animation.VillagerMetaDataSection;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.neoforged.fml.ModList;

import java.io.IOException;
import java.util.Optional;

public class HaregonProfessionLayer<T extends LivingEntity & VillagerDataHolder, M extends EntityModel<T> & VillagerHeadModel> extends RenderLayer<T, M> {

    //This class is based on VillagerProfessionLayer
    private static final Int2ObjectMap<ResourceLocation> LEVEL_LOCATIONS = Util.make(new Int2ObjectOpenHashMap<>(), map -> {
                    map.put(1, ResourceLocation.withDefaultNamespace("stone"));
                    map.put(2, ResourceLocation.withDefaultNamespace("iron"));
                    map.put(3, ResourceLocation.withDefaultNamespace("gold"));
                    map.put(4, ResourceLocation.withDefaultNamespace("emerald"));
                    map.put(5, ResourceLocation.withDefaultNamespace("diamond"));

                });

    private final Object2ObjectMap<VillagerType, VillagerMetaDataSection.Hat> typeHatCache = new Object2ObjectOpenHashMap<>();
    private final Object2ObjectMap<VillagerProfession, VillagerMetaDataSection.Hat> professionHatCache = new Object2ObjectOpenHashMap<>();
    private final ResourceManager resourceManager;
    private final String path;

    public HaregonProfessionLayer(RenderLayerParent<T, M> renderer, ResourceManager resourceManager, String path) {
        super(renderer);
        this.resourceManager = resourceManager;
        this.path = path;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.isInvisible()) {
            VillagerData villagerData = (livingEntity).getVillagerData();
            VillagerType villagerType = villagerData.getType();
            VillagerProfession villagerProfession = villagerData.getProfession();
            VillagerMetaDataSection.Hat villagermetadatasection$hat = this.getHatData(this.typeHatCache, "type", BuiltInRegistries.VILLAGER_TYPE, villagerType);
            VillagerMetaDataSection.Hat villagermetadatasection$hat1 = this.getHatData(this.professionHatCache, "profession", BuiltInRegistries.VILLAGER_PROFESSION, villagerProfession);
            M model = this.getParentModel();

            ((VillagerHeadModel)model).hatVisible(villagermetadatasection$hat1 == VillagerMetaDataSection.Hat.NONE || villagermetadatasection$hat1 == VillagerMetaDataSection.Hat.PARTIAL && villagermetadatasection$hat != VillagerMetaDataSection.Hat.FULL);
            ResourceLocation resourcelocation = this.getResourceLocation("type", BuiltInRegistries.VILLAGER_TYPE.getKey(villagerType));
            renderColoredCutoutModel(model, resourcelocation, poseStack, buffer, packedLight, livingEntity, -1);
            model.hatVisible(true);

            if (villagerProfession != VillagerProfession.NONE && !livingEntity.isBaby()) {
                ResourceLocation professionTexture = this.getResourceLocation("profession", BuiltInRegistries.VILLAGER_PROFESSION.getKey(villagerProfession));
                renderColoredCutoutModel(model, professionTexture, poseStack, buffer, packedLight, livingEntity, -1);

                if (villagerProfession != VillagerProfession.NITWIT) {
                    ResourceLocation levelTexture = this.getProfessionLevelLocation(LEVEL_LOCATIONS.get(Mth.clamp(villagerData.getLevel(), 1, LEVEL_LOCATIONS.size())));
                    renderColoredCutoutModel(model, levelTexture, poseStack, buffer, packedLight, livingEntity, -1);
                }
            }
        }
    }

    private ResourceLocation getResourceLocation(String folder, ResourceLocation location) {

        boolean isChefsDelightProfession = ModList.get().isLoaded("chefsdelight") && folder.equals("profession")  && (location.getPath().equals("cook") || location.getPath().equals("chef"));
        boolean isVillagersPlusProfession = ModList.get().isLoaded("villagersplus") && folder.equals("profession") && location.getNamespace().equals("villagersplus");
        boolean isCreateBetterVillagersProfession = ModList.get().isLoaded("create_better_villagers") && folder.equals("profession") && location.getNamespace().equals("create_better_villagers");
        boolean isBeekeeperHutProfession = ModList.get().isLoaded("beekeeperhut") && folder.equals("profession") && location.getPath().equals("beekeeper");
        boolean isSawmillProfession = ModList.get().isLoaded("sawmill") && folder.equals("profession") && location.getPath().equals("carpenter");
        boolean isMoreVillagersProfession = ModList.get().isLoaded("morevillagers") && folder.equals("profession") && location.getNamespace().equals("morevillagers");
        boolean isMushroomVillagerProfession = ModList.get().isLoaded("mushroom_villager_trader") && folder.equals("profession") && location.getNamespace().equals("mushroom_villager_trader");

        if(isChefsDelightProfession || isVillagersPlusProfession || isCreateBetterVillagersProfession || isBeekeeperHutProfession || isSawmillProfession || isMoreVillagersProfession || isMushroomVillagerProfession){
            return location.withPath((path) -> {
                return "textures/entity/" + this.path + "/" + folder + "/" + path + ".png";
            });
        } else {
            return ResourceLocation.fromNamespaceAndPath(HaregonVillagers.MOD_ID, "textures/entity/" + this.path + "/" + folder + "/" + location.getPath() + ".png");
        }
    }

    private ResourceLocation getProfessionLevelLocation(ResourceLocation location) {
        return ResourceLocation.withDefaultNamespace("textures/entity/villager/profession_level/" + location.getPath() + ".png");
    }

    public <K> VillagerMetaDataSection.Hat getHatData(Object2ObjectMap<K, VillagerMetaDataSection.Hat> cache, String folder, DefaultedRegistry<K> registry, K key) {
        return cache.computeIfAbsent(key, value -> {

            ResourceLocation texture = this.getResourceLocation(folder, registry.getKey(key));

            return this.resourceManager
                    .getResource(texture)
                    .flatMap(resource -> {
                        try {
                            return resource.metadata()
                                    .getSection(VillagerMetaDataSection.SERIALIZER)
                                    .map(VillagerMetaDataSection::getHat);
                        } catch (IOException e) {
                            return Optional.empty();
                        }
                    }).orElse(VillagerMetaDataSection.Hat.NONE);
        });
    }
}
