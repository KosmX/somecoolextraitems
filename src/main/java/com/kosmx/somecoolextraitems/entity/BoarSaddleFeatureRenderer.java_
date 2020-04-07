package com.kosmx.somecoolextraitems.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BoarSaddleFeatureRenderer extends FeatureRenderer<BoarEntity, PigEntityModel<BoarEntity>> {
   private static final Identifier SKIN = new Identifier("textures/entity/pig/pig_saddle.png");
   private final PigEntityModel<BoarEntity> model = new PigEntityModel<>(0.5F);

   public BoarSaddleFeatureRenderer(final FeatureRendererContext<BoarEntity, PigEntityModel<BoarEntity>> context) {
      super(context);
   }

   public void render(final MatrixStack matrixStack, final VertexConsumerProvider vertexConsumerProvider, final int i, final BoarEntity pigEntity, final float f, final float g, final float h, final float j, final float k, final float l) {
      if (pigEntity.isSaddled()) {
         ((PigEntityModel<BoarEntity>)this.getContextModel()).copyStateTo(this.model);
         this.model.animateModel(pigEntity, f, g, h);
         this.model.setAngles(pigEntity, f, g, j, k, l);
         final VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(SKIN));
         this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
      }
   }
}
