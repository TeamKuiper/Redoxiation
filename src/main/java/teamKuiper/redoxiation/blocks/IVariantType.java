package teamKuiper.redoxiation.blocks;

import net.minecraft.util.IStringSerializable;

public interface IVariantType extends IStringSerializable {
	public int getMetadata();
	public String getName();
}
