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
package com.KanbeKotori.KeyCraft.Items;

import com.KanbeKotori.KeyCraft.Helper.MaterialHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class ItemAuroraIronShovel extends ItemSpade {
	
	public ItemAuroraIronShovel() {
		super(MaterialHelper.AuroraIron);
	}

	/** ��Ʒ��+2��������ƻ������Ӧ������ */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (stack.getItemDamage() >= this.getMaxDamage() - 1) {
			if (attacker instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)attacker;
				player.setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel, 1, 0));
				return true;
			}		
		}
		stack.damageItem(2, attacker);
		return true;
	}

	/** ��Ʒ��+1��������ƻ������Ӧ������ */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, int posX, int posY ,int posZ, EntityLivingBase entity) {
		if (stack.getItemDamage() >= this.getMaxDamage()) {
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)entity;
				player.setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel, 1, 0));
				return true;
			}	
		}
		stack.damageItem(1, entity);
		return true;
	}

}