package teamKuiper.redoxiation.blocks.rocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.BlockBase;
import teamKuiper.redoxiation.blocks.IVariantType;

public class BlockNetherOre extends BlockBase {
	
	public static final PropertyEnum<OreType> TYPE = PropertyEnum.create("type", OreType.class);
	
	public BlockNetherOre() {
		super(Material.ROCK);
		
		setUnlocalizedName("ore");
        setRegistryName(Redoxiation.MODID, "ore_nether");
		setCreativeTab(Redoxiation.tabRedoxiation);
		setHardness(3.0F);
		setResistance(15.0F);
		
		for(int i = 0; i < OreType.values().length; i++) {
			OreType type = OreType.values()[i];
			setHarvestLevel("pickaxe", type.getHarvestLevel(), getStateFromMeta(type.getMetadata()));
		}
	}
	
	//For tntium -start-
	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if(world.getBlockState(pos).getValue(TYPE) == OreType.TNTIUM) {
			Random random = new Random();
			if(!Minecraft.getMinecraft().player.capabilities.isCreativeMode) {
				if(!world.isRemote){
					if (random.nextInt(4) >= 3) {
						world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 2, true);
					}
				}
			}
		}
	}
	
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return (state.getValue(TYPE) == OreType.TNTIUM) ? Items.GUNPOWDER : Item.getItemFromBlock(this);
    }

	@Override
	public int damageDropped(IBlockState state) {
		return (state.getValue(TYPE) == OreType.TNTIUM) ? 0 : state.getValue(TYPE).getMetadata();
	}
    
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		if(state.getValue(TYPE) == OreType.TNTIUM) {
			return 1 + random.nextInt(2);
		}
		return 1;
	}
	//For tntium -end-

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
	public void init() {
		ferroNickel = addVariant(OreType.FERRO_NICKEL.metadata, "blockFerroNickel");
		pseudoBronze = addVariant(OreType.PSEUDO_BRONZE.metadata, "blockPseudoBronze");
		pseudoBrass = addVariant(OreType.PSEUDO_BRASS.metadata, "blockFerroBrass");
		argentAurum = addVariant(OreType.ARGENT_AURUM.metadata, "blockArgentAurum");
		pseudoSolder = addVariant(OreType.PSEUDO_SOLDER.metadata, "blockPseudoSolder");
		tntium = addVariant(OreType.TNTIUM.metadata, "blockTNTium");
	}
	
	public static ItemStack ferroNickel, pseudoBronze, pseudoBrass, argentAurum, pseudoSolder, pseudoStellite, tntium;
	
	public enum OreType implements IVariantType {
		FERRO_NICKEL(0, "ferro_nickel", 2),
		PSEUDO_BRONZE(1, "pseudo_bronze", 2),
		PSEUDO_BRASS(2, "pseudo_brass", 2),
		ARGENT_AURUM(3, "argent_aurum", 2),
		PSEUDO_SOLDER(4, "pseudo_solder", 2),
		PSEUDO_STELLITE(5, "pseudo_stellite", 2),
		TNTIUM(6, "tntium", 2);
		
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
