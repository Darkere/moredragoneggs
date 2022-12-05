package com.darkere.moredragoneggs;

import com.darkere.moredragoneggs.config.ModServerConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallSkullBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreDragonEggs {

  public static final String MOD_ID = "moredragoneggs";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
  public static final ModServerConfigs SERVER_CONFIG = new ModServerConfigs();

  public static void PlaceDragonHead(BlockPos pos, ServerLevel level) {
    if (!SERVER_CONFIG.shouldSpawnHead()) {
      return;
    }

    while (level.getBlockState(pos).getBlock() != Blocks.BEDROCK && pos.getY() > 0) {
      pos = pos.below();
    }

    Entity e = level.getNearestPlayer(
        pos.getX(),
        pos.getY(),
        pos.getZ(),
        -1,
        null
    );

    Direction dir;
    if (e != null) {
      dir = Direction.getNearest(
          e.getX() - pos.getX(),
          0,
          e.getZ() - pos.getZ()
      );
    } else {
      dir = Direction.NORTH;
    }

    for (int i = 0; i < 4; i++) {
      BlockPos headPos = pos.relative(dir);

      // account for possible BetterEnd Reforked style end podiums
      if (level.getBlockState(headPos).getBlock() == Blocks.BEDROCK) {
        headPos = headPos.relative(dir);
      }

      if (level.getBlockState(headPos).getBlock() == Blocks.AIR) {
        BlockPos finalHeadPos = headPos;
        Direction finalDir = dir;
        level.getServer().tell(new TickTask(
            0,
            () -> level.setBlockAndUpdate(
                finalHeadPos,
                Blocks.DRAGON_WALL_HEAD.defaultBlockState().setValue(
                    WallSkullBlock.FACING,
                    finalDir
                )
            )
        ));
        break;
      }

      dir = dir.getClockWise();
    }
  }

  public static void init() {
    LOGGER.debug(MOD_ID + "initialized");
  }
}
