package teamKuiper.redoxiation.blocks.cog;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;

public class ItemBlockCog extends ItemBlock {

	public ItemBlockCog() {
		super(RedoxiationBlocks.cog);
		setRegistryName("cog");
		setUnlocalizedName("cog");
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(hand == EnumHand.MAIN_HAND) {
			ItemStack stack = player.getHeldItemMainhand();
			BlockPos newPos = pos.add(facing.getFrontOffsetX(), facing.getFrontOffsetY(), facing.getFrontOffsetZ());
			if(worldIn.getBlockState(newPos).getBlock() == RedoxiationBlocks.cog) {
				boolean succeed = ((TileCog) worldIn.getTileEntity(pos)).placeSide(facing.getOpposite(), CogType.values()[stack.getMetadata()]);
				if(succeed) {
					return EnumActionResult.SUCCESS;
				}
			}
		}
		return EnumActionResult.FAIL;
	}

}
