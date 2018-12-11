package teamKuiper.redoxiation.handlers;

import net.minecraftforge.common.config.Configuration;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.advancement.RedoxiationAdvancement;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class RedoxiationConfigHandler {
	private static Configuration config = Redoxiation.config;
	public static final String CATEGORY_OREGEN = "oregen";
	public static final String CATEGORY_BLOCKS = "blocks";
	public static final String CATEGORY_ITEMS = "items";
	public static final String CATEGORY_TOOLS = "tools";
	public static String comment = "";
	public static void initConfig(){
		RedoxiationGenHandler oregen = new RedoxiationGenHandler();
		RedoxiationGenericItems items = null;
		config.load();
    	Redoxiation.dummybool = config.get(config.CATEGORY_GENERAL, "Dummy", true).getBoolean();
    	Redoxiation.oredif = config.getInt("OreDifficulty", config.CATEGORY_GENERAL, 1, 0, 2, "1 to 2");
    	RedoxiationAdvancement.isachivenable = config.getBoolean("Achievement Enable", config.CATEGORY_GENERAL, true, "Achivevement Option");
    	// Enable / Disable
    	
		// Blocks Start
    	
    	// Ores Start
    	RedoxiationBlocks.oreCopper_cfg = config.get(CATEGORY_BLOCKS, "oreCopper", true).getBoolean();
    	RedoxiationBlocks.oreTin_cfg = config.get(CATEGORY_BLOCKS, "oreTin", true).getBoolean();
    	RedoxiationBlocks.oreLead_cfg = config.get(CATEGORY_BLOCKS, "oreLead", true).getBoolean();
    	RedoxiationBlocks.oreSilver_cfg = config.get(CATEGORY_BLOCKS, "oreSilver", true).getBoolean();
    	RedoxiationBlocks.oreNickel_cfg = config.get(CATEGORY_BLOCKS, "oreNickel", true).getBoolean();
    	RedoxiationBlocks.orePlatinum_cfg = config.get(CATEGORY_BLOCKS, "orePlatinum", true).getBoolean();
    	RedoxiationBlocks.oreZinc_cfg = config.get(CATEGORY_BLOCKS, "oreZinc", true).getBoolean();
    	RedoxiationBlocks.oreCobalt_cfg = config.get(CATEGORY_BLOCKS, "CoblatOre", true).getBoolean();
    	RedoxiationBlocks.oreChromium_cfg = config.get(CATEGORY_BLOCKS, "oreChromium", true).getBoolean();
    	RedoxiationBlocks.pitchblend_cfg = config.get(CATEGORY_BLOCKS, "pitchblend", true).getBoolean();
    	RedoxiationBlocks.rutile_cfg = config.get(CATEGORY_BLOCKS, "rutile", true).getBoolean();
    	RedoxiationBlocks.scheelite_cfg = config.get(CATEGORY_BLOCKS, "scheelite", true).getBoolean();
    	RedoxiationBlocks.bauxite_cfg = config.get(CATEGORY_BLOCKS, "bauxite", true).getBoolean();
    	RedoxiationBlocks.oreSulfur_cfg = config.get(CATEGORY_BLOCKS, "oreSulfur", true).getBoolean();
    	RedoxiationBlocks.limestone_cfg = config.get(CATEGORY_BLOCKS, "limestone", true).getBoolean();
    	RedoxiationBlocks.cryolite_cfg = config.get(CATEGORY_BLOCKS, "cryolite", true).getBoolean();
		// Ores End
    	
		// Blocks End
    	
    	// Items Start
		//Gold
		items.crushedOreGold_enable = config.get(CATEGORY_ITEMS, "crushedOreGold", true).getBoolean();
		items.dustOreGold_enable = config.get(CATEGORY_ITEMS, "dustOreGold", true).getBoolean();

		//Iron
		items.nuggetIron_enable = config.get(CATEGORY_ITEMS, "nuggetIron", true).getBoolean();
		items.crushedOreIron_enable = config.get(CATEGORY_ITEMS, "crushedOreIron", true).getBoolean();
		items.dustOreIron_enable = config.get(CATEGORY_ITEMS, "dustOreIron", true).getBoolean();
		
		//Aluminum
		items.nuggetAluminum_enable = config.get(CATEGORY_ITEMS, "nuggetAluminum", true).getBoolean();
		items.dustBauxite_enable = config.get(CATEGORY_ITEMS, "dustBauxite", true).getBoolean();
		items.ingotAluminum_enable = config.get(CATEGORY_ITEMS, "ingotAluminum", true).getBoolean();
		items.RawBauxite_enable = config.get(CATEGORY_ITEMS, "RawBauxite", true).getBoolean();
		
		//Chromium
		items.nuggetChromium_enable = config.get(CATEGORY_ITEMS, "nuggetChromium", true).getBoolean();
		items.crushedOreChromium_enable = config.get(CATEGORY_ITEMS, "crushedOreChromium", true).getBoolean();
		items.dustOreChromium_enable = config.get(CATEGORY_ITEMS, "dustOreChromium", true).getBoolean();
		items.ingotChromium_enable = config.get(CATEGORY_ITEMS, "ingotChroumium", true).getBoolean();
		
		//Cobalt
		items.dustOreCobalt_enable = config.get(CATEGORY_ITEMS, "dustOreCoblat", true).getBoolean();
		items.ingotCobalt_enable = config.get(CATEGORY_ITEMS, "ingotCobalt", true).getBoolean();
		
		//Copper
		items.nuggetCopper_enable = config.get(CATEGORY_ITEMS, "nuggetCopper", true).getBoolean();
		items.crushedOreCopper_enable = config.get(CATEGORY_ITEMS, "crushedOreCopper", true).getBoolean();
		items.dustOreCopper_enable = config.get(CATEGORY_ITEMS, "dustOreCopper", true).getBoolean();
		items.ingotCopper_enable = config.get(CATEGORY_ITEMS, "ingotCopper", true).getBoolean();
		
		//Lead
		items.nuggetLead_enable = config.get(CATEGORY_ITEMS, "nuggetLead", true).getBoolean();
		items.crushedOreLead_enable = config.get(CATEGORY_ITEMS, "crushedOreLead", true).getBoolean();
		items.dustOreLead_enable = config.get(CATEGORY_ITEMS, "dustOreLead", true).getBoolean();
		items.ingotLead_enable = config.get(CATEGORY_ITEMS, "ingotLead", true).getBoolean();
		
		//Nickel
		items.nuggetNickel_enable = config.get(CATEGORY_ITEMS, "nuggetNickel", true).getBoolean();
		items.crushedOreNickel_enable = config.get(CATEGORY_ITEMS, "crushedOreNickel", true).getBoolean();
		items.dustOreNickel_enable = config.get(CATEGORY_ITEMS, "dustOreNickel", true).getBoolean();
		items.ingotNickel_enable = config.get(CATEGORY_ITEMS, "ingotNickel", true).getBoolean();
		
		//Platinum
		items.nuggetPlatinum_enable = config.get(CATEGORY_ITEMS, "nuggetPlatinum", true).getBoolean();
		items.crushedOrePlatinum_enable = config.get(CATEGORY_ITEMS, "crushedOrePlatinum", true).getBoolean();
		items.dustOrePlatinum_enable = config.get(CATEGORY_ITEMS, "dustOrePlatinum", true).getBoolean();
		items.ingotPlatinum_enable = config.get(CATEGORY_ITEMS, "ingotPlatinum", true).getBoolean();

		//Salt
		items.SaltChunk_enable = config.get(CATEGORY_ITEMS, "SaltChunk", true).getBoolean();
		items.dustSalt_enable = config.get(CATEGORY_ITEMS, "dustSalt", true).getBoolean();
		
		//Sulfur
		items.SulfurChunk_enable = config.get(CATEGORY_ITEMS, "SulfurChunk", true).getBoolean();
		items.dustSulfur_enable = config.get(CATEGORY_ITEMS, "dustSulfur", true).getBoolean();
		
		//Silver
		items.nuggetSilver_enable = config.get(CATEGORY_ITEMS, "nuggetSilver", true).getBoolean();
		items.dustOreSilver_enable = config.get(CATEGORY_ITEMS, "dustOreSilver", true).getBoolean();
		items.crushedOreSilver_enable = config.get(CATEGORY_ITEMS, "crushedOreSilver", true).getBoolean();
		items.ingotSilver_enable = config.get(CATEGORY_ITEMS, "ingotSilver", true).getBoolean();
		
		//Steel
		items.nuggetSteel_enable = config.get(CATEGORY_ITEMS, "nuggetSteel", true).getBoolean();
		items.ingotSteel_enable = config .get(CATEGORY_ITEMS, "ingotSteel", true).getBoolean();
		
		//Tin
		items.nuggetTin_enable = config.get(CATEGORY_ITEMS, "nuggetTin", true) .getBoolean();
		items.crushedOreTin_enable = config.get(CATEGORY_ITEMS, "crushedOreTin", true).getBoolean();
		items.dustOreTin_enable = config.get(CATEGORY_ITEMS, "dustOreTin", true) .getBoolean();
		items.ingotTin_enable = config.get(CATEGORY_ITEMS, "ingotTin", true) .getBoolean();
		
		//Titanate
		items.nuggetTitanium_enable = config.get(CATEGORY_ITEMS, "nuggetTitanium", true).getBoolean();
		items.dustRutile_enable = config.get(CATEGORY_ITEMS, "dustRutile", true).getBoolean();
		items.ingotTitanium_enable = config.get(CATEGORY_ITEMS, "ingotTitanium", true).getBoolean();
		items.RawRutile_enable = config.get(CATEGORY_ITEMS, "RawRutile", true).getBoolean();
		
		//Tungstate
		items.nuggetTungsten_enable = config.get(CATEGORY_ITEMS, "nuggetTungsten", true).getBoolean();
		items.dustScheelite_enable = config.get(CATEGORY_ITEMS, "dustScheelite", true).getBoolean();
		items.ingotTungsten_enable = config.get(CATEGORY_ITEMS, "ingotTungsten", true).getBoolean();
		items.RawScheelite_enable = config.get(CATEGORY_ITEMS, "RawScheelite", true).getBoolean();
		
		//Zinc
		items.nuggetZinc_enable = config.get(CATEGORY_ITEMS, "nuggetZinc", true).getBoolean();
		items.crushedOreZinc_enable = config.get(CATEGORY_ITEMS, "crushedOreZinc", true).getBoolean();
		items.dustOreZinc_enable = config.get(CATEGORY_ITEMS, "dustOreZinc", true).getBoolean();
		items.ingotZinc_enable = config.get(CATEGORY_ITEMS, "ingotZinc", true).getBoolean();
		
		
		//Bucket
		items.itemHotAirBucket_enable = config.get(CATEGORY_ITEMS, "HotAirBucket", true).getBoolean();
		items.itemMoltenPigironBucket_enable = config.get(CATEGORY_ITEMS, "MoltenPigironBucket", true).getBoolean();
		items.itemSlagBucket_enable = config.get(CATEGORY_ITEMS, "SlagBucket", true).getBoolean();
		
		//ETC
		//Item
		items.Calcite_enable = config.get(CATEGORY_ITEMS, "Calcite", true).getBoolean();
		items.ItemCryolite_enable = config.get(CATEGORY_ITEMS, "ItemCryolite", true).getBoolean();
		items.DolomitePowder_enable = config.get(CATEGORY_ITEMS, "DolomitePowder", true).getBoolean();
		items.DolomiteShard_enable = config.get(CATEGORY_ITEMS, "DolomiteShard", true).getBoolean();
		
		//OOO ingot
		items.ingotPlutonium_enable = config.get(CATEGORY_ITEMS, "ingotPlotonium", true).getBoolean();
		items.ingotUranium_enable = config.get(CATEGORY_ITEMS, "ingotUranium", true).getBoolean();
		
		//crashed OOO
		items.crashedNetherrack_enable = config.get(CATEGORY_ITEMS, "crashedNetherrack", true).getBoolean();
		items.crashedObsidian_enable = config.get(CATEGORY_ITEMS, "crashedObsidian", true).getBoolean();

  
    	//oregen
    	//Nether gen control Start
    	oregen.ferroNickelOregen = config.getBoolean("oreFerroNickelgen", CATEGORY_OREGEN, true, "Ferronickel Oregen Configuration [Default : true]");
    	oregen.pseudoBronzeOregen = config.getBoolean("orePseudoBronzegen", CATEGORY_OREGEN, true, "PseudoBronze Oregen Configuration [Default : true]");
    	oregen.pseudoBrassOregen = config.getBoolean("orePseudoBrassOregen", CATEGORY_OREGEN, true, "PseudoBrass Oregen Configuration [Default : true]");
    	oregen.argentAurumOregen = config.getBoolean("argentAurumOregen", CATEGORY_OREGEN, true, "argentAurum Oregen Configuration [Default : true");
    	oregen.pseudoSolderOregen = config.getBoolean("pseudoSolderOregen", CATEGORY_OREGEN, true, "pseudoSolder Oregen Configuration [Default : true]");
    	oregen.pseudoStelliteOregen = config.getBoolean("pseudoStelliteOregen", CATEGORY_OREGEN, true, "pseudoStellite Oregen Configuration [Default : true]");
    	oregen.TNTiumOregen = config.getBoolean("TNTiumOregen", CATEGORY_OREGEN, true, "TNTium Oregen Configuration [Default : true]");
    	//End
    	//Overworld gen control Start
    	
    	config.save();
    	Redoxiation.logger.info("Config Loading Complete. Starting Item Registry");
	}
}
