package carpet.mixins;

import carpet.fakes.EntityInterface;
import carpet.script.EntityEventsGroup;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Pig.class)
public class Pig_scarpetEventsMixin {
    /**
     * Calls on_transform for pigs being thunder blasted into pigmen
     * @return pigman variable unchanged, cos we only wanna look at it, not touch it
     */
    @ModifyArg(
            method = "thunderHit",
            at=@At(value="INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")
    )
    private Entity onThunderHitEvent(final Entity pigman){
        ((EntityInterface)this).getEventContainer().onEvent(EntityEventsGroup.Event.ON_TRANSFORM, pigman);
        return pigman;
    }
}
