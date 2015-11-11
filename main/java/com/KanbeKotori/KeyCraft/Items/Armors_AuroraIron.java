package com.KanbeKotori.KeyCraft.Items;

import com.KanbeKotori.KeyCraft.KeyCraft;
import com.KanbeKotori.KeyCraft.Helper.MaterialHelper;

import net.minecraft.entity.Entity;
import net.minecraft.item.*;

public class Armors_AuroraIron extends ItemArmor {
	
	int pos;

	/** position������λ�ã�0Ϊͷ����1Ϊ�ؼף�2Ϊ���ȣ�3Ϊѥ�ӡ� */
	public Armors_AuroraIron(int position) {
		super(MaterialHelper.AuroraIronArmor, 0, position);
		this.pos = position;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (pos == 2)	return KeyCraft.MODID + ":textures/models/armor/AuroraIron_layer_2.png";	
		return KeyCraft.MODID + ":textures/models/armor/AuroraIron_layer_1.png";	
	}
}
