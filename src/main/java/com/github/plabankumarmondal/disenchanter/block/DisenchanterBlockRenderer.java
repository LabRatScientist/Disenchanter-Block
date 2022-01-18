package com.github.plabankumarmondal.disenchanter.block;

import com.github.plabankumarmondal.disenchanter.block.entity.DisenchanterBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class DisenchanterBlockRenderer extends GeoBlockRenderer<DisenchanterBlockEntity> {
  public DisenchanterBlockRenderer() {
    super(new DisenchanterBlockModel());
  }

  @Override
  public RenderLayer getRenderType(DisenchanterBlockEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
    return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
  }
}
