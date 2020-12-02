package eu.pb4.youcantdamageme.mixin;

import eu.pb4.youcantdamageme.YouCantDamageMe;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setAbsorptionAmount(F)V"), cancellable = true)
    private void cancelDamage(DamageSource source, float amount, CallbackInfo ci) {
        if (!this.world.getGameRules().getBoolean(YouCantDamageMe.DAMAGE_GAMERULE) && !source.isOutOfWorld()) {
            ci.cancel();
        }
    }

    @ModifyVariable(method = "applyDamage", at = @At("HEAD"), ordinal = 0)
    private float scaleDamage(float y) {
        return y * this.world.getGameRules().getInt(YouCantDamageMe.DAMAGE_SCALE_GAMERULE) / 100;
    }

    @Inject(method = "isInvulnerableTo", at = @At("TAIL"), cancellable = true)
    private void otherSources(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (damageSource.getAttacker() != null && !damageSource.getMagic() && damageSource.getAttacker() != this) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(YouCantDamageMe.ENTITY_ATTACK_DAMAGE_GAMERULE));
        } else if (damageSource.getAttacker() != null && !damageSource.getMagic() && damageSource.getAttacker() == this) {
            cir.setReturnValue(!this.world.getGameRules().getBoolean(YouCantDamageMe.SELF_DAMAGE_GAMERULE));
        }

        else if (damageSource.getMagic()) cir.setReturnValue(!this.world.getGameRules().getBoolean(YouCantDamageMe.MAGIC_DAMAGE_GAMERULE));
    }




    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

}
