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
	 * ������ʵ�����ҵ�ŷ����������
	 * ��������ת�������������¡���������䣻
	 * ����۳�10��ŷ����������㹻�Ļ�����
	 */
	private int getPointAppropriate(EntityPlayer _old) {
		int point_old = RewriteHelper.getAuroraPoint(_old);
		if (_old.isDead)
			return Math.max(point_old - 10, 0);
		return point_old;
	}

	/**
	 * ���������ʱ������ҵ�����Skill�Լ�ŷ����������¡��Respawn�Ժ�������
	 * ���л�����ʱҲ�����
	 */
	@SubscribeEvent
    public void OnDeadClone(PlayerEvent.Clone event) {
		final EntityPlayer _old = event.original;
		final EntityPlayer _new = event.entityPlayer;

		final NBTTagCompound newData = _new.getEntityData();
		// ���������ŷ��������
		newData.setInteger("SkillPoint", getPointAppropriate(_old));
		for (Skill i : RewriteHelper.SKILLS) {
			final String name = "Skill" + String.format("%03d", i.id);
			newData.setBoolean(name, RewriteHelper.hasSkill(_old, i.id));
		}
				
		// �����ͬ����@RewriteNetwork.SendSyncPacket.onPlayerRespawn
	}

}
