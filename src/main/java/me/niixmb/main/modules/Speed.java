package me.niixmb.main.modules;

import me.niixmb.main.Category;
import static me.niixmb.main.modules.BaseModule.MC;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;


public class Speed extends BaseModule {
    public Speed() {
        category = Category.MOVEMENT;
        ID = "speed";
        name = "Speed";
        isEnabled = false;
        description = "Com Speed ativo, sua velocidade para andar sera aumentada.";
    }
    @Override
    public void onEnable() {
        
    }
    
    @Override
    public void onDisable() {
        
    }
    
    
    @Override
    public void onUpdate() {
        ClientPlayerEntity player = MC.player;
        
        if(player.isSneaking() || player.forwardSpeed == 0 && player.sidewaysSpeed == 0) return;
        if(player.forwardSpeed > 0 && !player.horizontalCollision) player.setSprinting(true);
        if(!player.isOnGround()) return;
        
        Vec3d v = player.getVelocity();
        
        player.setVelocity(v.x * 1.8, v.y + 0.1, v.z * 1.8);
        
        v = player.getVelocity();
        double currentSpeed = Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.z, 2));
        double maxSpeed = 0.50F;
        
        if(currentSpeed > maxSpeed) {
            player.setVelocity(v.x / currentSpeed * maxSpeed, v.y, v.z / currentSpeed * maxSpeed);
        }
    }
    
}
