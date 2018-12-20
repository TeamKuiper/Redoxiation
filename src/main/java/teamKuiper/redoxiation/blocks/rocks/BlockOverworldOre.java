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
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.BlockBase;
import teamKuiper.redoxiation.blocks.IVariantType;
import teamKuiper.redoxiation.items.ItemCommon;

public class BlockOverworldOre extends BlockBase {
	
	public static final PropertyEnum<OreType> TYPE = PropertyEnum.create("type", OreType.class);
	
	public BlockOverworldOre() {
		super(Material.ROCK);
		setUnlocalizedName("ore");
		setRegistryName(Redoxiation.MODID, "ore_overworld");
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHardness(2.5F);
		setResistance(5.0F);
		
		for(int i = 0; i < OreType.values().length; i++) {
			OreType type = OreType.values()[i];
			setHarvestLevel("pickaxe", type.getHarvestLevel(), getStateFromMeta(type.getMetadata()));
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
        return ((state.getValue(TYPE) == OreType.SULFUR) ? ItemCommon.sulfurChunk.getItem() : Item.getItemFromBlock(this));
    }

	@Override
	public int damageDropped(IBlockState state) {
		return ((state.getValue(TYPE) == OreType.SULFUR) ? ItemCommon.sulfurChunk.getItemDamage() : state.getValue(TYPE).getMetadata());
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
	public void postInit() {
		oreCopper = addVariant(OreType.COPPER.getMetadata(), "oreCopper");
		oreTin = addVariant(OreType.TIN.getMetadata(), "oreTin");
		oreLead = addVariant(OreType.LEAD.getMetadata(), "oreLead");
		oreSilver = addVariant(OreType.SILVER.getMetadata(), "oreSilver");
		oreNickel = addVariant(OreType.NICKEL.getMetadata(), "oreNickel");
		orePlatinum = addVariant(OreType.PLATINUM.getMetadata(), "orePlatinum");
		oreZinc = addVariant(OreType.ZINC.getMetadata(), "oreZinc");
		oreCobalt = addVariant(OreType.COBALT.getMetadata(), "oreCobalt");
		oreChromium = addVariant(OreType.CHROMIUM.getMetadata(), "oreChromium");
		orePitchblend = addVariant(OreType.PITCHBLEND.getMetadata(), "orePitchblend");
		oreSulfur = addVariant(OreType.SULFUR.getMetadata(), "oreSulfur");
	}
	
	public static ItemStack oreCopper, oreTin, oreLead, oreSilver, oreNickel, orePlatinum, oreZinc, oreCobalt, oreChromium, orePitchblend, oreSulfur;
	
	public enum OreType implements IVariantType {
		COPPER(0, "copper", 1),
		TIN(1, "tin", 1),
		LEAD(2, "lead", 1),
		SILVER(3, "silver", 2),
		NICKEL(4, "nickel", 1),
		PLATINUM(5, "platinum", 2),
		ZINC(6, "zinc", 1),
		COBALT(7, "cobalt", 1),
		CHROMIUM(8, "chromium", 1),
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
