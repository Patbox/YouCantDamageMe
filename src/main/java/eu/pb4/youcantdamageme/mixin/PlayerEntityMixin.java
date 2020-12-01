package eu.pb4.youcantdamageme.mixin;

import eu.pb4.youcantdamageme.YouCantDamageMe;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setAbsorptionAmount(F)V"), cancellable = true)
    private void cancelDamage(DamageSource source, float amount, CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(YouCantDamageMe.DAMAGE_GAMERULE) && !source.isOutOfWorld()) {
            ci.cancel();
        }
    }

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

}
