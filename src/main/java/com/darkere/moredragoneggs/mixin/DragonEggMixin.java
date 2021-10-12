package com.darkere.moredragoneggs.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DragonFightManager.class)
public class DragonEggMixin {

    @Shadow
    private
    boolean previouslyKilled;
    @Shadow
    @Final
    private
    ServerWorld level;


    @Inject(at = @At("HEAD"),method = "setDragonKilled(Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;)V")
    private void setDragonKilled(EnderDragonEntity p_186096_1_, CallbackInfo cb) {
        if (this.previouslyKilled) {
            this.level.setBlockAndUpdate(this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.defaultBlockState());
        }
    }
}
