package com.github.plabankumarmondal.disenchanter.block;

import com.github.plabankumarmondal.disenchanter.Disenchanter;
import com.github.plabankumarmondal.disenchanter.block.entity.DisenchanterBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DisenchanterBlockModel extends AnimatedGeoModel<DisenchanterBlockEntity> {

  @Override
  public Identifier getModelLocation(DisenchanterBlockEntity object) {
    return new Identifier(Disenchanter.MOD_ID, "geo/disenchanter_block.geo.json");
  }

  @Override
  public Identifier getTextureLocation(DisenchanterBlockEntity object) {
    return new Identifier(Disenchanter.MOD_ID, "textures/block/disenchanter_block.png");
  }

  @Override
  public Identifier getAnimationFileLocation(DisenchanterBlockEntity animatable) {
    return new Identifier(Disenchanter.MOD_ID, "animations/disenchanter_block.animation.json");
  }
}
