package teamKuiper.redoxiation.blocks.tileentity;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class TileMachineBase extends TileEntity implements IInventory, ITickable {

	Block block;
	private int[][] emptyPos;
	
	private int xMinus, yMinus, zMinus;
	private int xPlus, yPlus, zPlus;
	
	private boolean hasMaster, isMaster;
	public boolean hasmastercheck;
	private BlockPos masterPos;

	Item[] fuel;
	private int[] burnTime;
	ItemStack[][] recipe;
	private String inventoryName;
	
	TileMachineBase(String inventoryName, Block block, int xMinus, int yMinus, int zMinus, int xPlus, int yPlus, int zPlus, int[][] emptyPos, Item[] fuel, int[] burnTime, ItemStack[][] recipe) {
		this.inventoryName = inventoryName;
		this.block = block;
		this.xMinus = xMinus;
		this.yMinus = yMinus;
		this.zMinus = zMinus;
		this.xPlus = xPlus;
		this.yPlus = yPlus;
		this.zPlus = zPlus;
		this.emptyPos = emptyPos;
		this.fuel = fuel;
		this.burnTime = burnTime;
		this.recipe = recipe;
	}
	
	public int[] getSlotCount() {
		return new int[]{1, recipe[0].length, recipe[1].length};
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {
			if (hasMaster()) {
				hasmastercheck = true;
				if (isMaster()) {
					if (!checkMultiBlockForm()) {
						resetStructure();
					}
				}
			} else {
				// Constantly check if structure is formed until it is.
				if (checkMultiBlockForm()) {
					setupStructure();
				}
			}
		}
		if (canSmelt()) {
			int numberOfFuelBurning = burnFuel();

			// If fuel is available, keep cooking the item, otherwise start
			// "uncooking" it at double speed
			if (numberOfFuelBurning > 0) {
				cookTime += numberOfFuelBurning;
			} else {
				cookTime -= 1;
			}

			if (cookTime < 0) {
				cookTime = 0;
			}

			// If cookTime has reached maxCookTime smelt the item and reset
			// cookTime
			if (cookTime >= COOK_TIME_FOR_COMPLETION) {
				smeltItem();
				cookTime = 0;
			}
		} else {
			burnTimeRemaining[0] = 0;
			cookTime = 0;
		}

		// when the number of burning slots changes, we need to force the block
		// to re-render, otherwise the change in
		// state will not be visible. Likewise, we need to force a lighting
		// recalculation.
		// The block update (for renderer) is only required on client side, but
		// the lighting is required on both, since
		// the client needs it for rendering and the server needs it for crop
		// growth etc
		int numberBurning = numberOfBurningFuelSlots();
		if (cachedNumberOfBurningSlots != numberBurning) {
			cachedNumberOfBurningSlots = numberBurning;
			if (world.isRemote) {
				world.markBlockForUpdate(pos);
			}
		}
	}

	/** Check that structure is properly formed */
	public boolean checkMultiBlockForm() {
		int xCoord = pos.getX();
		int yCoord = pos.getY();
		int zCoord = pos.getZ();
		
		for (int x = xCoord - xMinus; x <= xCoord + xPlus; x++) {
			for (int y = yCoord - yMinus; y <= yCoord + yPlus; y++) {
				for (int z = zCoord - zMinus; z <= zCoord + zPlus; z++) {
					//check Block
					boolean isEmpty = false;
					for(int i = 0; i < emptyPos.length; i++) {
						if(xCoord + emptyPos[i][0] == x && yCoord + emptyPos[i][1] == y && zCoord + emptyPos[i][2] == z) {
							isEmpty = true;
							break;
						}
						
					}
					Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
					if(isEmpty ? !(block == Blocks.AIR) : !block.equals(block)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/** Setup all the blocks in the structure */
	public void setupStructure() {
		int xCoord = pos.getX();
		int yCoord = pos.getY();
		int zCoord = pos.getZ();
		
		for (int x = xCoord - xMinus; x <= xCoord + xPlus; x++) {
			for (int y = yCoord - yMinus; y <= yCoord + yPlus; y++) {
				nextLoop:
				for (int z = zCoord - zMinus; z <= zCoord + zPlus; z++) {
					for (int i = 0; i < emptyPos.length; i++) {
						if (xCoord + emptyPos[i][0] == x && yCoord + emptyPos[i][1] == y
								&& zCoord + emptyPos[i][2] == z) {
							continue nextLoop;
						}
					}

					TileEntity tile = world.getTileEntity(pos);
					// Check if block is bottom center block
					boolean master = (x == xCoord && y == yCoord && z == zCoord);
					if (tile != null && this.getClass().isInstance(tile)) {
						((TileMachineBase) tile).setMasterCoords(new BlockPos(xCoord, yCoord, zCoord));
						((TileMachineBase) tile).setHasMaster(true);
						((TileMachineBase) tile).setIsMaster(master);
						((TileMachineBase) tile).hasmastercheck = true;
					}

				}
			}
		}
				
	}

	/** Reset method to be run when the master is gone or tells them to */
	public void reset() {
		masterPos = new BlockPos(0, 0, 0);
		hasMaster = false;
		isMaster = false;
		hasmastercheck = false;
	}

	/** Check that the master exists */
	public boolean checkForMaster() {
		return world.getBlockState(masterPos).getBlock() == block;
	}

	/** Reset all the parts of the structure */
	public void resetStructure() {
		int xCoord = pos.getX();
		int yCoord = pos.getY();
		int zCoord = pos.getZ();
		
		for (int x = xCoord - xMinus; x <= xCoord + xPlus; x++) {
			for (int y = yCoord - yMinus; y <= yCoord + yPlus; y++) {
				nextLoop:
				for (int z = zCoord - zMinus; z <= zCoord + zPlus; z++) {
					for (int i = 0; i < emptyPos.length; i++) {
						if (xCoord + emptyPos[i][0] == x && yCoord + emptyPos[i][1] == y
								&& zCoord + emptyPos[i][2] == z) {
							continue nextLoop;
						}
					}
					
					TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
					if (tile != null && (this.getClass().isInstance(tile))) {
						((TileMachineBase) tile).reset();
					}
				}
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setInteger("masterX", masterPos.getX());
		data.setInteger("masterY", masterPos.getY());
		data.setInteger("masterZ", masterPos.getZ());
		data.setBoolean("hasMaster", hasMaster);
		data.setBoolean("isMaster", isMaster);
		if (hasMaster() && isMaster()) {
			// Any other values should ONLY BE SAVED TO THE MASTER
		}
		// Save the stored item stacks

		// to use an analogy with Java, this code generates an array of hashmaps
		// The itemStack in each slot is converted to an NBTTagCompound, which
		// is effectively a hashmap of key->value pairs such
		// as slot=1, id=2353, count=1, etc
		// Each of these NBTTagCompound are then inserted into NBTTagList, which
		// is similar to an array.
		NBTTagList dataForAllSlots = new NBTTagList();
		for (int i = 0; i < this.itemStacks.length; ++i) {
			if (this.itemStacks[i] != null) {
				NBTTagCompound dataForThisSlot = new NBTTagCompound();
				dataForThisSlot.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(dataForThisSlot);
				dataForAllSlots.appendTag(dataForThisSlot);
			}
		}
		// the array of hashmaps is then inserted into the parent hashmap for
		// the container
		data.setTag("Items", dataForAllSlots);

		// Save everything else
		data.setShort("CookTime", cookTime);
		data.setTag("burnTimeRemaining", new NBTTagIntArray(burnTimeRemaining));
		data.setTag("burnTimeInitial", new NBTTagIntArray(burnTimeInitialValue));
		
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		int masterX = data.getInteger("masterX");
		int masterY = data.getInteger("masterY");
		int masterZ = data.getInteger("masterZ");
		masterPos = new BlockPos(masterX, masterY, masterZ);
		
		hasMaster = data.getBoolean("hasMaster");
		isMaster = data.getBoolean("isMaster");
		if (hasMaster() && isMaster()) {
			// Any other values should ONLY BE READ BY THE MASTER
		}
		final byte NBT_TYPE_COMPOUND = 10; // See NBTBase.createNewByType() for
											// a listing
		NBTTagList dataForAllSlots = data
				.getTagList("Items", NBT_TYPE_COMPOUND);

		Arrays.fill(itemStacks, null); // set all slots to empty
		for (int i = 0; i < dataForAllSlots.tagCount(); ++i) {
			NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(i);
			byte slotNumber = dataForOneSlot.getByte("Slot");
			if (slotNumber >= 0 && slotNumber < this.itemStacks.length) {
				this.itemStacks[slotNumber] = ItemStack
						.loadItemStackFromNBT(dataForOneSlot);
			}
		}

		// Load everything else. Trim the arrays (or pad with 0) to make sure
		// they have the correct number of elements
		cookTime = data.getShort("CookTime");
		burnTimeRemaining = Arrays.copyOf(
				data.getIntArray("burnTimeRemaining"), FUEL_SLOTS_COUNT);
		burnTimeInitialValue = Arrays.copyOf(
				data.getIntArray("burnTimeInitial"), FUEL_SLOTS_COUNT);
		cachedNumberOfBurningSlots = -1;
	}

	public boolean hasMaster() {
		return hasMaster;
	}

	public boolean isMaster() {
		return isMaster;
	}

	public BlockPos getMasterPos() {
		return masterPos;
	}

	public void setHasMaster(boolean bool) {
		hasMaster = bool;
	}

	public void setIsMaster(boolean bool) {
		isMaster = bool;
	}

	public void setMasterCoords(BlockPos pos) {
		masterPos = pos;
	}

	// Create and initialize the itemStacks variable that will store store the
	// itemStacks
	public static final int FUEL_SLOTS_COUNT = 1;
	public static final int INPUT_SLOTS_COUNT = 3;
	public static final int OUTPUT_SLOTS_COUNT = 2;
	public static final int TOTAL_SLOTS_COUNT = FUEL_SLOTS_COUNT
			+ INPUT_SLOTS_COUNT + OUTPUT_SLOTS_COUNT;

	public static final int FIRST_FUEL_SLOT = 0;
	public static final int FIRST_INPUT_SLOT = FIRST_FUEL_SLOT
			+ FUEL_SLOTS_COUNT;
	public static final int FIRST_OUTPUT_SLOT = FIRST_INPUT_SLOT
			+ INPUT_SLOTS_COUNT;

	protected ItemStack[] itemStacks = new ItemStack[TOTAL_SLOTS_COUNT];

	/** The number of burn ticks remaining on the current piece of fuel */
	private int[] burnTimeRemaining = new int[FUEL_SLOTS_COUNT];
	/**
	 * The initial fuel value of the currently burning fuel (in ticks of burn
	 * duration)
	 */
	private int[] burnTimeInitialValue = new int[FUEL_SLOTS_COUNT];

	/** The number of ticks the current item has been cooking */
	private short cookTime;
	/** The number of ticks required to cook an item */
	private static final short COOK_TIME_FOR_COMPLETION = 200; // vanilla value
																// is 200 = 10
																// seconds

	private int cachedNumberOfBurningSlots = -1;

	/**
	 * Returns the amount of fuel remaining on the currently burning item in the
	 * given fuel slot.
	 * 
	 * @param fuelSlot
	 *            the number of the fuel slot (0..3)
	 * @return fraction remaining, between 0 - 1
	 */
	public double fractionOfFuelRemaining(int fuelSlot) {
		if (burnTimeInitialValue[fuelSlot] <= 0)
			return 0;
		double fraction = burnTimeRemaining[fuelSlot]
				/ (double) burnTimeInitialValue[fuelSlot];
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	/**
	 * return the remaining burn time of the fuel in the given slot
	 * 
	 * @param fuelSlot
	 *            the number of the fuel slot (0..3)
	 * @return seconds remaining
	 */
	public int secondsOfFuelRemaining(int fuelSlot) {
		if (burnTimeRemaining[fuelSlot] <= 0)
			return 0;
		return burnTimeRemaining[fuelSlot] / 20; // 20 ticks per second
	}

	/**
	 * Get the number of slots which have fuel burning in them.
	 * 
	 * @return number of slots with burning fuel, 0 - FUEL_SLOTS_COUNT
	 */
	public int numberOfBurningFuelSlots() {
		int burningCount = 0;
		for (int burnTime : burnTimeRemaining) {
			if (burnTime > 0)
				++burningCount;
		}
		return burningCount;
	}

	/**
	 * Returns the amount of cook time completed on the currently cooking item.
	 * 
	 * @return fraction remaining, between 0 - 1
	 */
	public double fractionOfCookTimeComplete() {
		double fraction = cookTime / (double) COOK_TIME_FOR_COMPLETION;
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	/**
	 * for each fuel slot: decreases the burn time, checks if burnTimeRemaining
	 * = 0 and tries to consume a new piece of fuel if one is available
	 * 
	 * @return the number of fuel slots which are burning
	 */
	private int burnFuel() {
		int burningCount = 0;
		boolean inventoryChanged = false;
		// Iterate over all the fuel slots
		for (int i = 0; i < FUEL_SLOTS_COUNT; i++) {
			int fuelSlotNumber = i + FIRST_FUEL_SLOT;
			if (burnTimeRemaining[i] > 0) {
				--burnTimeRemaining[i];
				++burningCount;
			}
			if (burnTimeRemaining[i] == 0) {
				if (itemStacks[fuelSlotNumber] != null
						&& getItemBurnTime(itemStacks[fuelSlotNumber]) > 0) {
					// If the stack in this slot is not null and is fuel, set
					// burnTimeRemaining & burnTimeInitialValue to the
					// item's burn time and decrease the stack size
					burnTimeRemaining[i] = burnTimeInitialValue[i] = getItemBurnTime(itemStacks[fuelSlotNumber]);
					itemStacks[fuelSlotNumber].setCount(itemStacks[fuelSlotNumber].getCount() - 1);
					++burningCount;
					inventoryChanged = true;
					// If the stack size now equals 0 set the slot contents to
					// the items container item. This is for fuel
					// items such as lava buckets so that the bucket is not
					// consumed. If the item dose not have
					// a container item getContainerItem returns null which sets
					// the slot contents to null
					if (itemStacks[fuelSlotNumber].getCount() == 0) {
						itemStacks[fuelSlotNumber] = itemStacks[fuelSlotNumber]
								.getItem().getContainerItem(
										itemStacks[fuelSlotNumber]);
					}
				}
			}
		}
		if (inventoryChanged)
			markDirty();
		return burningCount;
	}

	/**
	 * Check if any of the input items are smeltable and there is sufficient
	 * space in the output slots
	 * 
	 * @return true if smelting is possible
	 */
	public boolean canSmelt() {
		return smeltItem(false);
	}

	/**
	 * Smelt an input item into an output slot, if possible
	 */
	private void smeltItem() {
		smeltItem(true);
	}

	/**
	 * checks that there is an item to be smelted in one of the input slots and
	 * that there is room for the result in the output slots If desired,
	 * performs the smelt
	 * 
	 * @param performSmelt
	 *            if true, perform the smelt. if false, check whether smelting
	 *            is possible, but don't change the inventory
	 * @return false if no items can be smelted, true otherwise
	 */
	protected boolean smeltItem(boolean performSmelt) {
		
		int smeltNum = -1;
		boolean canSmelt = false;
		boolean[] existItems = new boolean[recipe[0].length];
		
		for(int x = 0; x < existItems.length; x++) {
			existItems[x] = false;
		}
		
		recipe:
		for(int x = 0; x < recipe.length / 2; x++) {
			for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++) {
				if (itemStacks[inputSlot] != null) {
					for(int y = 0; y < recipe[x * 2].length; y++) {
						if(existItems[y] == true) {
							continue;
						}
						if (itemStacks[inputSlot].getItem()	== recipe[x*2][y].getItem()
								&& itemStacks[inputSlot].getCount() >= recipe[x*2][y].getCount()) {
							existItems[y] = true;
						}
					}
				}
			}
			for(int z = 0; z < existItems.length; z++) {
				if(existItems[z] == false) {
					continue recipe;
				}
			}
			smeltNum = x * 2;
			canSmelt = true;
		}
		
		if(!canSmelt) {
			return false;
		}

		for (int outputSlot = FIRST_OUTPUT_SLOT; outputSlot < FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT; outputSlot++) {
			if (itemStacks[outputSlot] != null) {
				if (itemStacks[outputSlot].getItem().getUnlocalizedName() == recipe[smeltNum + 1][outputSlot - FIRST_OUTPUT_SLOT].getItem().getUnlocalizedName() || 
						itemStacks[outputSlot].getCount() > getInventoryStackLimit() - recipe[smeltNum + 1][outputSlot - FIRST_OUTPUT_SLOT].getCount()) {
					return false;
				}
			}
		}

		if (!performSmelt) {
			return true;
		}
		
		for (int inputSlot = FIRST_INPUT_SLOT; inputSlot < FIRST_INPUT_SLOT + INPUT_SLOTS_COUNT; inputSlot++) {
			for(int y = 0; y < recipe[smeltNum].length; y++) {
				if (itemStacks[inputSlot].getItem() == recipe[smeltNum][y].getItem()) {
					itemStacks[inputSlot].setCount(itemStacks[inputSlot].getCount() - recipe[smeltNum][y].getCount());
				}
			}
			if (itemStacks[inputSlot].getCount() <= 0) {
				itemStacks[inputSlot] = null;
			}
		}
			
		for (int outputSlot = FIRST_OUTPUT_SLOT; outputSlot < FIRST_OUTPUT_SLOT + OUTPUT_SLOTS_COUNT; outputSlot++) {
			if (itemStacks[outputSlot] == null) {
				if(recipe[smeltNum + 1][outputSlot - FIRST_OUTPUT_SLOT] != null) {
					itemStacks[outputSlot] = new ItemStack(recipe[smeltNum + 1][outputSlot - FIRST_OUTPUT_SLOT].getItem(), recipe[smeltNum + 1][outputSlot - FIRST_OUTPUT_SLOT].getCount());
				}
			} else {
				itemStacks[outputSlot].setCount(itemStacks[outputSlot].getCount() +  recipe[smeltNum + 1][outputSlot - FIRST_OUTPUT_SLOT].getCount());
			}
		}
			
		markDirty();
		return true;
		
	}
	// returns the smelting result for the given stack. Returns null if the
	// given stack can not be smelted
	public ItemStack getSmeltingResultForItem(ItemStack stack) {
		return null;
	}

	// returns the number of ticks the given item will burn. Returns 0 if the
	// given item is not a valid fuel
	public short getItemBurnTime(ItemStack stack) {
		int burntime = 0;
		for(int x = 0; x < fuel.length; x++) {
			if (stack.getItem() == fuel[x]) {
				burntime = this.burnTime[x];
			}
		}
		return (short) MathHelper.clamp(burntime, 0, Short.MAX_VALUE);
	}

	// Gets the number of slots in the inventory
	@Override
	public int getSizeInventory() {
		return itemStacks.length;
	}

	// Gets the stack in the given slot
	@Override
	public ItemStack getStackInSlot(int i) {
		if (i > TOTAL_SLOTS_COUNT) {
			return null;
		}
		return itemStacks[i];
	}

	/**
	 * Removes some of the units from itemstack in the given slot, and returns
	 * as a separate itemstack
	 * 
	 * @param slotIndex
	 *            the slot number to remove the items from
	 * @param count
	 *            the number of units to remove
	 * @return a new itemstack containing the units removed from the slot
	 */
	@Override
	public ItemStack decrStackSize(int slotIndex, int count) {
		ItemStack itemStackInSlot = getStackInSlot(slotIndex);
		if (itemStackInSlot == null)
			return null;

		ItemStack itemStackRemoved;
		if (itemStackInSlot.getCount() <= count) {
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(slotIndex, null);
		} else {
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if (itemStackInSlot.getCount() == 0) {
				setInventorySlotContents(slotIndex, null);
			}
		}
		markDirty();
		return itemStackRemoved;
	}

	// overwrites the stack in the given slotIndex with the given stack
	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
		itemStacks[slotIndex] = itemstack;
		if (itemstack != null && itemstack.getCount() > getInventoryStackLimit()) {
			itemstack.setCount(getInventoryStackLimit());
		}
		markDirty();
	}

	// This is the maximum number if items allowed in each slot
	// This only affects things such as hoppers trying to insert items you need
	// to use the container to enforce this for players
	// inserting items via the gui
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	// Return true if the given player is able to use this block. In this case
	// it checks that
	// 1) the world tileentity hasn't been replaced in the meantime, and
	// 2) the player isn't too far away from the centre of the block
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (this.world.getTileEntity(xCoord, yCoord, zCoord) != this)
			return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(xCoord + X_CENTRE_OFFSET, yCoord
				+ Y_CENTRE_OFFSET, zCoord + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
	}

	// Return true if the given stack is allowed to be inserted in the given
	// slot
	// Unlike the vanilla furnace, we allow anything to be placed in the fuel
	// slots
	public boolean isItemValidForFuelSlot(ItemStack itemStack) {
		Item item = itemStack.getItem();
		for(int x = 0; x < fuel.length; x++) {
			if(item == fuel[x]) {
				return true;
			}
		}
		return false;
	}

	// Return true if the given stack is allowed to be inserted in the given
	// slot
	// Unlike the vanilla furnace, we allow anything to be placed in the fuel
	// slots
	public boolean isItemValidForInputSlot(ItemStack itemStack) {
		Item item = itemStack.getItem();
		for(int x = 0; x < recipe.length; x += 2) {
			for(int y = 0; y < recipe[x].length; y++) {
				if(item == recipe[x][y].getItem()) {
					return true;
				}
			}
		}
		return false;
	}

	// Return true if the given stack is allowed to be inserted in the given
	// slot
	// Unlike the vanilla furnace, we allow anything to be placed in the fuel
	// slots
	static public boolean isItemValidForOutputSlot(ItemStack itemStack) {
		return false;
	}

	// When the world loads from disk, the server needs to send the TileEntity
	// information to the client
	// it uses getDescriptionPacket() and onDataPacket() to do this
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		final int METADATA = 0;
		return new SPacketUpdateTileEntity(pos, METADATA, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	// will add a key for this container to the lang file so we can name it in
	// the GUI
	@Override
	public String getInventoryName() {
		return inventoryName;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	// standard code to look up what the human-readable name is
	public IChatComponent getDisplayName() {
		return this.hasCustomInventoryName() ? new ChatComponentText(
				this.getInventoryName()) : new ChatComponentTranslation(
				this.getInventoryName());
	}

	// Fields are used to send non-inventory information from the server to
	// interested clients
	// The container code caches the fields and sends the client any fields
	// which have changed.
	// The field ID is limited to byte, and the field value is limited to short.
	// (if you use more than this, they get cast down
	// in the network packets)
	// If you need more than this, or shorts are too small, use a custom packet
	// in your container instead.

	private static final byte COOK_FIELD_ID = 0;
	private static final byte FIRST_BURN_TIME_REMAINING_FIELD_ID = 1;
	private static final byte FIRST_BURN_TIME_INITIAL_FIELD_ID = FIRST_BURN_TIME_REMAINING_FIELD_ID
			+ (byte) FUEL_SLOTS_COUNT;
	private static final byte NUMBER_OF_FIELDS = FIRST_BURN_TIME_INITIAL_FIELD_ID
			+ (byte) FUEL_SLOTS_COUNT;

	public int getField(int id) {
		if (id == COOK_FIELD_ID)
			return cookTime;
		if (id >= FIRST_BURN_TIME_REMAINING_FIELD_ID
				&& id < FIRST_BURN_TIME_REMAINING_FIELD_ID + FUEL_SLOTS_COUNT) {
			return burnTimeRemaining[id - FIRST_BURN_TIME_REMAINING_FIELD_ID];
		}
		if (id >= FIRST_BURN_TIME_INITIAL_FIELD_ID
				&& id < FIRST_BURN_TIME_INITIAL_FIELD_ID + FUEL_SLOTS_COUNT) {
			return burnTimeInitialValue[id - FIRST_BURN_TIME_INITIAL_FIELD_ID];
		}
		System.err
				.println("Invalid field ID in TileInventorySmelting.getField:"
						+ id);
		return 0;
	}

	public void setField(int id, int value) {
		if (id == COOK_FIELD_ID) {
			cookTime = (short) value;
		} else if (id >= FIRST_BURN_TIME_REMAINING_FIELD_ID
				&& id < FIRST_BURN_TIME_REMAINING_FIELD_ID + FUEL_SLOTS_COUNT) {
			burnTimeRemaining[id - FIRST_BURN_TIME_REMAINING_FIELD_ID] = value;
		} else if (id >= FIRST_BURN_TIME_INITIAL_FIELD_ID
				&& id < FIRST_BURN_TIME_INITIAL_FIELD_ID + FUEL_SLOTS_COUNT) {
			burnTimeInitialValue[id - FIRST_BURN_TIME_INITIAL_FIELD_ID] = value;
		} else {
			System.err
					.println("Invalid field ID in TileInventorySmelting.setField:"
							+ id);
		}
	}

	public int getFieldCount() {
		return NUMBER_OF_FIELDS;
	}

	// -----------------------------------------------------------------------------------------------------------
	// The following methods are not needed for this example but are part of
	// IInventory so they must be implemented

	// Unused unless your container specifically uses it.
	// Return true if the given stack is allowed to go in the given slot
	@Override
	public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {
		return false;
	}

	/**
	 * This method removes the entire contents of the given slot and returns it.
	 * Used by containers such as crafting tables which return any items in their
	 * slots when you close the GUI
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (itemStack != null)
			setInventorySlotContents(slotIndex, null);
		return itemStack;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}
}