package net.pathdos.totemtweaks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.pathdos.totemtweaks.config.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRenderMixin {

    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemDisplayContext;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;I)V"))
    private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickProgress, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, OrderedRenderCommandQueue orderedRenderCommandQueue, int light, CallbackInfo info) {
        if (Gui.get().enableTotemSizeChange && item.getItem() == Items.TOTEM_OF_UNDYING) {
            float sizeRange = Gui.get().maxTotemSize - Gui.get().minTotemSize;
            float size = (float) (Math.sin(System.currentTimeMillis() / 1000.0 * Gui.get().totemSizeChangeSpeed) / 2 + 0.5) * sizeRange + Gui.get().minTotemSize;
            matrices.scale(size, size, size);
        } else if (hand == Hand.MAIN_HAND && item.getItem() == Items.TOTEM_OF_UNDYING) {
            matrices.scale(Gui.get().totemSize, Gui.get().totemSize, Gui.get().totemSize);
        } else if (hand == Hand.OFF_HAND && item.getItem() == Items.TOTEM_OF_UNDYING) {
            matrices.scale(Gui.get().totemSize, Gui.get().totemSize, Gui.get().totemSize);
        }
    }

    @ModifyArgs(method = "applyEquipOffset", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V"))
    private void modifyEquipOffset(Args args, MatrixStack matrices, Arm arm, float equipProgress) {
        MinecraftClient client = MinecraftClient.getInstance();
        ItemStack itemStack = arm == client.player.getMainArm() ? client.player.getMainHandStack() : client.player.getOffHandStack();
        if (Gui.get().disableEquipAnimation && itemStack.getItem() == Items.TOTEM_OF_UNDYING) {
            args.set(0, (arm == Arm.RIGHT ? 0.56F : -0.56F));
            args.set(1, -0.52F);
            args.set(2, -0.72F);
        }
    }
}