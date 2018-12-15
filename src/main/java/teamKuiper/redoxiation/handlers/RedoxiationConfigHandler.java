package teamKuiper.redoxiation.handlers;

import net.minecraftforge.common.config.Configuration;
import teamKuiper.redoxiation.Redoxiation;

public class RedoxiationConfigHandler {
	private static Configuration config = Redoxiation.config;
	public static final String CATEGORY_OREGEN = "oregen";
	public static final String CATEGORY_BLOCKS = "blocks";
	public static final String CATEGORY_ITEMS = "items";
	public static final String CATEGORY_TOOLS = "tools";
	public static String comment = "";
	public static void initConfig(){
		config.load();
    	Redoxiation.dummybool = config.get(Configuration.CATEGORY_GENERAL, "Dummy", true).getBoolean();
    	Redoxiation.oredif = config.getInt("OreDifficulty", Configuration.CATEGORY_GENERAL, 1, 0, 2, "1 to 2");
  
    	//oregen
    	//Nether gen control Start
    	RedoxiationGenHandler.ferroNickelOregen = config.getBoolean("oreFerroNickelgen", CATEGORY_OREGEN, true, "Ferronickel Oregen Configuration [Default : true]");
    	RedoxiationGenHandler.pseudoBronzeOregen = config.getBoolean("orePseudoBronzegen", CATEGORY_OREGEN, true, "PseudoBronze Oregen Configuration [Default : true]");
    	RedoxiationGenHandler.pseudoBrassOregen = config.getBoolean("orePseudoBrassOregen", CATEGORY_OREGEN, true, "PseudoBrass Oregen Configuration [Default : true]");
    	RedoxiationGenHandler.argentAurumOregen = config.getBoolean("argentAurumOregen", CATEGORY_OREGEN, true, "argentAurum Oregen Configuration [Default : true");
    	RedoxiationGenHandler.pseudoSolderOregen = config.getBoolean("pseudoSolderOregen", CATEGORY_OREGEN, true, "pseudoSolder Oregen Configuration [Default : true]");
    	RedoxiationGenHandler.pseudoStelliteOregen = config.getBoolean("pseudoStelliteOregen", CATEGORY_OREGEN, true, "pseudoStellite Oregen Configuration [Default : true]");
    	RedoxiationGenHandler.TNTiumOregen = config.getBoolean("TNTiumOregen", CATEGORY_OREGEN, true, "TNTium Oregen Configuration [Default : true]");
    	//End
    	//Overworld gen control Start
    	
    	config.save();
    	Redoxiation.logger.info("Config Loading Complete. Starting Item Registry");
	}
}
