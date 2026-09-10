package com.saphienyako.haregon_villagers;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue HAREGON_VILLAGERS = BUILDER
            .comment("Whether villagers should look like Haregon Villagers")
            .define("haregon_villagers", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
