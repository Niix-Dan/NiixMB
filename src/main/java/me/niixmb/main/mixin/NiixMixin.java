
package me.niixmb.main.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftClient.class)
@Environment(EnvType.CLIENT)
public class NiixMixin {
    /*
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public GameOptions options;
    public ClientPlayerEntity player;
    public InGameHud inGameHud;
    public Screen currentScreen;

    @ModifyConstant(
        method = "handleInputEvents",
        constant = @Constant(intValue = 9),
        slice = @Slice(
            from = @At("HEAD"),
            to = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;keyPressed()Z"
                )
        ),
        remap = false
    )
    @Inject(method = "handleInputEvents", at = @At("HEAD"), remap = false)
    public void keyPressed(CallbackInfo info) {
        
        
    }*/
}
