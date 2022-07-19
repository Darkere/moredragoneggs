package com.darkere.moredragoneggs;

import net.minecraft.block.Blocks;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
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

    public static void PlaceDragonHead(BlockPos pos, ServerWorld level) {
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

        for (int i = 0; i < 4; i++) {
            BlockPos headPos = pos.relative(dir);

            // account for possible BetterEnd Reforked style end podiums
            if (level.getBlockState(headPos).getBlock() == Blocks.BEDROCK) {
                headPos = headPos.relative(dir);
            }

            if (level.getBlockState(headPos).getBlock() == Blocks.AIR) {
                BlockPos finalHeadPos = headPos;
                Direction finalDir = dir;
                level.getServer().tell(new TickDelayedTask(0, () -> level.setBlockAndUpdate(finalHeadPos, Blocks.DRAGON_WALL_HEAD.defaultBlockState().setValue(WallSkullBlock.FACING, finalDir))));
                break;
            }

            dir = dir.getClockWise();
        }
    }


}
