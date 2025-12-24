package net.pathdos.totemtweaks.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.pathdos.totemtweaks.config.Gui;
import org.joml.Quaternionfc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;


@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {

    @Shadow
    private ItemStack floatingItem;

    @Shadow
    private int floatingItemTimer;

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private float floatingItemOffsetX;

    @Shadow
    private float floatingItemOffsetY;

    @Unique
    private int overlayTimeLeft;

    // Works?
    @Inject (method = "setFloatingItem", at = @At("TAIL"))
    public void InjectSetFloatingItem(ItemStack stack, Random random, CallbackInfo ci) {
      if (!Gui.get().TotemPopAnimation) {
            this.floatingItemTimer = 0;
        } else {
            this.floatingItemTimer = Gui.get().animationSpeed;
        }
        if (Gui.get().lockRotationPosition) {
            this.floatingItemOffsetX = 0;
            this.floatingItemOffsetY = 0;
        }
    }

//    @Inject(method = "renderFloatingItem", at = @At("TAIL"))
//    public void renderFloatingItemWithOverlays(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
//        if (Gui.get().showTotemCount && floatingItem != null && floatingItem.getItem() == Items.TOTEM_OF_UNDYING) {
//            int totemCount = getTotemCount();
//            String countText = "Totems: " + totemCount;
//            int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
//            int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();
//            int textWidth = MinecraftClient.getInstance().textRenderer.getWidth(countText);
//            int x = (screenWidth - textWidth) / 2;
//            int y = screenHeight / 2 + 20;
//            context.drawText(MinecraftClient.getInstance().textRenderer, countText, x, y, 0xFFFFFF, true);
//            if (Gui.get().showOverlay && overlayTimeLeft > 0) {
//                int baseColor = Gui.get().overlayColor & 0x00FFFFFF;
//                int alpha = (int) ((Gui.get().overlayOpacity / 255.0) * (overlayTimeLeft / (float) Gui.get().animationSpeed) * 255);
//                int color = baseColor | (alpha << 24);
//                context.fill(0, 0, screenWidth, screenHeight, color);
//                overlayTimeLeft--;
//            }
//        }
//        if (Gui.get().showOverlay) {
//            overlayTimeLeft = floatingItemTimer;
//        }
//    }
/*    @Unique
    private int getTotemCount() {
        int count = 0;
        assert MinecraftClient.getInstance().player != null;
        for (ItemStack stack : MinecraftClient.getInstance().player.getInventory().getMainStacks()) {
            if (stack.getItem() == Items.TOTEM_OF_UNDYING) {
                count += stack.getCount();
            }
        }
        return count;
    }*/

    @ModifyVariable(method = "renderFloatingItem", at = @At("STORE"), ordinal = 0)
    private int modifyTickRenderfloatingItem(int i) {
        return Gui.get().animationSpeed - floatingItemTimer;
    }

    @ModifyVariable(method = "renderFloatingItem", at = @At("STORE"), ordinal = 1)
    private float modifyFloatRenderfloatingItem(float f) {
        return f * 40 / Gui.get().animationSpeed;
    }

    // Changes different origin point for totem pop then oldr version
/*    @Inject(
            method = "renderFloatingItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;push()V",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void moveTotemOrigin(MatrixStack matrices, float tickProgress, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        Window window = client.getWindow();

        float sliderX = Gui.get().xPosition;
        float sliderY = Gui.get().yPosition;

        float screenWidth = window.getScaledWidth();
        float screenHeight = window.getScaledHeight();


        float pixelX = (sliderX / 100f) * screenWidth;
        float pixelY = (sliderY / 100f) * screenHeight;

        float normalizedX = (pixelX - screenWidth / 2f) / (screenWidth / 2f);
        float normalizedY = (screenHeight / 2f - pixelY) / (screenHeight / 2f);

        matrices.translate(normalizedX, normalizedY, 0);

    }*/



    @ModifyArgs(method = "renderFloatingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V"))
    private void modifyScaleArgs(Args args) {
        float scale = 0.8F;

        scale *= Gui.get().popSize;

        args.set(0, scale);
        args.set(1, scale);
        args.set(2, scale);
    }


    @WrapWithCondition(method = "renderFloatingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionfc;)V", ordinal = 0))
    private boolean wrapRotationY(MatrixStack matrixStack, Quaternionfc rotation) {
        return !Gui.get().disableRotations;
    }

    @WrapWithCondition(method = "renderFloatingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionfc;)V", ordinal = 1))
    private boolean wrapRotationX(MatrixStack matrixStack, Quaternionfc rotation) {
        return !Gui.get().disableRotations;
    }

    @WrapWithCondition(method = "renderFloatingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionfc;)V", ordinal = 2))
    private boolean wrapRotationZ(MatrixStack matrixStack, Quaternionfc rotation) {
        return !Gui.get().disableRotations;
    }
}