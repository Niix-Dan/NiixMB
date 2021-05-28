package me.niixmb.main.mixin;

import me.niixmb.main.callback.PlayerDeathCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 *
 * Bruh
 */
@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "onDeath", at = @At(value = "TAIL"), cancellable = true)
    public void onDeath(DamageSource source, CallbackInfo ci) {
        
        
        //PlayerDeathCallback
        PlayerDeathCallback.EVENT.invoker().kill((ServerPlayerEntity) (Object) this, source);
        
    }
}
