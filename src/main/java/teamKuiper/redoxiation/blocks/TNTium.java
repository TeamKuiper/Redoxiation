package teamKuiper.redoxiation.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;

public class TNTium extends Block {

	public TNTium() {
		super(Material.ROCK);
		setRegistryName(Redoxiation.MODID, "tntium");
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHarvestLevel("pickaxe", 2);
		setHardness(3.0F);
		setResistance(15.0F);

	}

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z,
			int meta) {
		Random random = new Random();
		if(!Minecraft.getMinecraft().player.capabilities.isCreativeMode) {
			if(!world.isRemote){
				if (random.nextInt(4) >= 3) {
					world.createExplosion(null, x, y, z, 2, true);
				}
			}
		}

	}

	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Items.GUNPOWDER;
	}

	public int quantityDropped(Random rand) {
		return 1 + rand.nextInt(2);
	}

}