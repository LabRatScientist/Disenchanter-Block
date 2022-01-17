package com.github.plabankumarmondal.disenchanter.block;

import com.github.plabankumarmondal.disenchanter.Disenchanter;
import com.github.plabankumarmondal.disenchanter.block.entity.DisenchanterBlockEntity;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import java.util.Map;

public class DisenchanterBlock extends BlockWithEntity implements BlockEntityProvider {
  public static final Identifier ID = new Identifier(Disenchanter.MOD_ID, "disenchanter_block");
  public static Inventory blockEntityInventory;

  private static World world2;
  private static BlockPos pos2;

  public DisenchanterBlock(Settings settings) {
    super(settings);
  }

  @Nullable
  @Override
  public BlockEntity createBlockEntity(BlockView world) {
    return new DisenchanterBlockEntity();
  }

//  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//    return new DisenchanterBlockEntity(pos, state);
//  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (world.isClient) return ActionResult.SUCCESS;
    blockEntityInventory = (Inventory) world.getBlockEntity(pos);
    player.openHandledScreen(state.createScreenHandlerFactory(world, pos));

    world2 = world;
    pos2 = pos;

    return ActionResult.SUCCESS;
  }

  public static void disenchant() {
    Enchantment firstEnchantment;
    int firstEnchantmentLvl;
    ItemStack firstSlotItemStack = DisenchanterBlock.blockEntityInventory.getStack(0);
    ItemStack secondSlotItemStack = DisenchanterBlock.blockEntityInventory.getStack(1);
    Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.get(firstSlotItemStack);
    ItemStack newEnchantedBook = new ItemStack(Items.ENCHANTED_BOOK);

    if(!firstSlotItemStack.isEmpty() && !enchantmentMap.isEmpty() && !secondSlotItemStack.isEmpty()) {
      Iterator<Map.Entry<Enchantment, Integer>> enchantmentIterator = enchantmentMap.entrySet().iterator();
      Map.Entry<Enchantment, Integer> entry = enchantmentIterator.next();
      firstEnchantment = entry.getKey();
      firstEnchantmentLvl = entry.getValue();
      EnchantmentHelper.set(ImmutableMap.of(firstEnchantment, firstEnchantmentLvl), newEnchantedBook);
      DisenchanterBlock.blockEntityInventory.setStack(1, newEnchantedBook);
      DisenchanterBlock.blockEntityInventory.setStack(0, ItemStack.EMPTY);

      if(!world2.isClient) {
        world2.playSound(
                null,
                pos2,
                SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
                SoundCategory.BLOCKS,
                1f,
                1f
        );
      }
    }
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(Properties.HORIZONTAL_FACING);
  }

  @Nullable
  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return (BlockState) this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return VoxelShapes.cuboid(0, 0, 0, 0.87D, 0.62D,0.87D);
  }
}
