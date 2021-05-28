package me.niixmb.main.modules;

import me.niixmb.main.Category;
import net.minecraft.client.MinecraftClient;


public abstract class BaseModule {
    public BaseModule(){}
    public String name = "";
    public String description = "";
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public String ID = "";
    public boolean isEnabled = false;
    public Category category;
    
    public boolean Execute() {
        onUpdate();
        return false;
    }
    
    public abstract void onUpdate();
    public abstract void onEnable();
    public abstract void onDisable();
}
