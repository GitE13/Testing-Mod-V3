package elisa.testingmod.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record AdvancedCustomComponent(float temperature, boolean burnt) {
    public static final Codec<AdvancedCustomComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.FLOAT.fieldOf("temperature").forGetter(AdvancedCustomComponent::temperature),
                Codec.BOOL.optionalFieldOf("burnt", false).forGetter(AdvancedCustomComponent::burnt)
        ).apply(builder, AdvancedCustomComponent::new);
    });
}