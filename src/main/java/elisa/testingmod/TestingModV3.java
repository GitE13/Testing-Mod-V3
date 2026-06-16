package elisa.testingmod;

import elisa.testingmod.component.AdvancedCustomComponent;
import elisa.testingmod.component.ModComponents;
import elisa.testingmod.enchantment.ModEnchantments;
import elisa.testingmod.item.ModItems;
import elisa.testingmod.potion.ModPotions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class TestingModV3 implements ModInitializer {
	public static final String MOD_ID = "testing-mod-v3";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public <T> void registerTooltip(String key, DataComponentType<T> component, ChatFormatting format, Function<T, ?> extractor) {
		ItemTooltipCallback.EVENT.register((stack, context, type, tooltip) -> {
			if (stack.has(component)) {

				T data = stack.get(component);

				if (data != null) {
					Object value = extractor.apply(data);
					tooltip.add(Component.translatable(key, value).withStyle(format));
				}
			}
		});
	}

	@Override
	public void onInitialize() {
		TestingModV3.LOGGER.info("Initializing " + MOD_ID);

		ModItems.initialize();
		ModPotions.initialize();
		ModEnchantments.initialize();
		ModComponents.initialize();

		registerTooltip("item.testing-mod-v3.counter.info", ModComponents.CLICK_COUNT_COMPONENT, ChatFormatting.GOLD, data -> data);
		registerTooltip("item.testing-mod-v3.temperature.info", ModComponents.ADVANCED_COMPONENT, ChatFormatting.GOLD, AdvancedCustomComponent::temperature);
		registerTooltip("item.testing-mod-v3.burnt.info", ModComponents.ADVANCED_COMPONENT, ChatFormatting.GOLD, AdvancedCustomComponent::burnt);
	}
}