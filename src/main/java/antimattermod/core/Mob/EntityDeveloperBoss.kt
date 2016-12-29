package antimattermod.core.Mob

import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.boss.IBossDisplayData
import net.minecraft.entity.monster.EntityMob
import net.minecraft.world.World

/**
 * Created by kojin15.
 */
class EntityDeveloperBoss(world: World) : EntityMob(world), IBossDisplayData {
    
    
    
    init {

    }

    //取り敢えず無効に
    override fun isAIEnabled(): Boolean {
        return false
    }

    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        getEntityAttribute(SharedMonsterAttributes.attackDamage).baseValue = 60.0
        getEntityAttribute(SharedMonsterAttributes.followRange).baseValue = 1280.0
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).baseValue = 3000.0
        getEntityAttribute(SharedMonsterAttributes.maxHealth).baseValue = 3000.0
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).baseValue = 3.0
    }
}