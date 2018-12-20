package teamKuiper.redoxiation.blocks.rocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.BlockBase;
import teamKuiper.redoxiation.blocks.IVariantType;
import teamKuiper.redoxiation.items.ItemCommon;

public class BlockRock extends BlockBase {
	
	public static final PropertyEnum<RockType> TYPE = PropertyEnum.create("type", RockType.class);
	
	public BlockRock() {
		super(Material.ROCK);
		
		setUnlocalizedName("rock");
		setRegistryName(Redoxiation.MODID, "rock");
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHardness(2.0F);
		setResistance(10.0F);
		
		for(int i = 0; i < RockType.values().length; i++) {
			RockType type = RockType.values()[i];
			setHarvestLevel("pickaxe", type.getHarvestLevel(), getStateFromMeta(type.getMetadata()));
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(TYPE).getDropItem().getItem();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(TYPE).getDropItem().getMetadata();
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
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, RockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE).getMetadata();
	}
	
	@Override
	public void postInit() {
		bauxite = addVariant(RockType.BAUXITE.getMetadata(), "blockBauxite");
		cryolite = addVariant(RockType.CRYOLITE.getMetadata(), "blockCryolite");
		dolomite = addVariant(RockType.DOLOMITE.getMetadata(), "blockDolomite");
		limestone = addVariant(RockType.LIMESTONE.getMetadata(), "blockLimestone");
		rutile = addVariant(RockType.RUTILE.getMetadata(), "blockRutile");
		saltRock = addVariant(RockType.SALTROCK.getMetadata(), "blockSaltrock");
		sceelite = addVariant(RockType.SCHEELITE.getMetadata(), "blockSceelite");
	}
	
	public static ItemStack bauxite, cryolite, dolomite, limestone, rutile, saltRock, sceelite;
	
	public enum RockType implements IVariantType {
		BAUXITE(0, "bauxite", 2, ItemCommon.rawBauxite, 2, 2),
		CRYOLITE(1, "cryolite", 1, ItemCommon.itemCryolite, 2, 2),
		DOLOMITE(2, "dolomite", 1, ItemCommon.dolomiteShard, 1, 0),
		LIMESTONE(3, "limestone", 1, ItemCommon.calcite, 3, 2),
		RUTILE(4, "rutile", 2, ItemCommon.rawRutile, 2, 2),
		SALTROCK(5, "saltrock", 1, ItemCommon.saltChunk, 2, 2),
		SCHEELITE(6, "sceelite", 2, ItemCommon.rawScheelite, 2, 2);

		private final int metadata;
		private final String name;
		private final int harvestLevel;
		private final ItemStack dropItem;
		private final int quantityBase;
		private final int quantityRandomMax;
		
		RockType(int metadata, String name, int harvestLevel, ItemStack dropItem, int quantityBase, int quantityRandomMax) {
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

		public ItemStack getDropItem() {
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
