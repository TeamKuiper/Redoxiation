package teamKuiper.redoxiation;

import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import teamKuiper.redoxiation.achievement.AchievementEvents;
import teamKuiper.redoxiation.achievement.RedoxiationAchievements;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.gui.GuiHandler;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;
import teamKuiper.redoxiation.multipart.MultiPartEventHandler;
import teamKuiper.redoxiation.multipart.RegisterBlockPart;
import teamKuiper.redoxiation.proxy.CommonProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Redoxiation.MODID, version = Redoxiation.VERSION, name = Redoxiation.NAME, dependencies="after:ForgeMultipart" )
public class Redoxiation {
	@Instance(Redoxiation.MODID)
	public static Redoxiation instance;

	@SidedProxy(clientSide = "teamKuiper.redoxiation.proxy.ClientProxy", serverSide = "teamKuiper.redoxiation.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "redoxiation";
	public static final String VERSION = "Alpha 0.05";
	public static final String NAME = "Redoxiation";

	public static boolean dummybool;
	public static int oredif;
	public static Configuration config;

	public static final CreativeTabs tabRedoxiation = new CreativeTabs("Redoxiation") {
		private static final String __OBFID = "CL_00000080";

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getItemFromBlock(RedoxiationBlocks.oreCopper);
		}
	};

	public static final CreativeTabs tabRedoxiationitems = new CreativeTabs("RedoxiationItems") {
		private static final String __OBFID = "CL_00000081";

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return RedoxiationGenericItems.Calcite;
		}
	};

	@Metadata
	public ModMetadata meta;

	RedoxiationEventHandler handler = new RedoxiationEventHandler();

	public static org.apache.logging.log4j.Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("Hello World!");
		
		// Simple Config
		config = new Configuration(event.getSuggestedConfigurationFile());
		RedoxiationConfigHandler.InitConfig();
		
		// Config end
		RedoxiationBlocks.registerBlocks();
		RedoxiationGenericItems.registerItems();
		proxy.registerTileEntitySpecialRenderer();
		RedoxiationRecipeManager.recipeFurnace();
		RedoxiationRecipeManager.recipeCrafting();
		
		if (RedoxiationAchievements.isachivenable) {
			RedoxiationAchievements.addDefaultAchievements();
		}
		
		GameRegistry.registerWorldGenerator(handler, 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(Redoxiation.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiHandler.getGuiID());

        if (Loader.isModLoaded("ForgeMultipart")) {
            new RegisterBlockPart().init();
        }
        MinecraftForge.EVENT_BUS.register(new MultiPartEventHandler());

        PacketCustom.assignHandler(this, new PacketHandler());
//        if(FMLCommonHandler.instance().getSide().isClient())
//            PacketCustom.assignHandler(this, new McMultipartCPH());
    }

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		if (RedoxiationAchievements.isachivenable) {
			RedoxiationAchievements.registerAchievementPane();
			MinecraftForge.EVENT_BUS.register(new AchievementEvents());
			logger.info("ACHIVLOADED");
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
	}
}
