package com.KanbeKotori.KeyCraft.Event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraftforge.event.entity.living.*;

import com.KanbeKotori.KeyCraft.Helper.*;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class SubscribePointAgainstFire {
	
	/** �ж�Skill321-�������Ƿ��Ѿ�CD�����ǲ���ͬ�����������л����������CD�� */
	public boolean isCD_against_fire(EntityPlayer player) {
		final long time = player.worldObj.getTotalWorldTime();
    	if (time - player.getEntityData().getLong("LastTime_AgFire") >= 60 * 20) {
    		player.getEntityData().setLong("LastTime_AgFire", time);
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.againstfire")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �ж�Skill322-������UP���Ƿ��Ѿ�CD�����ǲ���ͬ�����������л����������CD�� */
	public boolean isCD_against_lava(EntityPlayer player) {
		final long time = player.worldObj.getTotalWorldTime();
    	if (time - player.getEntityData().getLong("LastTime_AgLava") >= 30 * 20) {
    		player.getEntityData().setLong("LastTime_AgLava", time);
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.againstfireplus")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �ж�Skill322-������UP��\Skill323-������MAX�����µ�����������˺��Ƿ��Ѿ�CD�����ǲ���ͬ�����������л����������CD�� */
	public boolean isCD_mention(EntityPlayer player) {
		final long time = player.worldObj.getTotalWorldTime();
    	if (time - player.getEntityData().getLong("LastTime_IgnoreFire") >= 30 * 20) {
    		player.getEntityData().setLong("LastTime_IgnoreFire", time);
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.againstfiremax")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �����ΪSkill321-������\Skill322-������UP��\Skill323-������MAX�������ĳЩ�˺� */
	@SubscribeEvent
	public void PointAgainstFireAndLava(LivingHurtEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			if (event.source.damageType.equals("lava")) {
				if (RewriteHelper.hasSkill(player, RewriteHelper.FireResistMax.id)) {
    				event.setCanceled(true); 
    				isCD_mention(player);
    			} else if (RewriteHelper.hasSkill(player, RewriteHelper.FireResistUp.id)) {
    				event.setCanceled(true);
    				if (isCD_against_lava(player)) {
    					RewriteHelper.modifyAuroraPoint(player, -1);
    				}
    			}
    		} else if (event.source.damageType.equals("inFire") || event.source.damageType.equals("onFire")) {
    			if (RewriteHelper.hasSkill(player, RewriteHelper.FireResistUp.id)) {
    				event.setCanceled(true); 
    				isCD_mention(player);
    			} else if (RewriteHelper.hasSkill(player, RewriteHelper.FireResist.id)) {
    				event.setCanceled(true);
    				if (isCD_against_fire(player)) {
    					RewriteHelper.modifyAuroraPoint(player, -1);
    				}
    			}
    		} 
		}
	}

}
