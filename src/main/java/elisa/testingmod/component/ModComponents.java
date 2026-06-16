package elisa.testingmod.component;

import com.mojang.serialization.Codec;
import elisa.testingmod.TestingModV3;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModComponents {

    public static <T> DataComponentType<T> registerComponent(String name, Codec<T> codec) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                Identifier.fromNamespaceAndPath(TestingModV3.MOD_ID, name),
                DataComponentType.<T>builder()
                        .persistent(codec)
                        .build()
        );
    }

    public static final DataComponentType<Integer> CLICK_COUNT_COMPONENT = registerComponent("click_count", Codec.INT);
    public static final DataComponentType<AdvancedCustomComponent> ADVANCED_COMPONENT = registerComponent("advanced", AdvancedCustomComponent.CODEC);

    public static void initialize() {}
}