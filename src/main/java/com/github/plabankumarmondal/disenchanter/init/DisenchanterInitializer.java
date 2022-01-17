package com.github.plabankumarmondal.disenchanter.init;

import com.github.plabankumarmondal.disenchanter.Disenchanter;
import com.github.plabankumarmondal.disenchanter.block.DisenchanterBlock;
import com.github.plabankumarmondal.disenchanter.block.entity.DisenchanterBlockEntity;
import com.github.plabankumarmondal.disenchanter.gui.BlockInventoryScreen;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DisenchanterInitializer {

  public static final Block DISENCHANTER_BLOCK = new DisenchanterBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES).requiresTool().strength(2.0F, 1200.0F));
  public static final BlockItem DISENCHANTER_BLOCK_ITEM = new BlockItem(DISENCHANTER_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS));
  public static BlockEntityType<DisenchanterBlockEntity> DISENCHANTER_BLOCK_ENTITY;
  public static ScreenHandlerType SCREEN_HANDLER_TYPE;

  static {
    DISENCHANTER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "disenchanter_block:disenchanter_block_entity", BlockEntityType.Builder.create(DisenchanterBlockEntity::new, DISENCHANTER_BLOCK).build(null));
    SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(DisenchanterBlock.ID, (syncId, inventory) -> new BlockInventoryScreen(syncId, inventory, ScreenHandlerContext.EMPTY));
  }

  public static void init() {
    Registry.register(Registry.BLOCK, new Identifier(Disenchanter.MOD_ID, "disenchanter_block"), DISENCHANTER_BLOCK);
    Registry.register(Registry.ITEM, new Identifier(Disenchanter.MOD_ID, "disenchanter_block"), DISENCHANTER_BLOCK_ITEM);
  }
}
