package com.darkere.moredragoneggs;

import com.darkere.moredragoneggs.mixin.EndDragonFightAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
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

        for (int i = 0; i < 4; i++) {
            BlockPos headPos = pos.relative(dir);

            // account for possible BetterEnd Reforked style end podiums
            if (level.getBlockState(headPos).getBlock() == Blocks.BEDROCK) {
                headPos = headPos.relative(dir);
            }

            if (level.getBlockState(headPos).getBlock() == Blocks.AIR) {
                BlockPos finalHeadPos = headPos;
                Direction finalDir = dir;
                level.getServer().tell(new TickTask(0, () -> level.setBlockAndUpdate(finalHeadPos, Blocks.DRAGON_WALL_HEAD.defaultBlockState().setValue(WallSkullBlock.FACING, finalDir))));
                break;
            }

            dir = dir.getClockWise();
        }
    }


    public static void placeAdditionalEggAndMaybeHead(EndDragonFight dragonFight) {
        ServerLevel level = ((EndDragonFightAccess)dragonFight).moredragoneggs_getLevel();
        BlockPos pos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION);
        while (level.getBlockState(pos).getBlock() != Blocks.AIR){
            pos = pos.above();
        }
        if (dragonFight.hasPreviouslyKilledDragon()) {
            level.setBlockAndUpdate(pos, Blocks.DRAGON_EGG.defaultBlockState());
        }
        PlaceDragonHead( pos, level);
    }
}
