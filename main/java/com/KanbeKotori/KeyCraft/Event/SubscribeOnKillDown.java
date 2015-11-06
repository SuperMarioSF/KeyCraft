package com.KanbeKotori.KeyCraft.Event;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.*;

import com.KanbeKotori.KeyCraft.Helper.*;
import com.KanbeKotori.KeyCraft.Network.RewriteNetwork;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SubscribeOnKillDown {
	
	/** 给予玩家Skill243-『欧若拉掠夺』的欧若拉点数 */
	@SubscribeEvent
    public void Point_AuroraRob(LivingDeathEvent event) {
		if ((event.source.getEntity() instanceof EntityPlayer)) {
			EntityPlayer player = (EntityPlayer)event.source.getEntity();
			if (!player.worldObj.isRemote // 随机事件只发生在服务器
				&& RewriteHelper.hasSkill(player, RewriteHelper.AuroraRob.id)
				) {
				if (new Random().nextInt(16) == 8) {
					RewriteHelper.modifyAuroraPoint(player, 1);
					player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.aurorarob")));
				} else {
					//测试用
					//player.addChatMessage(new ChatComponentText("false"));
				}
			}
		}
	}

}
