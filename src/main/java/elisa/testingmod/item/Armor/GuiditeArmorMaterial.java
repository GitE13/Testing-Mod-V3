package elisa.testingmod.item.Armor;

import elisa.testingmod.TestingModV3;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class GuiditeArmorMaterial {
    public static final int BASE_DURABILITY = 15;
    public static final ResourceKey<EquipmentAsset> GUIDITE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(TestingModV3.MOD_ID, "guidite"));

    public static final TagKey<Item> REPAIRS_GUIDITE_ARMOR = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(TestingModV3.MOD_ID, "repairs_guidite_armor"));

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.LEGGINGS, 5,
                    ArmorType.BOOTS, 2
            ),
            5,
            SoundEvents.ARMOR_EQUIP_IRON,
            0.0F,
            0.5F,
            REPAIRS_GUIDITE_ARMOR,
            GUIDITE_ARMOR_MATERIAL_KEY
    );

}
