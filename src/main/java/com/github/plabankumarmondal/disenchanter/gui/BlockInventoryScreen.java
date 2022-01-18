package com.github.plabankumarmondal.disenchanter.gui;

import com.github.plabankumarmondal.disenchanter.block.DisenchanterBlock;
import com.github.plabankumarmondal.disenchanter.init.DisenchanterInitializer;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;

public class BlockInventoryScreen extends SyncedGuiDescription {

  private static final int INVENTORY_SIZE = 2;
  private static final Style STYLE;

  public BlockInventoryScreen(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
    super(DisenchanterInitializer.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

    WGridPanel root = new WGridPanel();
    setRootPanel(root);
    root.setSize(170, 150);
    root.setInsets(Insets.ROOT_PANEL);

    WItemSlot itemSlot1 = WItemSlot.of(blockInventory, 0);
    WItemSlot itemSlot2 = WItemSlot.of(blockInventory, 1);
    WButton button =  new WButton(new LiteralText("shift").fillStyle(STYLE));

    itemSlot1.setFilter(stack -> stack.isDamageable() || stack.hasEnchantments() );
    itemSlot2.setFilter(stack -> stack.getItem() == Items.BOOK && stack.getCount() == 1);

    root.add(itemSlot1, 1,1);
    root.add(button, 3, 1, 3, 10);
    root.add(itemSlot2, 7,1);

    root.add(this.createPlayerInventoryPanel(), 0, 3);

    root.validate(this);

    button.setOnClick(DisenchanterBlock::disenchant);
  }


  static {
    STYLE = Style.EMPTY.withFont(new Identifier("minecraft", "alt"));
  }
}