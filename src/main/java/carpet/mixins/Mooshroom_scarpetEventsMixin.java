package carpet.mixins;

import carpet.fakes.EntityInterface;
import carpet.script.EntityEventsGroup;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.MushroomCow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MushroomCow.class)
public class Mooshroom_scarpetEventsMixin {
    /**
     * Calls on_transform for mooshrooms being sheared into cows
     * @return cow variable unchanged, cos we only wanna look at it, not touch it
     */
    @ModifyArg(
        method = "shear",
        at=@At(value="INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")
    )
    private Entity onShearEvent(final Entity cow){
        ((EntityInterface)this).getEventContainer().onEvent(EntityEventsGroup.Event.ON_TRANSFORM, cow);
        return cow;
    }
}
