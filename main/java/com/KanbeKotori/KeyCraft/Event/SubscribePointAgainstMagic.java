/**
 * Copyright (c) Nulla Development Group, 2014-2015
 * ����Ʒ��Ȩ��Nulla���������С�
 * Developed by Kanbe-Kotori & xfgryujk.
 * ����Ʒ�� Kanbe-Kotori & xfgryujk ����������
 * This project is open-source, and it is distributed under
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * ����Ŀ��һ����Դ��Ŀ������ѭGNUͨ�ù�����ȨЭ�顣
 * �����ո�Э�������£����������ɴ������޸ġ�
 * http://www.gnu.org/licenses/gpl.html
 */
package com.KanbeKotori.KeyCraft.Event;

import com.KanbeKotori.KeyCraft.Helper.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraftforge.event.entity.living.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SubscribePointAgainstMagic {
	
	/** �ж�Skill331-������ը��ǡ��Ƿ��Ѿ�CD�����ǲ���ͬ�����������л����������CD�� */
	public boolean isCD_against_arrow(EntityPlayer player) {
		final long time = player.worldObj.getTotalWorldTime();
    	if (time - player.getEntityData().getLong("LastTime_AgArrow") >= 5 * 20) {
    		player.getEntityData().setLong("LastTime_AgArrow", time);
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.againstexplosion")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �ж�Skill332-����ħ����ǡ��Ƿ��Ѿ�CD�����ǲ���ͬ�����������л����������CD�� */
	public boolean isCD_against_magic(EntityPlayer player) {
		final long time = player.worldObj.getTotalWorldTime();
    	if (time - player.getEntityData().getLong("LastTime_AgMagic") >= 30 * 20) {
    		player.getEntityData().setLong("LastTime_AgMagic", time);
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.againstmagic")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �ж�Skill333-���ռ�Ӳ����ǡ��Ƿ��Ѿ�CD�����ǲ���ͬ�����������л����������CD�� */
	public boolean isCD_against_magic_plus(EntityPlayer player) {
		final long time = player.worldObj.getTotalWorldTime();
    	if (time - player.getEntityData().getLong("LastTime_AgMagicPlus") >= 30 * 20) {
    		player.getEntityData().setLong("LastTime_AgMagicPlus", time);
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
			if (event.source.damageType.equals("arrow")
				|| event.source.damageType.equals("explosion")
				|| event.source.damageType.equals("explosion.player")
				) {
				if (RewriteHelper.hasSkill(player, RewriteHelper.UltimateHardening.id)) {
    				event.setCanceled(true);
    				isCD_against_magic_plus(player);
    			} else if (RewriteHelper.hasSkill(player, RewriteHelper.ExplosionResist.id)) {
    				event.setCanceled(true);
    				if (isCD_against_arrow(player)) {
    					RewriteHelper.modifyAuroraPoint(player, -1);
    				}
    			}
    		} else if (event.source.damageType.equals("magic")
    				   || event.source.damageType.equals("indirectMagic")
    				   ) {
    			if (RewriteHelper.hasSkill(player, RewriteHelper.UltimateHardening.id)) {
    				event.setCanceled(true);
    				isCD_against_magic_plus(player);
    			} else if (RewriteHelper.hasSkill(player, RewriteHelper.MagicResist.id)) {
    				event.setCanceled(true);
    				if (isCD_against_magic(player)) {
    					RewriteHelper.modifyAuroraPoint(player, -1);
    				}
    			}
    		} 
		}
	}

}
