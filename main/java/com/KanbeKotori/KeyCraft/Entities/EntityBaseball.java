package com.KanbeKotori.KeyCraft.Entities;

import com.KanbeKotori.KeyCraft.Helper.RewriteHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBaseball extends EntityThrowable {

	protected static final float SPEED_NO_SKILL = 3.0F;
	protected static final float SPEED_HAS_SKILL = 10.0F;
	protected static final float DAMAGE_NO_SKILL = 5.0F;
	protected static final float DAMAGE_HAS_SKILL = 10.0F;
	
	public EntityBaseball(World world) {
        super(world);
    }
	
	public EntityBaseball(World world, EntityLivingBase thrower) {
        super(world, thrower);
        
        // ��������λ�ú��ٶ�
        this.setLocationAndAngles(thrower.posX, thrower.posY + (double)thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        if (thrower instanceof EntityPlayer) {
        	this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 
        							 RewriteHelper.hasSkill((EntityPlayer)thrower, RewriteHelper.BruteForce.id) ? SPEED_HAS_SKILL : SPEED_NO_SKILL, 0.0F);
        } else {
        	this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, SPEED_NO_SKILL, 0.0F);
        }
    }
	
	protected void onImpact(MovingObjectPosition target) {
        if (target.entityHit != null) {
        	EntityLivingBase thrower = this.getThrower();
        	if (thrower instanceof EntityPlayer) {
        		target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, thrower), 
        										  RewriteHelper.hasSkill((EntityPlayer)thrower, RewriteHelper.BruteForce.id) ? SPEED_HAS_SKILL : DAMAGE_NO_SKILL);
        	} else {
        		target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, thrower), DAMAGE_NO_SKILL);
        	}
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }

}
