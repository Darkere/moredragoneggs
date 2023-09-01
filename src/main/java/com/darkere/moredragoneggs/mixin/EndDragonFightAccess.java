package com.darkere.moredragoneggs.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EndDragonFight.class)
public interface EndDragonFightAccess {

    @Accessor("level")
    ServerLevel moredragoneggs_getLevel();
}
