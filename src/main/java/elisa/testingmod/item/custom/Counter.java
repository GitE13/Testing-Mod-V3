package elisa.testingmod.item.custom;

import elisa.testingmod.component.ModComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Counter extends Item {
    public Counter(Properties properties) {
        super(properties);
    }

    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        ItemStack stack = user.getItemInHand(hand);

        // Don't do anything on the client
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        // Read the current count and increase it by one
        int count = stack.getOrDefault(ModComponents.CLICK_COUNT_COMPONENT, 0);
        stack.set(ModComponents.CLICK_COUNT_COMPONENT, ++count);

        return InteractionResult.SUCCESS;
    }
}
