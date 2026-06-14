package elisa.testingmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LightningStick extends Item {
    public LightningStick(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        // Ensure we don't spawn the lightning only on the client.
        // This is to prevent desync.
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        float yaw = user.getYRot();
        float pitch = user.getXRot();

        double yawRadians = Math.toRadians(yaw);
        double pitchRadians = Math.toRadians(pitch);

        double x = -Math.sin(yawRadians) * Math.cos(pitchRadians) * 10;
        double y = -Math.sin(pitchRadians) * 10;
        double z = Math.cos(yawRadians) * Math.cos(pitchRadians)* 10;

        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.setPos(user.getEyePosition().add(x,y,z));
        level.addFreshEntity(lightningBolt);

        ItemStack heldStack = user.getItemInHand(hand);
        heldStack.hurtAndBreak(1,user,hand);

        return InteractionResult.SUCCESS;
    }
}