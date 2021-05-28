package me.niixmb.main.modules;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;

public class ModuleManager {
    public List<BaseModule> modules = new ArrayList<BaseModule>();
    
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    
    public ModuleManager() {
        modules.add(new Flight());
        modules.add(new Speed());
        modules.add(new NoFall());
        modules.add(new Jetpack());
        modules.add(new Protector());
    }
    
    public void updateModules() {
        for (BaseModule module : modules) {
            if(module.isEnabled) {
                module.Execute();
            }
        }
    }
    
    public void enableDisable(String ID) {
        for (BaseModule module : modules) {
            if(module.ID.equalsIgnoreCase(ID)) {
                if(module.isEnabled) {
                    module.onDisable();
                } else {
                    module.onEnable();
                }
                module.isEnabled = !module.isEnabled;
                MC.player.sendMessage(new LiteralText(module.isEnabled ? "\u00a7c"+module.name+": \u00a7a[ON]" : "\u00a77"+module.name+": \u00a77[OFF]"), true);
            }
            //MC.player.sendMessage(new LiteralText(module.isEnabled ? "\u00a7c"+module.name+": \u00a7a[ON]" : "\u00a77"+module.name+": \u00a77[OFF]"), false);
        }
    }
    public void enableDisable(String ID, boolean status) {
        for (BaseModule module : modules) {
            if(module.ID.equalsIgnoreCase(ID)) {
                module.isEnabled = status;
            }
        }
    }
    public String color() {
        return "\u00a7";
    }
    
    public BaseModule getModule(String id) {
        for(BaseModule module : modules) {
            if(module.ID.equalsIgnoreCase(id)) {
                return module;
            }
        }
        return null;
    }
}