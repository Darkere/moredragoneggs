package com.darkere.moredragoneggs.mixin;

import com.darkere.moredragoneggs.MoreDragonEggs;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndDragonFight.class)
public class DragonEggMixin {

    @Shadow
    private
    boolean previouslyKilled;
    @Shadow
    @Final
    private
    ServerLevel level;


    @Inject(at = @At("HEAD"),method = "setDragonKilled(Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;)V")
    private void setDragonKilled(EnderDragon p_64086_, CallbackInfo cb) {
        placeAdditionalEggAndMaybeHead();
    }

    protected void placeAdditionalEggAndMaybeHead() {
        BlockPos pos = this.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION);
        while (this.level.getBlockState(pos).getBlock() != Blocks.AIR){
            pos = pos.above();
        }
        if (this.previouslyKilled) {
            this.level.setBlockAndUpdate(pos, Blocks.DRAGON_EGG.defaultBlockState());
        }
        MoreDragonEggs.PlaceDragonHead( pos, this.level);
    }

}
