package com.saphienyako.haregon_villagers;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HaregonVillagers.MOD_ID);

    @SuppressWarnings("unused")
    public static final Supplier<CreativeModeTab> HAREGON_VILLAGERS_TAB =
            CREATIVE_MODE_TABS.register("haregon_villagers_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creative_tab.haregon_villagers_creative_tab"))
                    .icon(() -> new ItemStack(Items.CARROT))
                    .displayItems((displayParameters, output) -> {

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
