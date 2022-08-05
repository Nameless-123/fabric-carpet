package carpet.mixins;

import carpet.fakes.EntityInterface;
import carpet.script.EntityEventsGroup;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Villager.class)
public class Villager_scarpetEventsMixin {
    /**
     * Calls on_transform for villagers being thunder blasted into witches
     * @return witch variable unchanged, cos we only wanna look at it, not touch it
     */
    @ModifyArg(
            method = "thunderHit",
            at=@At(value="INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V")
    )
    private Entity onThunderHitEvent(final Entity witch){
        ((EntityInterface)this).getEventContainer().onEvent(EntityEventsGroup.Event.ON_TRANSFORM, witch);
        return witch;
    }
}
