package teamKuiper.redoxiation.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.Utils;

public class ItemBlockLeadTank extends ItemBlock {

    public ItemBlockLeadTank(Block p_i45328_1_) {
        super(p_i45328_1_);
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean isAdvanced) {
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.stackTagCompound;
            FluidStack fluid = null;
            if (tag.hasKey("Fluid")) {
                fluid = FluidStack.loadFluidStackFromNBT((NBTTagCompound) tag.getTag("Fluid"));
                tooltip.add(StatCollector.translateToLocal("tooltip."+ Redoxiation.MODID + ".leadTank.fluid") + ": " + fluid.getLocalizedName());
            }
            int amount = (fluid != null) ? fluid.amount : 0;
            tooltip.add(StatCollector.translateToLocal("tooltip."+ Redoxiation.MODID + ".leadTank.amount") + ": " + amount + " / " + 20000 + " mB");
        } else {
            tooltip.add(StatCollector.translateToLocal("tooltip."+ Redoxiation.MODID + ".tank.error"));
        }
    }
 
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List itemList) {
        itemList.add(Utils.getTankStackFromData());
    }
}
 
