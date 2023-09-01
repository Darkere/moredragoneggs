package com.darkere.moredragoneggs.mixin;

import com.darkere.moredragoneggs.MoreDragonEggs;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndDragonFight.class)
public class DragonEggMixin {

    @Inject(at = @At("HEAD"),method = "setDragonKilled(Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;)V")
    private void setDragonKilled(EnderDragon p_64086_, CallbackInfo cb) {
        MoreDragonEggs.placeAdditionalEggAndMaybeHead((EndDragonFight)(Object)this);
    }

}
