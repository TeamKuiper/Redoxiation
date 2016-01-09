package teamKuiper.redoxiation.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;
import teamKuiper.redoxiation.Redoxiation;

public class RedoxiationBucket extends ItemBucket {
	private String ITEMNAME;
	
	public RedoxiationBucket(Block block, String name) {
		super(block);
		this.setCreativeTab(Redoxiation.tabRedoxiationitems);
		ITEMNAME = name;
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(Redoxiation.MODID + ":" + ITEMNAME);
	}
}
