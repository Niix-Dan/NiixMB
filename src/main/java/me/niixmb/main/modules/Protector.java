package me.niixmb.main.modules;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import me.niixmb.main.Category;
import me.niixmb.main.NiixMB;
import me.niixmb.main.util.EnumSetting;
import me.niixmb.main.util.RotationUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

/**
 *
 * @author SrNox_
 */
public class Protector extends BaseModule {
    public Protector() {
        category = Category.OTHER;
        ID = "protector";
        name = "Protector";
        description = "Com o Protector ativo, irei fazer o possível para lhe manter seguro de tudo.";
        isEnabled = false;
    }
    private Entity target;
    private final EnumSetting<Priority> priority = new EnumSetting<>("Pritority", "-", Priority.values(), Priority.ANGLE);
    
    @Override
    public void onUpdate() {
        
        // Verificar se tem mobs hostis por perto.
        
        
        
        double posX = MC.player.getPos().x, posY = MC.player.getPos().y, posZ = MC.player.getPos().z;
        
        
        ClientPlayerEntity player = MC.player;
        ClientWorld world = MC.world;
        
        
        
        if(MC.player.isTouchingWater() && !MC.player.isDead()) {
            Vec3d velocity = player.getVelocity();
            player.setVelocity(velocity.x, 0.11, velocity.z);
        }
        
        if(MC.player.isInLava() && !MC.player.isDead()) {
            Vec3d velocity = player.getVelocity();
            player.setVelocity(velocity.x, 0.11, velocity.z);
        }
        
        
        
        if(player.getAttackCooldownProgress(0) < 1) 
            return;
        
        double rangeSq = 6;
        Stream<Entity> stream =
                StreamSupport.stream(MC.world.getEntities().spliterator(), true)
                .filter(e -> !e.removed)
                .filter(e -> e instanceof LivingEntity && ((LivingEntity)e).getHealth() > 0 || e instanceof EndCrystalEntity)
                .filter(e -> e != player)
                .filter(e -> {
                    if(!(e instanceof PlayerEntity))
                        return true;
                    Box box = e.getBoundingBox();
                    box = box.union(box.offset(0, 0, 0));
                    return !world.isSpaceEmpty(box);
                })
                .filter(e -> (e instanceof ZombifiedPiglinEntity) && (((ZombifiedPiglinEntity) e).isAngryAt(player)))
                .filter(e -> (e instanceof EndermanEntity) && (((EndermanEntity) e).isAngryAt(player)))
                .filter(e -> !(e instanceof TameableEntity) && ((TameableEntity)e).isTamed())
                .filter(e -> !(e instanceof HorseBaseEntity) && ((HorseBaseEntity)e).isTame())
                .filter(e -> !(e instanceof ArmorStandEntity))
                .filter(e -> !(e instanceof EndCrystalEntity));
        
        
        
        
        target = stream.min(priority.getSelected().comparator).orElse(null);
        if(target == null)
            return;
        
        MC.interactionManager.attackEntity(player, target);
        player.swingHand(Hand.MAIN_HAND);
        
        target = null;
        
        /*
        if(MC.player.isInLava() && !MC.player.isDead()) {
            MC.player.jump();
            MC.player.swingHand(Hand.MAIN_HAND);
        }*/
    }

    @Override
    public void onEnable() {
        NiixMB.module.enableDisable("nofall", true);
    }

    @Override
    public void onDisable() {
        
    }
    private enum Priority
	{
		DISTANCE("Distance", e -> MC.player.squaredDistanceTo(e)),
		
		ANGLE("Angle",
			e -> RotationUtils
				.getAngleToLookVec(e.getBoundingBox().getCenter())),
		
		HEALTH("Health", e -> e instanceof LivingEntity
			? ((LivingEntity)e).getHealth() : Integer.MAX_VALUE);
		
		private final String name;
		private final Comparator<Entity> comparator;
		
		private Priority(String name, ToDoubleFunction<Entity> keyExtractor)
		{
			this.name = name;
			comparator = Comparator.comparingDouble(keyExtractor);
		}
		
		@Override
		public String toString()
		{
			return name;
		}
	}
}
