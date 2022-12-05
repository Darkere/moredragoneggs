package com.darkere.moredragoneggs.quilt;

import com.darkere.moredragoneggs.fabriclike.MoreDragonEggsFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class MoreDragonEggsQuilt implements ModInitializer {

  @Override
  public void onInitialize(ModContainer mod) {
    MoreDragonEggsFabricLike.init();
  }
}
