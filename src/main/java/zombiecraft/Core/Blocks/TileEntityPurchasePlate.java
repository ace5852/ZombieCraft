package zombiecraft.Core.Blocks;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import zombiecraft.Core.Buyables;
import zombiecraft.Core.GameLogic.ZCGame;
import zombiecraft.Forge.ZCServerTicks;



public class TileEntityPurchasePlate extends TileEntity
{
	
	public int itemIndex = 0;
	
	public int cycleDelay = 60;
	public boolean cycleItems = false;
	public int cycleIndex = 0;
	public int cycleCurDelay = cycleDelay;
	
	
	//Unique to tile entity rendering stuff
	public float startOffset;
	public float angle = 0F;
	
	public int watch_itemIndex = 0;
	public int watch_delay_itemIndex = 0;

    public TileEntityPurchasePlate()
    {
    	Random rand = new Random();
    	startOffset = (float)(Math.random() * Math.PI * 2.0D);
    	angle = rand.nextInt(360);
    	cycleIndex = rand.nextInt(Buyables.count());
    	
    	if (cycleItems) itemIndex = cycleIndex;
    }
    
    public void onClicked() {
    	if (cycleItems) {
    		cycleItems = false;
    		itemIndex = 0;
    	} else {
    		itemIndex++;
    		
    		if (itemIndex >= Buyables.items.size()) {
    			itemIndex = cycleIndex = 0;
    			cycleItems = true;
    			cycleCurDelay = cycleDelay;
    		}
    	}
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
    	if (!this.worldObj.isRemote) {
	    	if (cycleItems) {
		    	if (cycleCurDelay > 0) {
		    		cycleCurDelay--;
		    	}
		    	
		    	if (cycleCurDelay == 0) {
		    		cycleCurDelay = cycleDelay;
		    		cycleIndex++;
		    		
		    		
		    		if (cycleIndex >= Buyables.count()) {
		    			cycleIndex = 0;
		    		}
		    		itemIndex = cycleIndex;
		    		
		    	}
	    	}
	    	
	    	watchVariables();
    	}

        super.updateEntity();
        
    }
    
    public void watchVariables() {
    	
    	if (itemIndex != watch_itemIndex || watch_delay_itemIndex == 0) {
    		watch_delay_itemIndex = ZCGame.instance().packetUpdateDelay;
    		//ServerTickHandler.zcGame.updateTileInfo(this, itemIndex);
    		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    		//ZCServerTicks.sendPacketToAll(this.getDescriptionPacket());
    	}
    	
    	if (watch_delay_itemIndex > 0) watch_delay_itemIndex--;
    	watch_itemIndex = itemIndex;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.itemIndex = par1NBTTagCompound.getInteger("itemIndex");
        this.cycleCurDelay = par1NBTTagCompound.getInteger("cycleCurDelay");
        cycleItems = par1NBTTagCompound.getBoolean("cycleItems");
        
        //this.delay = par1NBTTagCompound.getShort("Delay");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("itemIndex", this.itemIndex);
        par1NBTTagCompound.setInteger("cycleCurDelay", this.cycleCurDelay);
        par1NBTTagCompound.setBoolean("cycleItems", cycleItems);
        //par1NBTTagCompound.setShort("Delay", (short)this.delay);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    	this.readFromNBT(pkt.func_148857_g());
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, var1);
    }
}
