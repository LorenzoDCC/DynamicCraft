package brazillianforgers.dynamiccraft.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import brazillianforgers.dynamiccraft.DynamicCraft;
import brazillianforgers.dynamiccraft.tileentities.TileEntityInfusionAltar;

public class DynamicGem extends BaseItem{

	private GemType type;
	
	public static enum GemType {
		SHARD,
		PEARL;
	}
	
	public DynamicGem(GemType type) {
		setCreativeTab(DynamicCraft.dynamicTab);
		this.type = type;
		setName();
	}
	
	private void setName() {
		if(type == GemType.PEARL) setUnlocalizedName("dynamicPearl");
		if(type == GemType.SHARD) setUnlocalizedName("dynamicShard");
	}
	
	public GemType getType() {
		return type;
	}
	
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		String cshift = EnumChatFormatting.DARK_PURPLE + EnumChatFormatting.BOLD.toString()
				+ StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(itemStack) + ".description.magic").trim()
				+ ": " + TileEntityInfusionAltar.getItemPower(itemStack);
		String cshift2 = EnumChatFormatting.DARK_PURPLE + EnumChatFormatting.BOLD.toString() + "Stack: " + TileEntityInfusionAltar.getItemPower(itemStack) * itemStack.stackSize;
		
		String sshift = EnumChatFormatting.DARK_PURPLE +  "<Press Shift>";
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			list.add(sshift);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			list.add(cshift);
			
			if(itemStack.stackSize > 1)
				list.add(cshift2);
		}
	}
	
}
