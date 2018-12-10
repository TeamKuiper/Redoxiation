package teamKuiper.redoxiation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.utils.WorldGenDeterminer;

public class RedoxiationGenHandler implements IWorldGenerator
{

	public static boolean ferroNickelOregen, pseudoBronzeOregen, pseudoBrassOregen, argentAurumOregen, pseudoSolderOregen, pseudoStelliteOregen, TNTiumOregen;

	WorldGenDeterminer netherRackDeterminer = new WorldGenDeterminer(Blocks.NETHERRACK);
	WorldGenDeterminer obsidianDeterminer = new WorldGenDeterminer(Blocks.OBSIDIAN);
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{

		switch(world.provider.getDimension())
		{
		
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
			
		}
		
	}

	private void generateNether(World world, Random random, int x, int z) 
	{
		if(ferroNickelOregen){
			(new WorldGenMinable(RedoxiationBlocks.oreFerroNickel.getDefaultState(), 15, netherRackDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
			addOreSpawn(RedoxiationBlocks.oreFerroNickel, 0, Blocks.NETHERRACK, world, random, x, z, 16, 16, 1 + random.nextInt(7), 60, 1, 255);
		}
		
		if(pseudoBronzeOregen){
			(new WorldGenMinable(RedoxiationBlocks.orePseudoBronze.getDefaultState(), 15, netherRackDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
			addOreSpawn(RedoxiationBlocks.orePseudoBronze, 0, Blocks.NETHERRACK, world, random, x, z, 16, 16, 1 + random.nextInt(7), 60, 1, 255);
		}
		
		if(pseudoBrassOregen){
			(new WorldGenMinable(RedoxiationBlocks.orePseudoBrassOre.getDefaultState(), 15, netherRackDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
			addOreSpawn(RedoxiationBlocks.orePseudoBrassOre, 0, Blocks.NETHERRACK, world, random, x, z, 16, 16, 1 + random.nextInt(7), 60, 1, 255);
		}
		
		if(argentAurumOregen){
			(new WorldGenMinable(RedoxiationBlocks.argentAurum.getDefaultState(), 15, netherRackDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
			addOreSpawn(RedoxiationBlocks.argentAurum, 0, Blocks.NETHERRACK, world, random, x, z, 16, 16, 1 + random.nextInt(7),60, 1, 255);
		}
		
		if(pseudoSolderOregen){
			(new WorldGenMinable(RedoxiationBlocks.pseudoSolder.getDefaultState(), 15, netherRackDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
			addOreSpawn(RedoxiationBlocks.pseudoSolder, 0, Blocks.NETHERRACK, world, random, x, z, 16, 16, 1 + random.nextInt(7), 60, 1, 255);
		}
		
		if(pseudoStelliteOregen){
			(new WorldGenMinable(RedoxiationBlocks.pseudoStellite.getDefaultState(), 15, netherRackDeterminer)).generate(world, random, new BlockPos(new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16))));
			addOreSpawn(RedoxiationBlocks.pseudoStellite, 0, Blocks.NETHERRACK, world, random, x, z, 16, 16, 1 + random.nextInt(7), 60, 1, 255);
		}
		
		if(TNTiumOregen){
			(new WorldGenMinable(RedoxiationBlocks.tntium.getDefaultState(), 15, netherRackDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
			addOreSpawn(RedoxiationBlocks.tntium, 0, Blocks.NETHERRACK, world, random, x, z, 16, 16, 1, 30, 1, 255);
		}
	}

	private void generateSurface(World world, Random random, int x, int z)
	{
		addOreSpawn(RedoxiationBlocks.oreCopper, 0, Blocks.STONE, world, random, x, z, 16, 16, 3 + random.nextInt(4), 12, 30, 70);
		addOreSpawn(RedoxiationBlocks.oreTin, 0, Blocks.STONE, world, random, x, z, 16, 16, 3 + random.nextInt(4), 12, 10, 60);
		addOreSpawn(RedoxiationBlocks.oreLead, 0, Blocks.STONE, world, random, x, z, 16, 16, 3 + random.nextInt(4), 12, 10, 60);
		addOreSpawn(RedoxiationBlocks.oreSilver, 0, Blocks.STONE, world, random, x, z, 16, 16, 2 + random.nextInt(3), 12, 7, 50);
		addOreSpawn(RedoxiationBlocks.orePlatinum, 0, Blocks.STONE, world, random, x, z, 16, 16, 1 + random.nextInt(1), 5, 5, 37);
		addOreSpawn(RedoxiationBlocks.oreNickel, 0, Blocks.STONE, world, random, x, z, 16, 16, 2 + random.nextInt(2), 10, 12, 46);
		addOreSpawn(RedoxiationBlocks.oreCobalt, 0, Blocks.STONE, world, random, x, z, 16, 16, 2 + random.nextInt(4), 10, 5, 52);
		addOreSpawn(RedoxiationBlocks.oreChromium, 0, Blocks.STONE, world, random, x, z, 16, 16, 2 + random.nextInt(4), 10, 5, 52);
		addOreSpawn(RedoxiationBlocks.oreZinc, 0, Blocks.STONE, world, random, x, z, 16, 16, 2 + random.nextInt(4), 10, 15, 60);
		addOreSpawn(RedoxiationBlocks.pitchblend, 0, Blocks.STONE, world, random, x, z, 16, 16, 1 + random.nextInt(2), 5, 3, 40);
		addOreSpawn(RedoxiationBlocks.limestone, 0, Blocks.STONE, world, random, x, z, 16, 16, 16 + random.nextInt(10), 16, 30, 125);
		addOreSpawn(RedoxiationBlocks.dolomite, 0, Blocks.STONE, world, random, x, z, 16, 16, 1 + random.nextInt(10), 16, 30, 125);
		addOreSpawn(RedoxiationBlocks.saltRock, 0, Blocks.STONE, world, random, x, z, 16, 16, 1 + random.nextInt(2), 12, 30, 125);
		addOreSpawn(RedoxiationBlocks.saltRock, 0, Blocks.DIRT, world, random, x, z, 16, 16, 1 + random.nextInt(2), 12, 30, 125);
		addOreSpawn(RedoxiationBlocks.saltRock, 0, Blocks.WATER, world, random, x, z, 16, 16, 1 + random.nextInt(2), 12, 30, 125);
		addOreSpawn(RedoxiationBlocks.bauxite, 0, Blocks.STONE, world, random, x, z, 16, 16, 15 + random.nextInt(10), 16, 30, 100);
		addOreSpawn(RedoxiationBlocks.rutile, 0, Blocks.STONE, world, random, x, z, 16, 16, 13 + random.nextInt(9), 16, 20, 90);
		addOreSpawn(RedoxiationBlocks.scheelite, 0, Blocks.STONE, world, random, x, z, 16, 16, 10 + random.nextInt(8), 16, 10, 80);
		addOreSpawn(RedoxiationBlocks.cryolite, 0, Blocks.STONE, world, random, x, z, 16, 16, 10 + random.nextInt(5), 16, 30, 70);
		addOreSpawn(RedoxiationBlocks.oreSulfur, 0, Blocks.LAVA, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 3, 125);
	}


	private void generateEnd(World world, Random random, int x, int z) 
	{
		(new WorldGenMinable(RedoxiationBlocks.obsidianIron.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianGold.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianCopper.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianTin.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianLead.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianSilver.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianPlatinum.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianNickel.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianZinc.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianCobalt.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianChromium.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianUranium.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));
		(new WorldGenMinable(RedoxiationBlocks.obsidianPlutonium.getDefaultState(), 15, obsidianDeterminer)).generate(world, random, new BlockPos(x + random.nextInt(16), 10 + random.nextInt(128), z + random.nextInt(16)));

		addOreSpawn(RedoxiationBlocks.obsidianIron, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 20, 100);
		addOreSpawn(RedoxiationBlocks.obsidianGold, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianCopper, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianTin, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianLead, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianSilver, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianPlatinum, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(3), 5, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianNickel, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianZinc, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianCobalt, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianChromium, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(5), 16, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianUranium, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(2), 3, 1, 100);
		addOreSpawn(RedoxiationBlocks.obsidianPlutonium, 0, Blocks.OBSIDIAN, world, random, x, z, 16, 16, 1 + random.nextInt(2), 3, 1, 100);
		
	}
	
	
	private void addOreSpawn(Block block, int metadata, Block target, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		
		assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
		
		assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		
		assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
		
		assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		
		assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";
	 
		int diffBtwnMinMaxY = maxY - minY;
		
		for(int x = 0; x < chancesToSpawn; x++)
		{
			
		int posX = blockXPos + random.nextInt(maxX);
		int posY = minY + random.nextInt(diffBtwnMinMaxY);
		int posZ = blockZPos + random.nextInt(maxZ);
		
		(new WorldGenMinable(block.getDefaultState(), maxVeinSize)).generate(world, random, new BlockPos(posX, posY, posZ));
		
		}
		
	}
	
}
