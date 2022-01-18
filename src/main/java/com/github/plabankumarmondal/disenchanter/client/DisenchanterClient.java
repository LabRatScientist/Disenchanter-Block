package com.github.plabankumarmondal.disenchanter.client;

import com.github.plabankumarmondal.disenchanter.block.DisenchanterBlockRenderer;
import com.github.plabankumarmondal.disenchanter.gui.BlockInventoryScreen;
import com.github.plabankumarmondal.disenchanter.gui.BlockScreen;
import com.github.plabankumarmondal.disenchanter.init.DisenchanterInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;


@Environment(EnvType.CLIENT)
public class DisenchanterClient implements ClientModInitializer {



  @Override
  public void onInitializeClient() {
    ScreenRegistry.<BlockInventoryScreen, BlockScreen>register(DisenchanterInitializer.SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new BlockScreen(gui, inventory.player, title));


      BlockEntityRendererRegistry.INSTANCE.register(DisenchanterInitializer.DISENCHANTER_BLOCK_ENTITY,
              (BlockEntityRendererFactory.Context rendererDispatcherIn) -> new DisenchanterBlockRenderer());

  }
}
