package teamKuiper.redoxiation.blocks.rocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.BlockBase;
import teamKuiper.redoxiation.blocks.IVariantType;

public class BlockObsidianOre extends BlockBase {
	
	public static final PropertyEnum<OreType> TYPE = PropertyEnum.create("type", OreType.class);
	
	public BlockObsidianOre() {
		super(Material.ROCK);
		
		setUnlocalizedName("ore");
        setRegistryName(Redoxiation.MODID, "ore_obsidian");
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
	
	@Override
	public void postInit() {
		oreObsidianIron = addVariant(OreType.IRON.metadata, "oreObsidianIron");
		oreObsidianGold = addVariant(OreType.GOLD.metadata, "oreObsidianGold");
		oreObsidianCopper = addVariant(OreType.COPPER.metadata, "oreObsidianCopper");
		oreObsidianTin = addVariant(OreType.TIN.metadata, "oreObsidianTin");
		oreObsidianLead = addVariant(OreType.LEAD.metadata, "oreObsidianLead");
		oreObsidianSilver = addVariant(OreType.SILVER.metadata, "oreObsidianSilver");
		oreObsidianNickel = addVariant(OreType.NICKEL.metadata, "oreObsidianNickel");
		oreObsidianPlatinum = addVariant(OreType.PLATINUM.metadata, "oreObsidianPlatinum");
		oreObsidianZinc = addVariant(OreType.ZINC.metadata, "oreObsidianZinc");
		oreObsidianCobalt = addVariant(OreType.COBALT.metadata, "oreObsidianCobalt");
		oreObsidianChromium = addVariant(OreType.CHROMUIM.metadata, "oreObsidianChromium");
		oreObsidianUranium = addVariant(OreType.URANIUM.metadata, "oreObsidianUranium");
		oreObsidianPlutonium = addVariant(OreType.PLUTONIUM.metadata, "oreObsidianPlutonium");
	}
	
	public static ItemStack oreObsidianIron, oreObsidianGold, oreObsidianCopper, oreObsidianTin, oreObsidianLead,
			oreObsidianSilver, oreObsidianNickel, oreObsidianPlatinum, oreObsidianZinc, oreObsidianCobalt,
			oreObsidianChromium, oreObsidianUranium, oreObsidianPlutonium;

	public enum OreType implements IVariantType {
		IRON(0, "iron", 3),
		GOLD(1, "gold", 3),
		COPPER(2, "copper", 3),
		TIN(3, "tin", 3),
		LEAD(4, "lead", 3),
		SILVER(5, "silver", 3),
		NICKEL(6, "nickel", 3),
		PLATINUM(7, "platinum", 3),
		ZINC(8, "zinc", 3),
		COBALT(9, "cobalt", 3),
		CHROMUIM(10, "chromium", 3),
		URANIUM(11, "uranium", 3),
		PLUTONIUM(12, "plutonium", 3);

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
