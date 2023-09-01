package com.darkere.moredragoneggs.mixin;

import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.teamabnormals.endergetic.common.world.EndergeticDragonFightManager")
public class EndergeticDragonEggMixin extends DragonEggMixin {


    @Inject(at = @At("HEAD"),method = "setDragonKilled(Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;)V")
    private void setDragonKilled(EnderDragon p_64086_, CallbackInfo cb) {
        this.placeAdditionalEggAndMaybeHead();
    }
}
