package teamKuiper.redoxiation.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.tileentity.TileBlastFurnaceBlock;
import teamKuiper.redoxiation.blocks.tileentity.TileCog;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityIronCog;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityStoneCog;
import teamKuiper.redoxiation.blocks.tileentity.TileEntityWoodenCog;
import teamKuiper.redoxiation.blocks.tileentity.TileLeadTank;
import teamKuiper.redoxiation.blocks.tileentity.TilePipeCopper;
import teamKuiper.redoxiation.blocks.tileentity.TileWire;

public class RedoxiationBlocks {

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
	
	public static boolean oreCopper_cfg, oreTin_cfg, oreLead_cfg,
			oreSilver_cfg, oreNickel_cfg, orePlatinum_cfg, oreZinc_cfg,
			oreCobalt_cfg, oreChromium_cfg, pitchblend_cfg, rutile_cfg,
			scheelite_cfg, bauxite_cfg, limestone_cfg, cryolite_cfg, oreSulfur_cfg;

	public static void registerBlocks() {
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
		System.out.println(blastFurnaceBlock);
		converterBlock = new ConverterBlock();
        tankLead = new LeadTank();
        pipeCopper = new BlockPipeCopper();

		// Registry

		GameRegistry.registerBlock(oreCopper, "oreCopper");
		GameRegistry.registerBlock(oreTin, "oreTin");
		GameRegistry.registerBlock(oreLead, "oreLead");
		GameRegistry.registerBlock(oreSilver, "oreSilver");
		GameRegistry.registerBlock(oreNickel, "oreNickel");
		GameRegistry.registerBlock(orePlatinum, "orePlatinum");
		GameRegistry.registerBlock(oreZinc, "oreZinc");
		GameRegistry.registerBlock(oreCobalt, "oreCobalt");
		GameRegistry.registerBlock(oreChromium, "oreChromium");
		GameRegistry.registerBlock(pitchblend, "pitchblend");
		GameRegistry.registerBlock(limestone, "limestone");
		GameRegistry.registerBlock(dolomite, "dolomite");
		GameRegistry.registerBlock(saltRock, "saltRock");
		GameRegistry.registerBlock(bauxite, "bauxite");
		GameRegistry.registerBlock(rutile, "rutile");
		GameRegistry.registerBlock(scheelite, "scheelite");
		GameRegistry.registerBlock(cryolite, "cryolite");
		GameRegistry.registerBlock(oreSulfur, "oreSulfur");
		GameRegistry.registerBlock(oreFerroNickel, "oreFerroNickel");
		GameRegistry.registerBlock(orePseudoBronze, "orePseudoBronze");
		GameRegistry.registerBlock(orePseudoBrassOre, "orePseudoBrassOre");
		GameRegistry.registerBlock(argentAurum, "argentAurum");
		GameRegistry.registerBlock(pseudoSolder, "pseudoSolder");
		GameRegistry.registerBlock(pseudoStellite, "pseudoStellite");
		GameRegistry.registerBlock(tntium, "TNTium");
		GameRegistry.registerBlock(obsidianIron, "obsidianIron");
		GameRegistry.registerBlock(obsidianGold, "obsidianGold");
		GameRegistry.registerBlock(obsidianCopper, "obsidianCopper");
		GameRegistry.registerBlock(obsidianTin, "obsidianTin");
		GameRegistry.registerBlock(obsidianLead, "obsidianLead");
		GameRegistry.registerBlock(obsidianSilver, "obsidianSilver");
		GameRegistry.registerBlock(obsidianNickel, "obsidianNickel");
		GameRegistry.registerBlock(obsidianPlatinum, "obsidianPlatinum");
		GameRegistry.registerBlock(obsidianZinc, "obsidianZinc");
		GameRegistry.registerBlock(obsidianCobalt, "obsidianCobalt");
		GameRegistry.registerBlock(obsidianChromium, "obsidianChromium");
		GameRegistry.registerBlock(obsidianUranium, "obsidianUranium");
		GameRegistry.registerBlock(obsidianPlutonium, "obsidianPlutonium");
		GameRegistry.registerBlock(woodenCog, "WoodenCog");
		GameRegistry.registerBlock(stoneCog, "StoneCog");
		GameRegistry.registerBlock(ironCog, "IronCog");
        GameRegistry.registerBlock(wire, "wire");
        GameRegistry.registerBlock(cog, "cog");
		GameRegistry.registerBlock(blastFurnaceBlock, "BlastFurnaceBlock");
		//GameRegistry.registerBlock(converterBlock, "ConverterBlock");
        GameRegistry.registerBlock(tankLead, "tankLead");
		GameRegistry.registerBlock(pipeCopper, "pipeCopper");

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
		
		// Tile Entities
        GameRegistry.registerTileEntity(TileWire.class, Redoxiation.MODID + ".wire");
        GameRegistry.registerTileEntity(TileCog.class, Redoxiation.MODID + ".cog");
		GameRegistry.registerTileEntity(TileEntityWoodenCog.class, Redoxiation.MODID + ".WoodenCog");
		GameRegistry.registerTileEntity(TileEntityStoneCog.class, Redoxiation.MODID + ".StoneCog");
		GameRegistry.registerTileEntity(TileEntityIronCog.class, Redoxiation.MODID + ".IronCog");
		System.out.println(blastFurnaceBlock);
		GameRegistry.registerTileEntity(TileBlastFurnaceBlock.class, Redoxiation.MODID + ".blastFurnaceBlock");
		System.out.println(blastFurnaceBlock);
        GameRegistry.registerTileEntity(TileLeadTank.class, Redoxiation.MODID + ".tankLead");
        GameRegistry.registerTileEntity(TilePipeCopper.class, Redoxiation.MODID + ".pipeCopper");
        
		

		// Fluids
		hotAir = new Fluid("HotAir").setLuminosity(0).setDensity(-10).setTemperature(1473).setViscosity(2000).setGaseous(true);
		moltenPigiron = new Fluid("MoltenPigiron").setLuminosity(15).setDensity(7874).setTemperature(1900).setViscosity(2000).setGaseous(false);
		slag = new Fluid("Slag").setLuminosity(15).setDensity(7874).setTemperature(1900).setViscosity(2000).setGaseous(false);

		// Fluid Registry

		// Block Fluids
		//Hot Air
		FluidRegistry.registerFluid(hotAir);
		hotAirBlock = (BlockFluidClassic) new BlockHotAir(hotAir, Material.lava).setBlockName("HotAir");
		GameRegistry.registerBlock(hotAirBlock, "FluidHotAir");
		hotAir.setUnlocalizedName(hotAirBlock.getUnlocalizedName());
		
		//MoltenPigIron
		FluidRegistry.registerFluid(moltenPigiron);
		moltenPigironBlock = (BlockFluidClassic) new BlockMoltenPigiron(moltenPigiron, Material.lava).setBlockName("MoltenPigiron");
		GameRegistry.registerBlock(moltenPigironBlock, "FluidMoltenPigiron");
		moltenPigiron.setUnlocalizedName(moltenPigironBlock.getUnlocalizedName());
		
		//Slag
		FluidRegistry.registerFluid(slag);
		slagBlock = (BlockFluidClassic) new BlockSlag(slag, Material.lava).setBlockName("Slag");
		GameRegistry.registerBlock(slagBlock, "FluidSlag");
		slag.setUnlocalizedName(slagBlock.getUnlocalizedName());
		// Fluids End
		
		Redoxiation.logger.info("Block Registry Complete. Starting Item Registry");
	}
}
