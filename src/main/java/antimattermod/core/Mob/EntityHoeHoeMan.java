package antimattermod.core.Mob;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by worldofthetakumi on 2016/10/26.
 */
public class EntityHoeHoeMan extends EntityMob implements IBossDisplayData {

    public EntityHoeHoeMan(World world){
        super(world);
        tasks.addTask(1,new EntityAISwimming(this));
        tasks.addTask(2,new EntityAIAttackOnCollide(this, EntityPlayer.class,100,true));
        tasks.addTask(3,new EntityAIWander(this,1.0D));
        targetTasks.addTask(1,new EntityAINearestAttackableTarget(this,EntityPlayer.class,0,true));
        targetTasks.addTask(2,new EntityAIHurtByTarget(this,false));
    }

    @Override
    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(60D);
        getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(1280D);
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(3000);
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(3000);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(3);
    }
}
