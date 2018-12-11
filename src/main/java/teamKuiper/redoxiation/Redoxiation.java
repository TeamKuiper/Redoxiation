package teamKuiper.redoxiation;

import codechicken.lib.packet.PacketCustom;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
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
import teamKuiper.redoxiation.advancement.AdvancementEvents;
import teamKuiper.redoxiation.advancement.RedoxiationAdvancement;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.blocks.gui.GuiHandler;
import teamKuiper.redoxiation.handlers.PacketHandler;
import teamKuiper.redoxiation.handlers.RedoxiationConfigHandler;
import teamKuiper.redoxiation.handlers.RedoxiationGenHandler;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;
import teamKuiper.redoxiation.multipart.MultiPartEventHandler;
import teamKuiper.redoxiation.multipart.RegisterBlockPart;
import teamKuiper.redoxiation.proxy.CommonProxy;
import teamKuiper.redoxiation.recipes.RedoxiationRecipes;

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
		public Item getTabIconItem() {
			return Item.getItemFromBlock(RedoxiationBlocks.oreCopper);
		}
	};

	public static final CreativeTabs tabRedoxiationitems = new CreativeTabs("RedoxiationItems") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return RedoxiationGenericItems.Calcite;
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
        RedoxiationRecipes.RecipesMember();
		
		// Simple Config
		config = new Configuration(event.getSuggestedConfigurationFile());
		RedoxiationConfigHandler.initConfig();
		
		// Config end
		RedoxiationBlocks.registerBlocks();
		RedoxiationGenericItems.registerItems();
		proxy.registerTileEntitySpecialRenderer();
		RedoxiationRecipeManager.recipeFurnace();
		RedoxiationRecipeManager.recipeCrafting();
		
		if (RedoxiationAdvancement.isachivenable) {
			RedoxiationAdvancement.addDefaultAchievements();
		}
		
		GameRegistry.registerWorldGenerator(handler, 0);
		NetworkRegistry.INSTANCE.registerGuiHandler(Redoxiation.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiHandler.getGuiID());

        if (Loader.isModLoaded("ForgeMultipart")) {
            new RegisterBlockPart().init();
        }
        MinecraftForge.EVENT_BUS.register(new MultiPartEventHandler());
        MinecraftForge.EVENT_BUS.register(new teamKuiper.redoxiation.EventHandler());

        PacketCustom.assignHandler(this, new PacketHandler());    }

	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		if (RedoxiationAdvancement.isachivenable) {
			RedoxiationAdvancement.registerAchievementPane();
			MinecraftForge.EVENT_BUS.register(new AdvancementEvents());
			logger.info("ACHIVLOADED");
		}
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
	}
}
