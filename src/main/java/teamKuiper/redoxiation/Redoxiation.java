package teamKuiper.redoxiation;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamKuiper.redoxiation.blocks.gui.GuiHandler;
import teamKuiper.redoxiation.blocks.rocks.BlockOverworldOre;
import teamKuiper.redoxiation.handlers.RedoxiationConfigHandler;
import teamKuiper.redoxiation.handlers.RedoxiationGenHandler;
import teamKuiper.redoxiation.handlers.RedoxiationRegistryHandler;
import teamKuiper.redoxiation.items.ItemCommon;
import teamKuiper.redoxiation.multipart.MultiPartEventHandler;
import teamKuiper.redoxiation.multipart.RegisterBlockPart;
import teamKuiper.redoxiation.proxy.CommonProxy;

@Mod(modid = Redoxiation.MODID, version = Redoxiation.VERSION, name = Redoxiation.NAME, dependencies="after:ForgeMultipart" )
public class Redoxiation {
	@Instance(Redoxiation.MODID)
	public static Redoxiation instance;

	@SidedProxy(clientSide = "teamKuiper.redoxiation.proxy.ClientProxy", serverSide = "teamKuiper.redoxiation.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "redoxiation";
	public static final String VERSION = "1.1-1a4";
	public static final String NAME = "Redoxiation";

	public static boolean dummybool;
	public static int oredif;
	public static Configuration config;

	public static final CreativeTabs tabRedoxiation = new CreativeTabs("Redoxiation") {
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return BlockOverworldOre.oreCopper;
		}
	};

	public static final CreativeTabs tabRedoxiationitems = new CreativeTabs("RedoxiationItems") {
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return ItemCommon.calcite;
		}
	};

	@Metadata
	public ModMetadata meta;

	RedoxiationGenHandler handler = new RedoxiationGenHandler();

	public static org.apache.logging.log4j.Logger logger;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("Hello World!");
		
		// Simple Config
		config = new Configuration(event.getSuggestedConfigurationFile());
		RedoxiationConfigHandler.initConfig();
		
		MinecraftForge.EVENT_BUS.register(new RedoxiationRegistryHandler());
		RedoxiationRecipeManager.recipeFurnace();
		RedoxiationRecipeManager.recipeCrafting();
		
		GameRegistry.registerWorldGenerator(handler, 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(Redoxiation.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiHandler.getGuiID());

        if (Loader.isModLoaded("ForgeMultipart")) {
            new RegisterBlockPart().init();
        }
        MinecraftForge.EVENT_BUS.register(new MultiPartEventHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
	}
}
