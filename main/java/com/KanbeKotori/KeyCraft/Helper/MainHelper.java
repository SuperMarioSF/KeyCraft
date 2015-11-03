package com.KanbeKotori.KeyCraft.Helper;

import java.util.ArrayList;
import java.util.ListIterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class MainHelper {
	
	/**
	 *ֻ���ڿͻ�����
	 */
	public static EntityPlayer getPlayerCl() {
		return Minecraft.getMinecraft().thePlayer;
	}
	
	/**
	 * ���ܷ���null
	 * ֻ���ڿͻ�����
	 */
	public static EntityPlayer getPlayerSv() {
		return getPlayerSv(getName());
	}
	
	/** 
	 * ���ܷ���null
	 */
	public static EntityPlayer getPlayerSv(String name) {
	    ServerConfigurationManager server = MinecraftServer.getServer().getConfigurationManager();
	    
	    for (ListIterator list = server.playerEntityList.listIterator(); list.hasNext(); ) {
	        EntityPlayer p = (EntityPlayer)list.next();
	        if(p.getGameProfile().getName().equals(name)){
	            return p;
	        }
	    }
	    return null;
	}
	
	/** 
	 * ���ܷ���null
	 */
	public static EntityPlayer getPlayerSv(EntityPlayer player) {
		if (player.worldObj.isRemote) {
			return player;
		} else {
			return getPlayerSv(player.getDisplayName());
		}
	}

	/**
	 * ���ܷ���null
	 * ֻ���ڿͻ�����
	 */
	public static String getName() {
		if (getPlayerCl() != null) {
			return getPlayerCl().getDisplayName();
		}
		return null;
	}

}
