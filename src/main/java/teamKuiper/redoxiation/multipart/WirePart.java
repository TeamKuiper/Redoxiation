package teamKuiper.redoxiation.multipart;

import codechicken.lib.vec.BlockCoord;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.minecraft.McSidedMetaPart;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import teamKuiper.redoxiation.Redoxiation;
import teamKuiper.redoxiation.blocks.RedoxiationBlocks;
import teamKuiper.redoxiation.render.RenderWirePart;

public class WirePart extends McSidedMetaPart {
    private RenderWirePart rend;
    private float scale = 1.0f;
    private float rotation = 0;
    private float angvel = 0;
    private int state;
    private int chunknumber = 0;
    public static int[] sideMetaMap = new int[]{1, 0, 3, 2, 5, 4};

    public WirePart() {
        super();
    }

    public WirePart(int meta) {
        super(meta);
    }

    @Override
    public void update() {
        if (chunknumber != 0) {
            angvel = 1 / ((float) chunknumber);
        } else {
            angvel = 0;
        }
        Redoxiation.logger.info(chunknumber);
        if (world().isRemote) {
            rotation += angvel;
        }
        if (rotation >= 360) {
            rotation = rotation - 360;
        }
    }

    @Override
    public Cuboid6 getBounds() {
        if (meta == 0) {
            return new Cuboid6(0.3125, 0.9375, 0.3125, 0.6875, 1.0, 0.6875);
        } else if (meta == 1) {
            return new Cuboid6(0.3125, 0.0, 0.3125, 0.6875, 0.0625, 0.6875);
        } else if (meta == 2) {
            return new Cuboid6(0.3125, 0.3125, 0.9375, 0.6875, 0.6875, 1.0);
        } else if (meta == 3) {
            return new Cuboid6(0.3125, 0.3125, 0.0, 0.6875, 0.6875, 0.0625);
        } else if (meta == 4) {
            return new Cuboid6(0.9375, 0.3125, 0.3125, 1.0, 0.6875, 0.6875);
        } else if (meta == 5) {
            return new Cuboid6(0.0, 0.3125, 0.3125, 0.0625, 0.6875, 0.6875);
        }
        return null;
    }

    @Override
    public Block getBlock() {
        return RedoxiationBlocks.Wire;
    }

    @Override
    public String getType() {
        return Redoxiation.MODID + "Wire";
    }

    @Override
    public int sideForMeta(int meta) {
        return ForgeDirection.getOrientation(meta).getOpposite().ordinal();
    }

    public static WirePart placement(World world, BlockCoord pos, int side) {
        pos = pos.copy().offset(side^1);
        Block block = world.getBlock(pos.x, pos.y, pos.z);
        if (!block.isSideSolid(world, pos.x, pos.y, pos.z, ForgeDirection.getOrientation(side))) {
            return null;
        }
        return new WirePart(sideMetaMap[side^1]);
    }

    @Override
    public void save(NBTTagCompound tag) {
        super.save(tag);
        tag.setFloat("scale", scale);
        tag.setFloat("rotation", rotation);
        tag.setFloat("angvel", angvel);
        tag.setInteger("state", state);
        tag.setInteger("chunknumber", chunknumber);
    }

    @Override
    public void load(NBTTagCompound tag) {
        super.load(tag);
        tag.getFloat("scale");
        tag.getFloat("rotation");
        tag.getFloat("angvel");
        tag.getInteger("state");
        tag.getInteger("chunknumber");
    }

//    @Override
//    public void invalidateConvertedTile() {
//        Redoxiation.logger.info("invalidateconvertedtile");
//        TileWire tile = (TileWire)this.world().getTileEntity(x(), y(), z());
//        this.scale = tile.getscale();
//        this.rotation = tile.getrotation();
//        this.angvel = tile.getangvel();
//        this.state = tile.getstate();
//        this.chunknumber = tile.getchunknumber();
//    }

    @Override
    public void onAdded() {
        super.onAdded();
        Redoxiation.logger.info("onadded");
    }

    @Override
    public void onWorldJoin() {
        Redoxiation.logger.info("onworldjoin");
        rend = new RenderWirePart();
        rend.func_147497_a(TileEntityRendererDispatcher.instance);
    }

    @Override
    public void renderDynamic(Vector3 pos, float frame, int pass) {
        Redoxiation.logger.info("renderdynamic" + x() + ":" + y() + ":" + z());
        Redoxiation.logger.info("x" + pos.x + "y" + pos.y + "z" + pos.z);
        rend.renderTileEntityAt(tile(), pos.x, pos.y, pos.z, sideForMeta(meta) + 1);
    }

    public float getscale() {
        return scale;
    }

    public void setscale(int argV) {
        scale = argV;
    }

    public float getrotation() {
        return rotation;
    }

    public void setsotation(float argR) {
        rotation = argR;
    }

    public int getchunknumber() {
        return chunknumber;
    }

    public void setchunknumber(int chunknumber) {
        this.chunknumber = chunknumber;
    }

    public int getstate() {
        return state;
    }

//    public boolean checkstate(int x, int y, int z, int st) {
//        return ((world().getBlock(x, y, z) == RedoxiationBlocks.Wire) && (((TileWire) worldObj.getTileEntity(x, y, z)).state != st));
//    }
//
//    public int fill(int x, int y, int z, int checknum, int st) {
//        checknum++;
//        TileWire tile = (TileWire) worldObj.getTileEntity(x, y, z);
//        tile.state = st;
//        if (checkstate(x + 1, y, z, st)) {
//            checknum = fill(x + 1, y, z, checknum, st);
//        }
//        if (checkstate(x - 1, y, z, st)) {
//            checknum = fill(x - 1, y, z, checknum, st);
//        }
//        if (checkstate(x, y + 1, z, st)) {
//            checknum = fill(x, y + 1, z, checknum, st);
//        }
//        if (checkstate(x, y - 1, z, st)) {
//            checknum = fill(x, y - 1, z, checknum, st);
//        }
//        if (checkstate(x, y, z + 1, st)) {
//            checknum = fill(x, y, z + 1, checknum, st);
//        }
//        if (checkstate(x, y, z - 1, st)) {
//            checknum = fill(x, y, z - 1, checknum, st);
//        }
//
//        tile.setchunknumber(checknum);
//        return checknum;
//    }
//
//    public int setfill(int x, int y, int z, int checknum, int st, World world) {
//        TileEntityWoodenCog tile = (TileEntityWoodenCog) world.getTileEntity(x, y, z);
//        tile.setstate(st);
//        tile.setchunknumber(checknum);
//
//        if (checkstate(x + 1, y, z, st)) {
//            setfill(x + 1, y, z, checknum, st, world);
//        }
//        if (checkstate(x - 1, y, z, st)) {
//            setfill(x - 1, y, z, checknum, st, world);
//        }
//        if (checkstate(x, y + 1, z, st)) {
//            setfill(x, y + 1, z, checknum, st, world);
//        }
//        if (checkstate(x, y - 1, z, st)) {
//            setfill(x, y - 1, z, checknum, st, world);
//        }
//        if (checkstate(x, y, z + 1, st)) {
//            setfill(x, y, z + 1, checknum, st, world);
//        }
//        if (checkstate(x, y, z - 1, st)) {
//            setfill(x, y, z - 1, checknum, st, world);
//        }
//        return checknum;
//    }
}