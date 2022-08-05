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

/**
 * This calls on_transform for the following conversions:
 * <li>Husk -> Zombie</li>
 * <li>Zombie -> Drowned</li>
 * <li>Villager -> Zombie Villager</li>
 * <li>Zombie Villager -> Villager</li>
 * <li>Piglin / Piglin brute -> Pigman</li>
 * <li>Hoglin -> Zoglin</li>
 * <li>Skeleton -> Stray</li>
 */
@Mixin(Mob.class)
public class MobMixin_scarpetEventsMixin {
    @Inject(
        method = "convertTo",
        at = @At("RETURN")
    )
    public <T extends  Mob> void onConvertEvent(EntityType<T> entityType, boolean bl, CallbackInfoReturnable<T> cir){
        ((EntityInterface)this).getEventContainer().onEvent(EntityEventsGroup.Event.ON_TRANSFORM, cir.getReturnValue());
    }
}
