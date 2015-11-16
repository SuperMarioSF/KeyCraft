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

import com.KanbeKotori.KeyCraft.KeyCraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModBlocks {
	
    public static Block NormalTrap;
    public static Block BloodTrap;


    public static void InitBlocks() {
    	NormalTrap = new BlockTrapNormal(null)
    		.setBlockName("NormalTrap")
    		.setBlockTextureName("keycraft:NormalTrap")
    		.setHardness(1.5F)
    		.setResistance(10.0F)
    		.setLightLevel(1)
    		.setCreativeTab(KeyCraft.CreativeTab);
    	GameRegistry.registerBlock(NormalTrap, "NormalTrap");
    	GameRegistry.addRecipe(new ItemStack(NormalTrap), new Object[] { " A ", "BCB", " B ", 'A', Blocks.wooden_pressure_plate, 'B', Items.iron_ingot, 'C', Blocks.glass });
    	GameRegistry.addRecipe(new ItemStack(NormalTrap), new Object[] { " A ", "BCB", " B ", 'A', Blocks.stone_pressure_plate, 'B', Items.iron_ingot, 'C', Blocks.glass });

    	BloodTrap = new BlockTrapBlood(null)
    		.setBlockName("BloodTrap")
    		.setBlockTextureName("keycraft:BloodTrap")
    		.setHardness(1.5F)
    		.setResistance(10.0F)
    		.setLightLevel(1)
    		.setCreativeTab(KeyCraft.CreativeTab);
    	GameRegistry.registerBlock(BloodTrap, "BloodTrap");
    		
    }
}
