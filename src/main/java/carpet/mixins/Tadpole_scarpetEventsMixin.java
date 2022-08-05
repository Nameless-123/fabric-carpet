package carpet.mixins;

import carpet.fakes.EntityInterface;
import carpet.script.EntityEventsGroup;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.frog.Tadpole;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Tadpole.class)
public class Tadpole_scarpetEventsMixin {
    /**
     * Calls on_transform for tadpoles growing up into frogs
     * @return frog variable unchanged, cos we only wanna look at it, not touch it
     */
    @ModifyArg(
        method = "ageUp()V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V")
    )
    private Entity onAgeUpEvent(final Entity frog){
        ((EntityInterface)this).getEventContainer().onEvent(EntityEventsGroup.Event.ON_TRANSFORM, frog);
        return frog;
    }
}
