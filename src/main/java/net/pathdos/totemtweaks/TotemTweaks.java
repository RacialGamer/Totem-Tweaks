package net.pathdos.totemtweaks;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.terraformersmc.modmenu.ModMenu;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.pathdos.totemtweaks.config.Gui;

public class TotemTweaks implements ModInitializer {

	@Override
	public void onInitialize() {
		AutoConfig.register(Gui.class, GsonConfigSerializer::new);
		registerCommands();
	}

	private void registerCommands() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(ClientCommandManager.literal("totemTweaks")
					.executes(context -> openConfigScreen(context))
			);

			dispatcher.register(ClientCommandManager.literal("simulatePop")
					.executes(context -> {
						simulatePop();
						return Command.SINGLE_SUCCESS;
					})
			);
		});
	}

	private int openConfigScreen(CommandContext<?> context) {
		MinecraftClient client = MinecraftClient.getInstance();
		client.send(() -> {
			client.setScreen(ModMenu.getConfigScreen("totemtweaks", client.currentScreen));
		});
		return Command.SINGLE_SUCCESS;
	}

	public static void simulatePop() {
		if (MinecraftClient.getInstance().player != null) {
			MinecraftClient.getInstance().gameRenderer.showFloatingItem(new ItemStack(Items.TOTEM_OF_UNDYING));
		}
	}
}

