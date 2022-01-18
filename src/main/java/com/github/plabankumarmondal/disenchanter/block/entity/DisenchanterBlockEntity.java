package com.github.plabankumarmondal.disenchanter.block.entity;

import com.github.plabankumarmondal.disenchanter.gui.BlockInventoryScreen;
import com.github.plabankumarmondal.disenchanter.init.DisenchanterInitializer;
import com.github.plabankumarmondal.disenchanter.inventory.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DisenchanterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, IAnimatable {

  private final AnimationFactory factory = new AnimationFactory(this);
  private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);

  public DisenchanterBlockEntity( BlockPos pos, BlockState state) {
    super(DisenchanterInitializer.DISENCHANTER_BLOCK_ENTITY , pos, state);
  }

  @Override
  public DefaultedList<ItemStack> getItems() {
    return items;
  }

  @Override
  public void readNbt(NbtCompound nbt) {
    super.readNbt(nbt);
    Inventories.readNbt(nbt, items);
  }

  @Override
  public void writeNbt(NbtCompound nbt) {
    super.writeNbt(nbt);
    Inventories.writeNbt(nbt, items);
  }

  @Override
  public boolean canPlayerUse(PlayerEntity player) {
    return pos.isWithinDistance(player.getPos(), 4.5);
  }

  @Override
  public Text getDisplayName() {
//    Using the block name as screen title
    return new TranslatableText(getCachedState().getBlock().getTranslationKey());
  }

  @Nullable
  @Override
  public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
    return new BlockInventoryScreen(syncId, inv, ScreenHandlerContext.create(world, pos));
  }

  @SuppressWarnings("unchecked")
  private <E extends  BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
    event.getController().transitionLengthTicks = 0;
    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.disenchanting_table.default", true));
    return PlayState.CONTINUE;
  }

  @Override
  public void registerControllers(AnimationData data) {
    data.addAnimationController(new AnimationController<DisenchanterBlockEntity>(this, "controller", 0, this::predicate));
  }

  @Override
  public AnimationFactory getFactory() {
    return factory;
  }
}
