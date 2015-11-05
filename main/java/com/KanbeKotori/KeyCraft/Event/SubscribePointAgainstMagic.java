package com.KanbeKotori.KeyCraft.Event;

import com.KanbeKotori.KeyCraft.Helper.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SubscribePointAgainstMagic {
	
	/** �ж�Skill331-������ը��ǡ��Ƿ��Ѿ�CD�����ǲ���ͬ��������������CD�� */
	public boolean isCD_against_arrow(EntityPlayer player) {
		if (System.currentTimeMillis() - player.getEntityData().getLong("LastTime_AgArrow") >= 5000) {
    		player.getEntityData().setLong("LastTime_AgArrow", System.currentTimeMillis());
			RewriteHelper.modifyAuroraPoint(player, -1);
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.againstexplosion")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �ж�Skill332-����ħ����ǡ��Ƿ��Ѿ�CD�����ǲ���ͬ��������������CD�� */
	public boolean isCD_against_magic(EntityPlayer player) {
		if (System.currentTimeMillis() - player.getEntityData().getLong("LastTime_AgMagic") >= 30000) {
    		player.getEntityData().setLong("LastTime_AgMagic", System.currentTimeMillis());
			RewriteHelper.modifyAuroraPoint(player, -1);
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.againstmagic")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �ж�Skill333-���ռ�Ӳ����ǡ��Ƿ��Ѿ�CD�����ǲ���ͬ��������������CD�� */
	public boolean isCD_against_magic_plus(EntityPlayer player) {
		if (System.currentTimeMillis() - player.getEntityData().getLong("LastTime_AgMagicPlus") >= 30000) {
    		player.getEntityData().setLong("LastTime_AgMagicPlus", System.currentTimeMillis());
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.againstmagicplus")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �����ΪSkill331-������ը��ǡ�\Skill332-����ħ����ǡ�\Skill333-���ռ�Ӳ����ǡ������ĳЩ�˺� */
	@SubscribeEvent
	public void PointAgainstMagic(LivingHurtEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
    		EntityPlayer player = (EntityPlayer)event.entityLiving;
			if (event.source.damageType.equals("arrow") || event.source.damageType.equals("explosion") || event.source.damageType.equals("explosion.player")) {
				if (RewriteHelper.hasSkill(player, RewriteHelper.UltimateHardening.id)) {
    				event.setCanceled(true);
    				isCD_against_magic_plus(player);
    			} else if (RewriteHelper.hasSkill(player, RewriteHelper.ExplosionResist.id)) {
    				event.setCanceled(true);
    				isCD_against_arrow(player);
    			}
    		} else if (event.source.damageType.equals("magic") || event.source.damageType.equals("indirectMagic")) {
    			if (RewriteHelper.hasSkill(player, RewriteHelper.UltimateHardening.id)) {
    				event.setCanceled(true);
    				isCD_against_magic_plus(player);
    			} else if (RewriteHelper.hasSkill(player, RewriteHelper.MagicResist.id)) {
    				event.setCanceled(true);
    				isCD_against_magic(player);
    			}
    		} 
		}
	}

}
