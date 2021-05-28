package me.niixmb.main.modules;

import me.niixmb.main.Category;
import me.niixmb.main.NiixMB;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Flight extends BaseModule {
    
    public Flight() {
        category = Category.MOVEMENT;
        ID = "flight";
        name = "Flight";
        isEnabled = false;
        description = "Com o Flight voce pode voar pelo mundo.\nRecomendado uso do No-Fall";
    }
    @Override
    public void onEnable() {
        NiixMB.module.enableDisable("jetpack", false);
        //NiixMB.INSTANCE.getEventManager().add(UpdateListener.class, NiixMB.module.getModule('flight'));
    }
    
    @Override
    public void onDisable() {
        
    }
    
    
    @Override
    public void onUpdate() {
        
        ClientPlayerEntity player = MC.player;
        
        player.abilities.flying = false;
        player.flyingSpeed = (float)0.5;
        player.setVelocity(0, 0, 0);
        
        Vec3d velcity = player.getVelocity();
        if(MC.options.keyJump.isPressed())
                player.setVelocity(velcity.add(0, 0.5, 0));
        if(MC.options.keySneak.isPressed())
                player.setVelocity(velcity.subtract(0, 0.5, 0));
        
    }
    
}
