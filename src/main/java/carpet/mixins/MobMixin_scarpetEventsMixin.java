package carpet.mixins;

import carpet.fakes.EntityInterface;
import carpet.script.EntityEventsGroup;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class MobMixin_scarpetEventsMixin {
    @Inject(
        method = "convertTo",
        at = @At("RETURN")
    )
    public <T extends  Mob> void mixin(EntityType<T> entityType, boolean bl, CallbackInfoReturnable<T> cir){
        ((EntityInterface)this).getEventContainer().onEvent(EntityEventsGroup.Event.ON_TRANSFORM, cir.getReturnValue());
    }
}
