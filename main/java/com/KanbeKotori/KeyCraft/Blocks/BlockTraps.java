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

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.*;
import net.minecraft.world.World;

import com.KanbeKotori.KeyCraft.Helper.RewriteHelper;

import cpw.mods.fml.relauncher.*;

public abstract class BlockTraps extends Block implements ITileEntityProvider {

	protected BlockTraps(EntityLivingBase layer) {
		super(Material.rock);
	}
	
	/** �����鱻����ʱ���ô˷����� */
	@Override
    public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase entity, ItemStack stack) {}
	
	/** ��ʵ���߹�����ʱ���ô˷����� */
	@Override
    public void onEntityWalking(World world, int posX, int posY, int posZ, Entity entity) {}
	
	/** �����鱻�һ�ʱ���ô˷����� */
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		TileEntityTrap tile = (TileEntityTrap)world.getTileEntity(x, y, z);
		if (tile.ownerName.isEmpty()) {
			tile.ownerName = player.getDisplayName();
			tile.markDirty();
		}
		
		ItemStack held = player.getHeldItem();
		if (player.getDisplayName().equals(tile.ownerName) && held != null) {
			Block block = Block.getBlockFromItem(held.getItem());
			if (block != null) {
				tile.fakeBlockID = Block.getIdFromBlock(block);
				tile.markDirty();
			}
		}
    	return true;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
    
	@Override
    public boolean renderAsNormalBlock() {
        return false;
    }

	@Override
	public int getRenderType() {
        return -1;
    }
    
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityTrap();
    }

}
