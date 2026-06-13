package elisa.testingmod.potion;

import elisa.testingmod.TestingModV3;
import elisa.testingmod.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

public class ModPotions {
    public static Holder<Potion> GENERATE_POTION(String name, Holder<MobEffect> effect, int duration, int amplifier, Holder<Potion> inputpotion, Item ingredient) {
        Holder<Potion> item = Registry.registerForHolder(
                BuiltInRegistries.POTION,
                Identifier.fromNamespaceAndPath(TestingModV3.MOD_ID, name),
                new Potion(name,
                        new MobEffectInstance(
                                effect,
                                duration,
                                amplifier
                        )
                )
        );
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.addMix(
                    // Input potion.
                    inputpotion,
                    // Ingredient
                    ingredient,
                    // Output potion.
                    item
            );
        });
        return item;
    } // Generates a potion holder from a name, effect, duration, amplifier, input potion to make it, and ingredient to make it

    public static final Holder<Potion> SUSPICIOUS_POTION = GENERATE_POTION("suspicious_potion", MobEffects.UNLUCK, 20 * 180, 0, Potions.WATER, ModItems.SUSPICIOUS_SUBSTANCE);

    public static void initialize() {}
}
