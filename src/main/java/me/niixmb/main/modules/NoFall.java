package me.niixmb.main.modules;

import me.niixmb.main.Category;
import static me.niixmb.main.NiixMB.MC;
import static me.niixmb.main.modules.BaseModule.MC;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.util.math.Vec3d;

public class NoFall extends BaseModule {
    public NoFall() {
        category = Category.MOVEMENT;
        ID = "nofall";
        name = "No-Fall";
        description = "Com o No-Fall ativo, voce nao leva dano de queda.";
        isEnabled = false;
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
        
        if(player.fallDistance <= (player.isFallFlying() ? 1 : 2)) return;
        if(player.isFallFlying() && player.isSneaking() && !isFallingFastEnoughToCauseDamage(player)) return;
        
        player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
    }
    private boolean isFallingFastEnoughToCauseDamage(ClientPlayerEntity player) {
       return player.getVelocity().y < -0.5;
    }
}
