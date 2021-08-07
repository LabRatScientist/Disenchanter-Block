package com.github.plabankumarmondal.disenchanter;

import com.github.plabankumarmondal.disenchanter.init.DisenchanterInitializer;
import net.fabricmc.api.ModInitializer;
import software.bernie.geckolib3.GeckoLib;

public class Disenchanter implements ModInitializer {

  public static final String MOD_ID = "disenchanter_block";

  @Override
  public void onInitialize() {
    GeckoLib.initialize();
    DisenchanterInitializer.init();
  }
}
