package elisa.testingmod.item;

import elisa.testingmod.TestingModV3;
import elisa.testingmod.item.Armor.GuiditeArmorMaterial;
import elisa.testingmod.item.custom.LightningStick;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;
import java.util.function.Function;

import static elisa.testingmod.item.Armor.GuiditeArmorMaterial.REPAIRS_GUIDITE_ARMOR;

public class ModItems {
    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TestingModV3.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    } // Generates an item from a name, item factory, and item properties

    public static Consumable generate_consumable_component(Holder<MobEffect> effect, int duration, int amplifier, float probability) {
        return Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(effect, duration, amplifier), probability))
                .build();
    } // Generates a component for a food item that gives an effect when consumed.

    public static void add_item_to_menu(ResourceKey<CreativeModeTab> resourceKey, Item[] Items) {
        for (Item item : Items) {
            CreativeModeTabEvents.modifyOutputEvent(resourceKey)
                    .register(creativeTab -> creativeTab.accept(item));
        }
    }
    public static void add_item_to_menu(ResourceKey<CreativeModeTab> resourceKey, Item item) {
        CreativeModeTabEvents.modifyOutputEvent(resourceKey)
                .register(creativeTab -> creativeTab.accept(item));
    }

    public static final FoodProperties FOOD_ALWAYS_COMPONENT = new FoodProperties.Builder()
            .alwaysEdible()
            .build();


    public static final ToolMaterial GUIDITE_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 200, 5.5F, 1.5F, 30, REPAIRS_GUIDITE_ARMOR);


    public static final Item SUSPICIOUS_SUBSTANCE = register(
            "suspicious_substance",
            Item::new,
            new Item.Properties());
    public static final Item POISONOUS_APPLE = register(
            "poisonous_apple",
            Item::new,
            new Item.Properties().food(FOOD_ALWAYS_COMPONENT, generate_consumable_component(MobEffects.POISON, 20*6, 1, 1.0f))
    );
    public static final Item Iron_GOLEM_SPAWN_EGG = register(
            "iron_golem_spawn_egg",
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(EntityType.IRON_GOLEM)
    );
    public static final Item GUIDITE_SWORD = register(
            "guidite_sword",
            Item::new,
            new Item.Properties().sword(GUIDITE_TOOL_MATERIAL, 3.0F, -2F)
    );
    public static final Item GUIDITE_HELMET = register(
            "guidite_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(GuiditeArmorMaterial.INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(GuiditeArmorMaterial.BASE_DURABILITY))
    );
    public static final Item GUIDITE_CHESTPLATE = register(
            "guidite_chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(GuiditeArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(GuiditeArmorMaterial.BASE_DURABILITY))
    );

    public static final Item GUIDITE_LEGGINGS = register(
            "guidite_leggings",
            Item::new,
            new Item.Properties().humanoidArmor(GuiditeArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(GuiditeArmorMaterial.BASE_DURABILITY))
    );

    public static final Item GUIDITE_BOOTS = register(
            "guidite_boots",
            Item::new,
            new Item.Properties().humanoidArmor(GuiditeArmorMaterial.INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(GuiditeArmorMaterial.BASE_DURABILITY))
    );
    public static final LightningStick LIGHTNING_STICK = register(
            "lightning_stick",
            LightningStick::new,
            new Item.Properties().stacksTo(1).durability(128));


    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
        CompostableRegistry.INSTANCE.add(ModItems.SUSPICIOUS_SUBSTANCE, 0.3f);
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 30 * 20);
        });

        add_item_to_menu(CreativeModeTabs.SPAWN_EGGS, Iron_GOLEM_SPAWN_EGG);
        add_item_to_menu(CreativeModeTabs.COMBAT, new Item[]{GUIDITE_HELMET, GUIDITE_CHESTPLATE, GUIDITE_LEGGINGS, GUIDITE_BOOTS, GUIDITE_SWORD, LIGHTNING_STICK});

    }

    public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(TestingModV3.MOD_ID, "creative_tab")
    );
    public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.SUSPICIOUS_SUBSTANCE))
            .title(Component.translatable("creativeTab.testingmodv3"))
            .displayItems((params, output) -> {
                output.accept(ModItems.SUSPICIOUS_SUBSTANCE);
                output.accept(ModItems.POISONOUS_APPLE);

                // The tab builder also accepts Blocks
                //output.accept(ModBlocks.CONDENSED_DIRT);

                // And custom ItemStacks
                ItemStack stack = new ItemStack(Items.SEA_PICKLE);
                stack.set(DataComponents.ITEM_NAME, Component.literal("Pickle Rick"));
                stack.set(DataComponents.LORE, new ItemLore(List.of(Component.literal("I'm pickle riiick!!"))));
                output.accept(stack);
            })
            .build();
}
