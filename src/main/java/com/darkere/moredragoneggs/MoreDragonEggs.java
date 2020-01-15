package com.darkere.moredragoneggs;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.world.dimension.EndDimension;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("moredragoneggs")
public class MoreDragonEggs {

    public MoreDragonEggs() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event) {
        if(event.getEntity().getEntityWorld().getDimension() instanceof EndDimension){
            if (event.getEntity() instanceof EnderDragonEntity) {
                EnderDragonEntity d = (EnderDragonEntity) event.getEntity();
                if (d.getFightManager() != null && d.getFightManager().hasPreviouslyKilledDragon()) {
                    event.getEntity().getEntityWorld().setBlockState(event.getEntity().getEntityWorld().getHeight(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.getDefaultState());
                }
            }
        }
    }
}
