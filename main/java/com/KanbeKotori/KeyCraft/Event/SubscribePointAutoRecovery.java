package com.KanbeKotori.KeyCraft.Event;

import java.util.Random;

import com.KanbeKotori.KeyCraft.Helper.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class SubscribePointAutoRecovery {
	
	/** �������Skill343-��ŷ������������ŷ�������� */
	@SubscribeEvent
	public void Point_AuroraAutoRecover(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (!player.worldObj.isRemote // ����¼�ֻ�����ڷ�����
			&& RewriteHelper.hasSkill(player, RewriteHelper.AuroraRegeneration.id)
			&& new Random().nextInt(2400) == 1200
			) {
			RewriteHelper.modifyAuroraPoint(player, 1);
			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.aurorarecovery")));
		}
	}

}
