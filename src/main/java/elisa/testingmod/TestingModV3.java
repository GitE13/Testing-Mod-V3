package elisa.testingmod;

import elisa.testingmod.item.ModItems;
import elisa.testingmod.potion.ModPotions;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestingModV3 implements ModInitializer {
	public static final String MOD_ID = "testing-mod-v3";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		TestingModV3.LOGGER.info("Initializing " + MOD_ID);

		ModItems.initialize();
		ModPotions.initialize();
	}
}