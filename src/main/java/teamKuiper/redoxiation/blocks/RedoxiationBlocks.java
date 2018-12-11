package teamKuiper.redoxiation.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileBlastFurnaceBlock;
import teamKuiper.redoxiation.blocks.tileentity.TileCog;
import teamKuiper.redoxiation.blocks.tileentity.TileIronCog;
import teamKuiper.redoxiation.blocks.tileentity.TileStoneCog;
import teamKuiper.redoxiation.blocks.tileentity.TileWoodenCog;
import teamKuiper.redoxiation.blocks.tileentity.TileTankLead;
import teamKuiper.redoxiation.blocks.tileentity.TilePipeCopper;
import teamKuiper.redoxiation.blocks.tileentity.TileWire;

public class RedoxiationBlocks {
	
    public static final PropertyEnum<EnumFacing> FACING = PropertyEnum.create("facing", EnumFacing.class);

	public static Block oreCopper, oreTin, oreLead, oreSilver, oreNickel,
			orePlatinum, oreZinc, oreCobalt, oreChromium, pitchblend,
			limestone, dolomite, saltRock, bauxite, rutile, scheelite, cryolite,
			oreSulfur, oreFerroNickel, orePseudoBronze, orePseudoBrassOre,
			argentAurum, pseudoSolder, pseudoStellite, tntium, obsidianIron,
			obsidianGold, obsidianCopper, obsidianTin, obsidianLead,
			obsidianSilver, obsidianNickel, obsidianPlatinum, obsidianZinc,
			obsidianCobalt, obsidianChromium, obsidianUranium,
			obsidianPlutonium, woodenCog, stoneCog, ironCog, wire, tankLead, cog, pipeCopper;
	
	public static Fluid hotAir, moltenPigiron, slag;
	public static BlockFluidClassic hotAirBlock, moltenPigironBlock, slagBlock;
	public static Block blastFurnaceBlock, converterBlock;
	
	public static boolean oreCopperCfg, oreTinCfg, oreLeadCfg,
			oreSilverCfg, oreNickelCfg, orePlatinumCfg, oreZincCfg,
			oreCobaltCfg, oreChromiumCfg, pitchblendCfg, rutileCfg,
			scheeliteCfg, bauxiteCfg, limestoneCfg, cryoliteCfg, oreSulfurCfg;
	
	public static Block[] blocks = {oreCopper, oreTin, oreLead, oreSilver, oreNickel, orePlatinum, oreZinc,
			oreCobalt, oreChromium, pitchblend, limestone, dolomite, saltRock, bauxite, rutile, scheelite, cryolite,
			oreSulfur, oreFerroNickel, orePseudoBronze, orePseudoBrassOre, argentAurum, pseudoSolder,
			pseudoStellite, tntium, obsidianIron, obsidianGold, obsidianCopper, obsidianTin, obsidianLead,
			obsidianSilver, obsidianNickel, obsidianPlatinum, obsidianZinc, obsidianCobalt, obsidianChromium,
			obsidianUranium, obsidianPlutonium, woodenCog, stoneCog, ironCog, wire, cog, blastFurnaceBlock,
			tankLead, pipeCopper, hotAirBlock, moltenPigironBlock, slagBlock};

	public static void initBlocks() {
		// Blocks
		// Normal Ore Name, Harvest level, Hardness, Resistance
		oreCopper = new RedoxiationOre("oreCopper", 1, 2.5F, 5.0F);
		oreTin = new RedoxiationOre("oreTin", 1, 2.5F, 5.0F);
		oreLead = new RedoxiationOre("oreLead", 1, 2.5F, 5.0F);
		oreSilver = new RedoxiationOre("oreSilver", 2, 2.5F, 5.0F);
		oreNickel = new RedoxiationOre("oreNickel", 1, 2.5F, 5.0F);
		orePlatinum = new RedoxiationOre("orePlatinum", 2, 2.5F, 5.0F);
		oreZinc = new RedoxiationOre("oreZinc", 1, 2.5F, 5.0F);
		oreCobalt = new RedoxiationOre("oreCobalt", 1, 2.5F, 5.0F);
		oreChromium = new RedoxiationOre("oreChromium", 1, 2.5F, 5.0F);

		pitchblend = new RedoxiationOre("pitchblend", 2, 2.5F, 5.0F);
		limestone = new Limestone();
		dolomite = new Dolomite();
		saltRock = new SaltRock();
		bauxite = new Bauxite();
		rutile = new Rutile();
		scheelite = new Scheelite();
		cryolite = new Cryolite();
		oreSulfur = new OreSulfur();

		// Nether Ore
		oreFerroNickel = new RedoxiationOre("oreFerroNickel", 2, 3.0F, 15.0F);
		orePseudoBronze = new RedoxiationOre("orePseudoBronze", 2, 3.0F, 15.0F);
		orePseudoBrassOre = new RedoxiationOre("orePseudoBrassOre", 2, 3.0F, 15.0F);

		argentAurum = new RedoxiationOre("argentAurum", 2, 3.0F, 15.0F);
		pseudoSolder = new RedoxiationOre("pseudoSolder", 2, 3.0F, 15.0F);
		pseudoStellite = new RedoxiationOre("pseudoStellite", 2, 3.0F, 15.0F);
		tntium = new TNTium();

		// Obsidian Ore
		obsidianIron = new RedoxiationOre("obsidianIron", 3, 50.0F, 2000.0F);
		obsidianGold = new RedoxiationOre("obsidianGold", 3, 50.0F, 2000.0F);
		obsidianCopper = new RedoxiationOre("obsidianCopper", 3, 50.0F, 2000.0F);
		obsidianTin = new RedoxiationOre("obsidianTin", 3, 50.0F, 2000.0F);
		obsidianLead = new RedoxiationOre("obsidianLead", 3, 50.0F, 2000.0F);
		obsidianSilver = new RedoxiationOre("obsidianSilver", 3, 50.0F, 2000.0F);
		obsidianNickel = new RedoxiationOre("obsidianNickel", 3, 50.0F, 2000.0F);
		obsidianPlatinum = new RedoxiationOre("obsidianPlatinum", 3, 50.0F, 2000.0F);
		obsidianZinc = new RedoxiationOre("obsidianZinc", 3, 50.0F, 2000.0F);
		obsidianCobalt = new RedoxiationOre("obsidianCobalt", 3, 50.0F, 2000.0F);
		obsidianChromium = new RedoxiationOre("obsidianChromium", 3, 50.0F, 2000.0F);
		obsidianUranium = new RedoxiationOre("obsidianUranium", 3, 50.0F, 2000.0F);
		obsidianPlutonium = new RedoxiationOre("obsidianPlutonium", 3, 50.0F, 2000.0F);

		// Cog
		woodenCog = new BlockWoodenCog();
		stoneCog = new BlockStoneCog();
		ironCog = new BlockIronCog();
        wire = new Wire();
        cog = new Cog();

		// Machine
		blastFurnaceBlock = new BlastFurnaceBlock();
		converterBlock = new ConverterBlock();
        tankLead = new TankLead();
        pipeCopper = new BlockPipeCopper();
        
		// Fluids
		hotAir = new Fluid("hotAir", new ResourceLocation(Redoxiation.MODID, "fluids/hotair_still"), new ResourceLocation(Redoxiation.MODID, "fluids/hotair_flow")).setLuminosity(0).setDensity(-10).setTemperature(1473).setViscosity(2000).setGaseous(true);
		moltenPigiron = new Fluid("moltenPigiron", new ResourceLocation(Redoxiation.MODID, "fluids/moltenpigiron_still"), new ResourceLocation(Redoxiation.MODID, "fluids/moltenpigiron_flow")).setLuminosity(15).setDensity(7874).setTemperature(1900).setViscosity(2000).setGaseous(false);
		slag = new Fluid("slag", new ResourceLocation(Redoxiation.MODID, "fluids/slag_still"), new ResourceLocation(Redoxiation.MODID, "fluids/slag_flow")).setLuminosity(15).setDensity(7874).setTemperature(1900).setViscosity(2000).setGaseous(false);

	}
	
	public static void setOreDictionary() {
		OreDictionary.registerOre("oreCopper", oreCopper);
		OreDictionary.registerOre("oreTin", oreTin);
		OreDictionary.registerOre("oreLead", oreLead);
		OreDictionary.registerOre("oreSilver", oreSilver);
		OreDictionary.registerOre("oreNickel", oreNickel);
		OreDictionary.registerOre("orePlatinum", orePlatinum);
		OreDictionary.registerOre("oreZinc", oreZinc);
		OreDictionary.registerOre("oreCobalt", oreCobalt);
		OreDictionary.registerOre("oreChromium", oreChromium);
		OreDictionary.registerOre("orepitchblend", pitchblend);
		OreDictionary.registerOre("blocklimestone", limestone);
		OreDictionary.registerOre("blocksaltRock", saltRock);
		OreDictionary.registerOre("blockbauxite", bauxite);
		OreDictionary.registerOre("blockrutile", rutile);
		OreDictionary.registerOre("blockscheelite", scheelite);
		OreDictionary.registerOre("blockcryolite", cryolite);
		OreDictionary.registerOre("oreSulfur", oreSulfur);
		OreDictionary.registerOre("blockFerronickel", oreFerroNickel);
		OreDictionary.registerOre("blockPseudoBronze", orePseudoBronze);
		OreDictionary.registerOre("blockPseudoBrass", orePseudoBrassOre);
		OreDictionary.registerOre("blockargentAurum", argentAurum);
		OreDictionary.registerOre("blockpseudoSolder", pseudoSolder);
		OreDictionary.registerOre("blockpseudoStellite", pseudoStellite);
		OreDictionary.registerOre("blockTNTium", tntium);
		OreDictionary.registerOre("oreobsidianIron", obsidianIron);
		OreDictionary.registerOre("oreobsidianGold", obsidianGold);
		OreDictionary.registerOre("oreobsidianCopper", obsidianCopper);
		OreDictionary.registerOre("oreobsidianTin", obsidianTin);
		OreDictionary.registerOre("oreobsidianLead", obsidianLead);
		OreDictionary.registerOre("oreobsidianSilver", obsidianSilver);
		OreDictionary.registerOre("oreobsidianNickel", obsidianNickel);
		OreDictionary.registerOre("oreobsidianPlatinum", obsidianPlatinum);
		OreDictionary.registerOre("oreobsidianZinc", obsidianZinc);
		OreDictionary.registerOre("oreobsidianCobalt", obsidianCobalt);
		OreDictionary.registerOre("oreobsidianChromium", obsidianChromium);
		OreDictionary.registerOre("oreobsidianUranium", obsidianUranium);
		OreDictionary.registerOre("oreobsidianPlutonium", obsidianPlutonium);
	}
	
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		// Block Fluids
		FluidRegistry.registerFluid(hotAir);
		hotAirBlock = (BlockFluidClassic) new BlockHotAir(hotAir, Material.LAVA).setRegistryName(Redoxiation.MODID, "hotAir");
		hotAir.setUnlocalizedName(hotAirBlock.getUnlocalizedName());
		
		FluidRegistry.registerFluid(moltenPigiron);
		moltenPigironBlock = (BlockFluidClassic) new BlockMoltenPigiron(moltenPigiron, Material.LAVA).setRegistryName(Redoxiation.MODID, "moltenPigiron");
		moltenPigiron.setUnlocalizedName(moltenPigironBlock.getUnlocalizedName());
		
		FluidRegistry.registerFluid(slag);
		slagBlock = (BlockFluidClassic) new BlockSlag(slag, Material.LAVA).setRegistryName(Redoxiation.MODID, "slag");
		slag.setUnlocalizedName(slagBlock.getUnlocalizedName());
		
		// Register
		event.getRegistry().registerAll(blocks);
		//GameRegistry.registerBlock(converterBlock, "ConverterBlock");

		// Tile Entities
        GameRegistry.registerTileEntity(TileWire.class, wire.getRegistryName());
        GameRegistry.registerTileEntity(TileCog.class, cog.getRegistryName());
		GameRegistry.registerTileEntity(TileWoodenCog.class, woodenCog.getRegistryName());
		GameRegistry.registerTileEntity(TileStoneCog.class, stoneCog.getRegistryName());
		GameRegistry.registerTileEntity(TileIronCog.class, ironCog.getRegistryName());
		GameRegistry.registerTileEntity(TileBlastFurnaceBlock.class, blastFurnaceBlock.getRegistryName());
        GameRegistry.registerTileEntity(TileTankLead.class, tankLead.getRegistryName());
        GameRegistry.registerTileEntity(TilePipeCopper.class, pipeCopper.getRegistryName());
		
		Redoxiation.logger.info("Block Registry Complete. Starting Item Registry");
	}
	
	public static void registerBlockItems(RegistryEvent.Register<Item> event) {
		for(Block block : blocks) {
			event.getRegistry().registerAll(Item.getItemFromBlock(block));
		}
	}

	public static void registerRenders(ModelRegistryEvent event) {
		for(Block block : blocks) {
			registerRender(Item.getItemFromBlock(block));
		}
	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
