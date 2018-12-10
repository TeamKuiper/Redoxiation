package teamKuiper.redoxiation.utils;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class WorldGenDeterminer implements Predicate<IBlockState> {
	
	Block[] blocks;
	
	public WorldGenDeterminer(Block... blocks) {
		this.blocks = blocks;
	}

	@Override
	public boolean apply(IBlockState input) {
		Block block = input.getBlock();
		for(int i = 0; i < blocks.length; i++) {
			if(blocks[i] == block)
				return true;
		}
		return false;
	}

}
