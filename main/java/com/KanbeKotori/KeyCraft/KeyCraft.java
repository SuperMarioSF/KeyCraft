package com.KanbeKotori.KeyCraft;

import com.KanbeKotori.KeyCraft.Event.*;
import com.KanbeKotori.KeyCraft.Items.*;
import com.KanbeKotori.KeyCraft.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.*;

@Mod(modid = KeyCraft.MODID, version = KeyCraft.VERSION)
public class KeyCraft {
	
	//Made by Kanbe-Kotori&xfgryujk
	public static final String MODID = "keycraft";
    public static final String VERSION = "Demo-2.3.1";
    
    public static CreativeTabs CreativeTab = new CreativeTab("KeyCraft");
    
    public static Item PointShakingSword;
    public static Item PointAuroraTrident;
    public static Item PointAuroraBlade;
    public static Item SanaeBread;
    public static Item PeachJuice;
    public static Item MapoTofu;
    public static Item PizzaJam;
    
    @SidedProxy(clientSide = "com.KanbeKotori.KeyCraft.ClientProxy",
            	serverSide = "com.KanbeKotori.KeyCraft.CommonProxy")
    public static CommonProxy proxy;
 
    @Instance("KeyCraft")
    public static KeyCraft instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit(event);
    	
    	PointShakingSword = new PointShakingSword();
    	PointShakingSword.setUnlocalizedName("PointShakingSword").setTextureName("keycraft:PointShakingSword").setCreativeTab(null);
    	GameRegistry.registerItem(PointShakingSword, "PointShakingSword");
    	
    	PointAuroraTrident = new PointAuroraTrident();
    	PointAuroraTrident.setUnlocalizedName("PointAuroraTrident").setTextureName("keycraft:PointAuroraTrident").setCreativeTab(null);
    	GameRegistry.registerItem(PointAuroraTrident, "PointAuroraTrident");
    	
    	PointAuroraBlade = new PointAuroraBlade();
		PointAuroraBlade.setUnlocalizedName("PointAuroraBlade").setTextureName("keycraft:PointAuroraBlade").setCreativeTab(null);
    	GameRegistry.registerItem(PointAuroraBlade, "PointAuroraBlade");
    	
    	SanaeBread = (new ItemCallbackFood(20)).setCallback(new ItemCallbackFood.ICallback(){
					public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
						if (!world.isRemote) {
				            player.setHealth(1);
				        }
					}
		    	}).setUnlocalizedName("SanaeBread").setTextureName("keycraft:SanaeBread").setCreativeTab(CreativeTab);
    	GameRegistry.registerItem(SanaeBread, "SanaeBread");
    	GameRegistry.addRecipe(new ItemStack(SanaeBread), new Object[] { "AAA", "ABA", "AAA", 'A', Items.rotten_flesh, 'B', Items.bread });
    	
    	PeachJuice = (new ItemFood(8, false)).setPotionEffect(Potion.moveSlowdown.id, 10, 3, 1.0F).setUnlocalizedName("PeachJuice")
    			.setTextureName("keycraft:PeachJuice").setCreativeTab(CreativeTab);
    	GameRegistry.registerItem(PeachJuice, "PeachJuice");
    	GameRegistry.addShapelessRecipe(new ItemStack(PeachJuice), new Object[] { Items.apple, Items.slime_ball });
    	
    	MapoTofu = (new ItemCallbackFood(10)).setCallback(new ItemCallbackFood.ICallback(){
					public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
						if (!world.isRemote) {
							player.setFire(5);
				        }
					}
		    	}).setPotionEffect(Potion.moveSpeed.id, 60, 3, 1.0F).setUnlocalizedName("MapoTofu").setTextureName("keycraft:MapoTofu")
	    		.setCreativeTab(CreativeTab);
    	GameRegistry.registerItem(MapoTofu, "MapoTofu");
    	GameRegistry.addRecipe(new ItemStack(MapoTofu), new Object[] { "ABA", "ACA", "AAA", 'A', new ItemStack(Items.dye, 1, 1), 
    			'B', Items.blaze_powder, 'C', Items.bowl });
    	
    	PizzaJam = (new ItemFood(20, false)).setPotionEffect(Potion.field_76443_y.id, 60, 0, 1.0F).setUnlocalizedName("PizzaJam")
    			.setTextureName("keycraft:PizzaJam").setCreativeTab(CreativeTab);
    	GameRegistry.registerItem(PizzaJam, "PizzaJam");
    	//GameRegistry.addRecipe(new ItemStack(PizzaJam), new Object[] {  });
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(new SubscribeCheating());
    	MinecraftForge.EVENT_BUS.register(new SubscribeOnAttack());
    	MinecraftForge.EVENT_BUS.register(new SubscribePointAgainstFire());
    	MinecraftForge.EVENT_BUS.register(new SubscribePointAgainstMagic());
    	MinecraftForge.EVENT_BUS.register(new SubscribePointAutoBuff());
    	MinecraftForge.EVENT_BUS.register(new SubscribeOnDead());
    	MinecraftForge.EVENT_BUS.register(new SubscribeShakingSwordUsing());
    	MinecraftForge.EVENT_BUS.register(new PointAuroraBlade());
    	MinecraftForge.EVENT_BUS.register(new SubscribeAuroraRecycle());

    	FMLCommonHandler.instance().bus().register(new SubscribeShakingSwordUsing());
    	FMLCommonHandler.instance().bus().register(new SubscribePointAutoBuff());
    	FMLCommonHandler.instance().bus().register(new SubscribePointAutoRecovery());
    	FMLCommonHandler.instance().bus().register(new SubscribeAuroraRecycle());
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    }

}
