package com.darkere.moredragoneggs.mixin;

import com.darkere.moredragoneggs.MoreDragonEggs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.teamabnormals.endergetic.common.world.EndergeticDragonFightManager")
public class EndergeticDragonEggMixin extends EndDragonFight {

    public EndergeticDragonEggMixin(ServerLevel level, long seed, CompoundTag fightData) {
        super(level, seed, fightData);
    }

    @Inject(at = @At("HEAD"),method = "setDragonKilled(Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;)V")
    private void setDragonKilled(EnderDragon p_64086_, CallbackInfo cb) {
        MoreDragonEggs.placeAdditionalEggAndMaybeHead(this);
    }
}
