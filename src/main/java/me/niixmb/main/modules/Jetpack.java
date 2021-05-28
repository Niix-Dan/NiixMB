package me.niixmb.main.modules;

import me.niixmb.main.Category;
import me.niixmb.main.NiixMB;
import static me.niixmb.main.modules.BaseModule.MC;
import net.minecraft.client.network.ClientPlayerEntity;

public class Jetpack extends BaseModule {
    public Jetpack() {
        category = Category.MOVEMENT;
        ID = "jetpack";
        name = "Jetpack";
        description = "Com o Jetpack ativo, você podera voar como se tivesse uma jetpack.\nRecomendado uso do No-Fall";
        isEnabled = false;
    }
    @Override
    public void onEnable() {
        NiixMB.module.enableDisable("flight", false);
    }
    
    @Override
    public void onDisable() {
        
    }
    
    
    @Override
    public void onUpdate() {
        ClientPlayerEntity player = MC.player;
        
        if(MC.options.keyJump.isPressed())
            player.jump();
    }
}
