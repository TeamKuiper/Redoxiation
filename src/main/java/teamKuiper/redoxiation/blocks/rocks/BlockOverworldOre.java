package teamKuiper.redoxiation.blocks.rocks;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.BlockBase;
import teamKuiper.redoxiation.blocks.IVariantType;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class BlockOverworldOre extends BlockBase {
	
	public static final PropertyEnum<OreType> TYPE = PropertyEnum.create("type", OreType.class);
	
	public BlockOverworldOre() {
		super(Material.ROCK);
		
		setRegistryName("oreOverworld");
		setUnlocalizedName("ore");
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHardness(2.5F);
		setResistance(5.0F);
		
		for(int i = 0; i < OreType.values().length; i++) {
			OreType type = OreType.values()[i];
			setHarvestLevel("pickaxe", type.getHarvestLevel(), getStateFromMeta(type.getMetadata()));
		}
		
		variants = new ItemStack[OreType.values().length];
		for (int i = 0; i < OreType.values().length; i++) {
			variants[i] = new ItemStack(this, 1, i);
		}
	}
	
	@Override
	public void onRegister() {
		for(int i = 0; i < variants.length; i++) {
			OreDictionary.registerOre(OreType.values()[i].getName(), variants[i]);
		}
	}

	//For sulfur ore -start-
	@Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
        return (blockState.getValue(TYPE) == OreType.SULFUR) ? 2.0F : this.blockHardness;
    }

	@Override
    public float getExplosionResistance(World world, BlockPos pos, @Nullable Entity exploder, Explosion explosion) {
        return ((world.getBlockState(pos).getValue(TYPE) == OreType.SULFUR) ? 10.0F : this.blockResistance) / 5.0F;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ((state.getValue(TYPE) == OreType.SULFUR) ? RedoxiationGenericItems.sulfurChunk : Item.getItemFromBlock(this));
    }
    
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		if(state.getValue(TYPE) == OreType.SULFUR) {
			int amount = 3 + random.nextInt(2);
			if (fortune > 0) {
				int j = random.nextInt(fortune + 5) - 1;
				if (j > 0) {
					j++;
				}
				amount += j;
			}
			return amount;
		}
		return 1;
	}
	//For sulfur ore -end-
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TYPE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, OreType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE).getMetadata();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(TYPE).getMetadata();
	}
	
	public enum OreType implements IVariantType {
		
		COPPER(0, "oreCopper", 1),
		TIN(1, "oreTin", 1),
		LEAD(2, "oreLead", 1),
		SILVER(3, "oreSilver", 2),
		NICKEL(4, "oreNickel", 1),
		PLATINUM(5, "orePlatinum", 2),
		ZINC(6, "oreZinc", 1),
		COBALT(7, "oreCobalt", 1),
		CHROMIUM(8, "oreChromium", 1),
		PITCHBLEND(9, "pitchblend", 2),
		SULFUR(10, "sulfur", 2);

		private final int metadata;
		private final String name;
		private final int harvestLevel;
		
		OreType(int metadata, String name, int harvestLevel) {
			this.metadata = metadata;
			this.name = name;
			this.harvestLevel = harvestLevel;
		}

		public int getMetadata() {
			return metadata;
		}

		public String getName() {
			return name;
		}

		public int getHarvestLevel() {
			return harvestLevel;
		}
	}
}
