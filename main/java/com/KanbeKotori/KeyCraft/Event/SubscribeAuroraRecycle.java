package com.KanbeKotori.KeyCraft.Event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;

import com.KanbeKotori.KeyCraft.KeyCraft;
import com.KanbeKotori.KeyCraft.Helper.*;
import com.KanbeKotori.KeyCraft.Items.ModItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class SubscribeAuroraRecycle {
	
	@SubscribeEvent
	public void Aurora_Recycle(PlayerTickEvent event) {
		String name = MainHelper.getName();
		EntityPlayer player = MainHelper.getPlayerSv(name);
		ItemStack itemstacks[] = new ItemStack[36];
		ItemStack held = player.getHeldItem();
		
		for (int i = 0; i < 36; i++) {
			if ((itemstacks[i] = player.inventory.mainInventory[i]) != null) {
				if (itemstacks[i].getItem() == ModItems.AuroraTrident) {
					if (held != itemstacks[i]) {
						double pp = (double)itemstacks[i].getItemDamage() / itemstacks[i].getMaxDamage();
						player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.recycletrident")));
						EventOnAuroraRecycle EventOnAuroraRecycle = new EventOnAuroraRecycle(player, pp);
			            MinecraftForge.EVENT_BUS.post(EventOnAuroraRecycle);
			            player.inventory.mainInventory[i] = null;
					}
				} else if (itemstacks[i].getItem() == ModItems.AuroraBlade) {
					if (held != itemstacks[i]) {
						double pp = (double)itemstacks[i].getItem().getDamage(itemstacks[i]) / itemstacks[i].getMaxDamage();
						player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.recycleblade")));
						EventOnAuroraRecycle EventOnAuroraRecycle = new EventOnAuroraRecycle(player, pp);
			            MinecraftForge.EVENT_BUS.post(EventOnAuroraRecycle);
			            player.inventory.mainInventory[i] = null;
					}
				}	
			}
		}
		
	}
	
	@SubscribeEvent
	public void Aurora_Recycle(EventOnAuroraRecycle event) {
		String name = MainHelper.getName();
		EntityPlayer player = MainHelper.getPlayerSv(name);
		if (event.proportion == 0) {
			RewriteHelper.addAuroraPoint(player, 1);
		} else {
			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("keycraft.prompt.recyclerate") + event.proportion));
			int time = (int)(6000 * event.proportion);
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, time, 1));
			player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, time, 3));
			player.addPotionEffect(new PotionEffect(Potion.confusion.id, time));
			player.addPotionEffect(new PotionEffect(Potion.weakness.id, time, 3));
		}
	}

}
