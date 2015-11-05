package com.KanbeKotori.KeyCraft.Event;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import com.KanbeKotori.KeyCraft.Helper.*;
import com.KanbeKotori.KeyCraft.Items.ModItems;
import com.KanbeKotori.KeyCraft.Network.RewriteNetwork;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class SubscribePointAutoBuff {
	
	/** �ж�Skill200-�������������Ƿ��Ѿ�CD�����ǲ���ͬ��������������CD�� */
	public boolean isCD_Buff_ER(EntityPlayer player) {
    	if (System.currentTimeMillis() - player.getEntityData().getLong("LastTime_ER") >= 300000) {
    		player.getEntityData().setLong("LastTime_ER", System.currentTimeMillis());
    		if (!player.worldObj.isRemote) {
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.er")));
			}
    		return true;
    	}
    	return false;
    }
	
	/** �������Skill100-�������ɶ������ٶ�Buff */
	@SubscribeEvent
	public void Point_AutoSpeedUp(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		List entities = player.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(player.posX-8.0D, player.posY-2.0D, player.posZ-8.0D, player.posX+8.0D, player.posY+2.0D, player.posZ+8.0D));
		for (Iterator iterator = entities.iterator(); iterator.hasNext(); ) {
			EntityLiving entity = (EntityLiving)iterator.next();
			if(!entity.equals(player)) {
				if (RewriteHelper.hasSkill(player, RewriteHelper.HuntingRhythm.id)) {
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100));
				}
				return ;
			}
		}
	}
	
	/** �������Skill200-���������������ٶ�Buff */
	@SubscribeEvent
	public void Point_ER(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (RewriteHelper.hasSkill(player, RewriteHelper.UrgentProtect.id) && player.getHealth() <= 6 && RewriteHelper.getAuroraPoint(player) > 5) {
			if (isCD_Buff_ER(player)) {
	    		RewriteHelper.modifyAuroraPoint(player, -5);
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 1));
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 4));
				player.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 1));
			}
		}
	}
	
	/** �������Skill212-����������������������Buff */
	@SubscribeEvent
	public void Point_MoreHealth(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (RewriteHelper.hasSkill(player, RewriteHelper.PhysiqueUp.id)) {
			if (!player.isPotionActive(Potion.field_76434_w)) {
				player.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, 0x7FFFFFFF, 4));
			}
		}
	}
	
	/** �������Skill341-��ŷ�������\Skill342-��ŷ����ӿ�����������ظ�Buff */
	@SubscribeEvent
	public void Point_AutoHeal(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (player.getHealth() < 20 && !player.isPotionActive(Potion.regeneration)) {
			if (RewriteHelper.hasSkill(player, RewriteHelper.AuroraSurge.id)) {
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 1));
			} else if (RewriteHelper.hasSkill(player, RewriteHelper.AuroraActivation.id)) {
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100));
			}
		}
	}
	
	/** �������Skill���ർ�µ�ŷ�������ߵ��ٶȺ���Ծ����Buff */
	@SubscribeEvent
	public void Point_Burst(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (RewriteHelper.hasSkill(player, RewriteHelper.PhysiqueUp.id)
			&& RewriteHelper.hasSkill(player, RewriteHelper.WitherAttach.id)
			&& RewriteHelper.hasSkill(player, RewriteHelper.AuroraRob.id)
			&& RewriteHelper.hasSkill(player, RewriteHelper.FireResistMax.id) 
			&& RewriteHelper.hasSkill(player, RewriteHelper.UltimateHardening.id) 
			&& RewriteHelper.hasSkill(player, RewriteHelper.AuroraRegeneration.id) 
			) {
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 255));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 100, 99));
		}
	}
	
	/** ������ҳ��е��߸���ʱû��Skill232-���������Ļ���Buff */
	@SubscribeEvent
	public void Overweight(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		ItemStack held = player.getHeldItem();
		if (held != null) {
			if (held.getItem() == ModItems.WirePole) {
				if (!RewriteHelper.hasSkill(player, RewriteHelper.BruteForce.id)) {
					player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1)); 	
				}
			}
		}
	}

}
