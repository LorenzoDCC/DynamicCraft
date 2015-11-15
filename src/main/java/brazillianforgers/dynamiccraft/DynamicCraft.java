package brazillianforgers.dynamiccraft;

import brazillianforgers.dynamiccraft.handler.*;
import brazillianforgers.dynamiccraft.handler.events.HandlerClient;
import brazillianforgers.dynamiccraft.handler.events.HandlerCommon;
import brazillianforgers.dynamiccraft.lib.Strings;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Logger;

/**
 * DynamicCraft Mod
 * @author BrazillianForgers Team
 */

@Mod(modid = Strings.MODID , version = Strings.VERSION , name = Strings.MODNAME, dependencies = Strings.DEENDENCIES)
public class DynamicCraft {
	
	public static final int guiIdInfusion = 8;
    
    public static Logger log;
    
    @Mod.Instance(Strings.MODID)
    public static DynamicCraft mod;
    
    @SidedProxy(clientSide = Strings.CLIENT, serverSide = Strings.COMMON)
    public static Common proxy;
        
    @Mod.EventHandler
    public static void preLoad(FMLPreInitializationEvent e) {
        //Get Logger
        log = e.getModLog();
        //Init Logger
        log.info("Start Loading...");
        
        ItemHandler.init();
        BlockHandler.init();
        Common.registerEntities();
        Common.registerNetworkStuff();
        Client.registerRender();
        
        InfusionRecipes.registerRecipes();
        
        //End Logger
        log.info("All Pre-Init modules enabled!");
    }
	
    @Mod.EventHandler
    public static void load(FMLInitializationEvent e) {
    	FMLCommonHandler.instance().bus().register(new HandlerClient());
    	MinecraftForge.EVENT_BUS.register(new HandlerCommon());
    }
	
    @Mod.EventHandler 
    public static void postLoad(FMLPostInitializationEvent e) {
    	UpdateHandler.init();
    }
    
    public static CreativeTabs dynamicTab = new CreativeTabs("dynamicTab") {
		public Item getTabIconItem() {
	            return Items.diamond;
		}
    };
    
}
