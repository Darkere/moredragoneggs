package com.darkere.moredragoneggs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("moredragoneggs")
public class MoreDragonEggs {

    public static final ServerConfig SERVER_CONFIG = new ServerConfig();

    public MoreDragonEggs() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG.spec);


    }

    public static void PlaceDragonHead(BlockPos pos, ServerLevel level) {
        if (!SERVER_CONFIG.shouldSpawnHead())
            return;

        while (level.getBlockState(pos).getBlock() != Blocks.BEDROCK && pos.getY() > 0) {
            pos = pos.below();
        }

        Entity e = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), -1, null);

        Direction dir;
        if (e != null)
            dir = Direction.getNearest(e.getX() - pos.getX(), 0, e.getZ() - pos.getZ());
        else
            dir = Direction.NORTH;

        BlockPos headPos = pos.relative(dir);
        int i = 0;
        while (level.getBlockState(headPos).getBlock() != Blocks.AIR && i < 4) {
            dir = dir.getClockWise();
            headPos = pos.relative(dir);
            i++;
        }

        if (i > 3)
            return;

        level.setBlockAndUpdate(headPos, Blocks.DRAGON_WALL_HEAD.defaultBlockState().setValue(WallSkullBlock.FACING, dir));



    }


}
