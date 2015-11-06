package com.KanbeKotori.KeyCraft.Event;

import com.KanbeKotori.KeyCraft.Helper.RewriteHelper;
import com.KanbeKotori.KeyCraft.Helper.RewriteHelper.Skill;
import com.KanbeKotori.KeyCraft.Network.RewriteNetwork;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SubscribeOnDead {

	/**
	 * ���������ʱ������ҵ�����Skill�Լ�ŷ����������¡��Respawn�Ժ�������
	 * �ڴ�ĩ�ػص�������ʱҲ�����PlayerEvent.Clone��PlayerRespawnEvent�����������PlayerChangedDimensionEvent
	 */
	@SubscribeEvent
    public void OnDeadClone(PlayerEvent.Clone event) {
		final EntityPlayer _old = event.original;
		final EntityPlayer _new = event.entityPlayer;

		final NBTTagCompound newData = _new.getEntityData();
		// ���������ŷ�����������л�����������䣬������10
		final int newAuroraPoint = event.wasDeath ? Math.max(RewriteHelper.getAuroraPoint(_old) - 10, 0) : RewriteHelper.getAuroraPoint(_old);
		newData.setInteger("SkillPoint", newAuroraPoint);
		for (Skill i : RewriteHelper.SKILLS) {
			final String name = "Skill" + String.format("%03d", i.id);
			newData.setBoolean(name, RewriteHelper.hasSkill(_old, i.id));
		}
		
		// ������л������ͬ����@RewriteNetwork.SendSyncPacket.onPlayerRespawn
	}

}
