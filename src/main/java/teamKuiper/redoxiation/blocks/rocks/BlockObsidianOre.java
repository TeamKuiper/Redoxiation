package teamKuiper.redoxiation.blocks.rocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import teamKuiper.redoxiation.Redoxiation;

public class BlockObsidianOre extends Block {
	
	public static final PropertyEnum<OreType> TYPE = PropertyEnum.create("type", OreType.class);

	public BlockObsidianOre() {
		super(Material.ROCK);
		setUnlocalizedName("ore");
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHardness(50.0F);
		setResistance(2000.0F);
		
		for(int i = 0; i < OreType.values().length; i++) {
			OreType type = OreType.values()[i];
			setHarvestLevel("pickaxe", type.getHarvestLevel(), getStateFromMeta(type.getMetadata()));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TYPE);
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < OreType.values().length; i++) {
			items.add(new ItemStack(this, 1, i));
		}
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
	
	public enum OreType implements IStringSerializable {
		
		OBSIDIAN_IRON(1, "obsidianIron", 3),
		OBSIDIAN_GOLD(2, "obsidianGold", 3),
		OBSIDIAN_COPPER(3, "obsidianCopper", 3),
		OBSIDIAN_TIN(4, "obsidianTin", 3),
		OBSIDIAN_LEAD(5, "obsidianLead", 3),
		OBSIDIAN_SILVER(6, "obsidianSilver", 3),
		OBSIDIAN_NICKEL(7, "obsidianNickel", 3),
		OBSIDIAN_PLATINUM(8, "obsidianPlatinum", 3),
		OBSIDIAN_ZINC(9, "obsidianZinc", 3),
		OBSIDIAN_COBALT(10, "obsidianCobalt", 3),
		OBSIDIAN_CHROMUIM(11, "obsidianChromium", 3),
		OBSIDIAN_URANIUM(12, "obsidianUranium", 3),
		OBSIDIAN_PLUTONIUM(13, "obsidianPlutonium", 3);

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
