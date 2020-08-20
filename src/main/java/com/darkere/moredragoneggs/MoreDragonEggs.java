package com.darkere.moredragoneggs;

import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("moredragoneggs")
public class MoreDragonEggs {

    public MoreDragonEggs() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getEntityLiving() == null) return;
        MinecraftServer server = event.getEntity().getServer();
        if (server == null) return;
        if (event.getEntity().getEntityWorld().equals(server.getWorld(World.field_234920_i_))) {
            if (event.getEntity() instanceof EnderDragonEntity) {
                EnderDragonEntity d = (EnderDragonEntity) event.getEntity();
                if (d.getFightManager() != null && d.getFightManager().hasPreviouslyKilledDragon()) {
                    event.getEntity().getEntityWorld().setBlockState(event.getEntity().getEntityWorld().getHeight(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.getDefaultState());
                }
            }
        }
    }
}
