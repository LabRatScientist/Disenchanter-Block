package com.github.plabankumarmondal.disenchanter.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class BlockScreen extends CottonInventoryScreen<BlockInventoryScreen> {

  public BlockScreen(BlockInventoryScreen gui, PlayerEntity player, Text title) {
    super(gui, player, title);
  }

}
