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
package com.KanbeKotori.KeyCraft.Blocks;

import com.KanbeKotori.KeyCraft.Helper.RewriteHelper;

import cpw.mods.fml.relauncher.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class BlockTrapBlood extends BlockTraps {

	protected BlockTrapBlood(EntityLivingBase layer) {
		super(layer);
		this.owner = layer;
	}
	
	/** �����鱻����ʱ���ô˷����� */
	@Override
    public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase entity, ItemStack stack) {
		this.owner = entity;
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			if (!RewriteHelper.hasSkill(player, RewriteHelper.BloodTrap.id)) {
				world.setBlockToAir(posX, posY, posZ);
				stack.stackSize++;
				if (owner instanceof EntityPlayer && world.isRemote)	{
					player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.notrapskill")));
				}
			}
		}
	}
	
	/** ��ʵ���߹�����ʱ���ô˷����� */
	@Override
    public void onEntityWalking(World world, int posX, int posY, int posZ, Entity entity) {
        super.onEntityWalking(world, posX, posY, posZ, entity);
        if (!entity.equals(owner)) {
        	world.setBlockToAir(posX, posY, posZ);
        	DamageSource source;
        	if (owner instanceof EntityPlayer)	{
        		source = DamageSource.causePlayerDamage((EntityPlayer)owner);
        	} else {
        		source = DamageSource.magic;
        	}
        	entity.attackEntityFrom(source, 30.0F);
        } else {
        	if (owner instanceof EntityPlayer && world.isRemote)	{
        		((EntityPlayer) owner).addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.yourtrap")));
        	}
        }
    }

}
