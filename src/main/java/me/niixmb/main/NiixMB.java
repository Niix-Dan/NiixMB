package me.niixmb.main;

import com.mojang.authlib.GameProfile;
import me.niixmb.main.callback.PlayerDeathCallback;
import me.niixmb.main.modules.ModuleManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;


public class NiixMB implements ModInitializer {
    
    public static KeyBinding flight_, jetpack_, nofall_, speed_, protector_;
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    //public static final IMinecraftClient IMC = (IMinecraftClient)MC;
    
    public static ModuleManager module;
    
    @Override
    public void onInitialize() {
        module = new ModuleManager();
        
        
        PlayerDeathCallback.EVENT.register((player, source) -> {
            MinecraftServer server = player.getServer();
            if(server == null) {
                return;
            }
            
            LiteralText msg = new LiteralText("F");
            
            server.sendSystemMessage(msg, MC.player.getUuid());/*
            for(ServerPlayerEntity player1 : server.getPlayerManager().getPlayerList()) {
                player1.sendSystemMessage(msg, MC.player.getUuid());
            }*/
            
        });
        
        
        
        flight_ = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Flight",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                "NiixMB"
        ));
        protector_ = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Protector",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_J,
                "NiixMB"
        ));
        jetpack_ = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Jetpack",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "NiixMB"
        ));
        nofall_ = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "No-Fall",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "NiixMB"
        ));
        speed_ = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Speed",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "NiixMB"
        ));
        
        GameUpdate();
    }
    
    
    private void GameUpdate() {
        
        
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            if(client.player == null) return;
            if(client.player.world == null) return;
            
            if(nofall_.wasPressed()) {
                module.enableDisable("nofall");
            }
            if(flight_.wasPressed()) {
                module.enableDisable("flight");
            }
            if(speed_.wasPressed()) {
                module.enableDisable("speed");
            }
            if(jetpack_.wasPressed()) {
                module.enableDisable("jetpack");
            }
            if(protector_.wasPressed()) {
                module.enableDisable("protector");
            }
            
            module.updateModules();
        });
    }
}
