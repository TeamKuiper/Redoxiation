package teamKuiper.redoxiation.blocks.cog;

import net.minecraft.block.state.IBlockState;
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
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(hand == EnumHand.MAIN_HAND) {
			ItemStack stack = player.getHeldItemMainhand();
			BlockPos newPos = pos.add(facing.getFrontOffsetX(), facing.getFrontOffsetY(), facing.getFrontOffsetZ());
			IBlockState state = worldIn.getBlockState(newPos);
			if(state.getBlock() == RedoxiationBlocks.cog) {
				boolean succeed = RedoxiationBlocks.cog.placeSide(state, facing.getOpposite(), CogType.values()[stack.getMetadata()]);
				if(succeed) {
					return EnumActionResult.SUCCESS;
				}
			}
		}
		return EnumActionResult.FAIL;
	}

}
