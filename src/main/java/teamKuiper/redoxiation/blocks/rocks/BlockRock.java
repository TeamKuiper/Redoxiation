package teamKuiper.redoxiation.blocks.rocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.items.RedoxiationGenericItems;

public class BlockRock extends Block {
	
	public static final PropertyEnum<RockType> TYPE = PropertyEnum.create("type", RockType.class);

	public BlockRock() {
		super(Material.ROCK);
		
		setUnlocalizedName("rock");
		setCreativeTab(Redoxiation.tabRedoxiation);
		
		for(int i = 0; i < RockType.values().length; i++) {
			RockType type = RockType.values()[i];
			setHarvestLevel("pickaxe", type.getHarvestLevel(), getStateFromMeta(type.getMetadata()));
		}

		setHardness(2.0F);
		setResistance(10.0F);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(TYPE).getDropItem();
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		RockType type = state.getValue(TYPE);
		int quantityBase = type.getQuantityBase();
		int amount = (quantityBase == 0 ? 0 : random.nextInt(quantityBase)) + type.getQuantityRandomMax();
		if (fortune > 0) {
			int j = random.nextInt(fortune + 5) - 1;
			if (j > 0) {
				j++;
			}
			amount += j;
		}
		return amount;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TYPE);
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < RockType.values().length; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, RockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE).getMetadata();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(TYPE).getMetadata();
	}
	
	public enum RockType implements IStringSerializable {
		
		BAUXITE(0, "bauxite", 2, RedoxiationGenericItems.rawBauxite, 2, 2),
		CRYOLITE(1, "cryolite", 1, RedoxiationGenericItems.itemCryolite, 2, 2),
		DOLOMITE(2, "dolomite", 1, RedoxiationGenericItems.dolomiteShard, 1, 0),
		LIMESTONE(3, "limestone", 1, RedoxiationGenericItems.calcite, 3, 2),
		ORE_SULFUR(4, "oreSulfur", 2, RedoxiationGenericItems.sulfurChunk, 3, 2),
		RUTILE(5, "rutile", 2, RedoxiationGenericItems.rawRutile, 2, 2),
		SALTROCK(6, "saltrock", 1, RedoxiationGenericItems.saltChunk, 2, 2),
		SCHEELITE(7, "sceelite", 2, RedoxiationGenericItems.rawScheelite, 2, 2);

		private final int metadata;
		private final String name;
		private final int harvestLevel;
		private final Item dropItem;
		private final int quantityBase;
		private final int quantityRandomMax;
		
		RockType(int metadata, String name, int harvestLevel, Item dropItem, int quantityBase, int quantityRandomMax) {
			this.metadata = metadata;
			this.name = name;
			this.harvestLevel = harvestLevel;
			this.dropItem = dropItem;
			this.quantityBase = quantityBase;
			this.quantityRandomMax = quantityRandomMax;
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

		public Item getDropItem() {
			return dropItem;
		}

		public int getQuantityBase() {
			return quantityBase;
		}

		public int getQuantityRandomMax() {
			return quantityRandomMax;
		}
	}
}
