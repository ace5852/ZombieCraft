package zombiecraft.Core.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zombiecraft.Core.GameLogic.ZCGame;

public class BlockWallPlaceable extends BlockFence
{
    public BlockWallPlaceable()
    {
        super("barrier", Material.wood);
    }

    public BlockWallPlaceable(int par1, Material par3Material)
    {
        super("barrier", par3Material);
    }
    
    @Override
    public IIcon getIcon(int par1, int par2)
    {
        return Blocks.stone.getIcon(par1, par2);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
    	if (ZCGame.instance() == null || ZCGame.instance().mapMan.doorNoClip) return null;
    	
        boolean var5 = this.canConnectFenceTo(par1World, par2, par3, par4 - 1);
        boolean var6 = this.canConnectFenceTo(par1World, par2, par3, par4 + 1);
        boolean var7 = this.canConnectFenceTo(par1World, par2 - 1, par3, par4);
        boolean var8 = this.canConnectFenceTo(par1World, par2 + 1, par3, par4);
        float var9 = 0.075F;
        float var10 = 0.925F;
        float var11 = 0.075F;
        float var12 = 0.925F;
        
        

        if (var5)
        {
            var11 = 0.0F;
        }

        if (var6)
        {
            var12 = 1.0F;
        }

        if (var7)
        {
            var9 = 0.0F;
        }

        if (var8)
        {
            var10 = 1.0F;
        }

        return AxisAlignedBB.getBoundingBox((double)((float)par2 + var9), (double)par3, (double)((float)par4 + var11), (double)((float)par2 + var10), (double)((float)par3 + 1.5F), (double)((float)par4 + var12));
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        boolean var5 = this.canConnectFenceTo(par1IBlockAccess, par2, par3, par4 - 1);
        boolean var6 = this.canConnectFenceTo(par1IBlockAccess, par2, par3, par4 + 1);
        boolean var7 = this.canConnectFenceTo(par1IBlockAccess, par2 - 1, par3, par4);
        boolean var8 = this.canConnectFenceTo(par1IBlockAccess, par2 + 1, par3, par4);
        float var9 = 0.075F;
        float var10 = 0.925F;
        float var11 = 0.075F;
        float var12 = 0.925F;

        if (var5)
        {
            var11 = 0.0F;
        }

        if (var6)
        {
            var12 = 1.0F;
        }

        if (var7)
        {
            var9 = 0.0F;
        }

        if (var8)
        {
            var10 = 1.0F;
        }

        this.setBlockBounds(var9, 0.0F, var11, var10, 1.0F, var12);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	//System.out.println("BUY MENU CODE FIX ATTEMPT onEntityCollidedWithBlock");
    	if (!world.isRemote) {
    		//if (entity instanceof EntityPlayer) ZCServerTicks.zcGame.triggerBuyMenu(entity, i, j, k, -1);
    	}
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 0;
    }

    /**
     * Returns true if the specified block can be connected by a fence
     */
    public boolean canConnectFenceTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        Block var5 = par1IBlockAccess.getBlock(par2, par3, par4);

        if (var5 != this && var5 != Blocks.fence_gate)
        {
            //Block var6 = Block.blocksList[var5];
            return var5 != null && var5.getMaterial().isOpaque() && var5.renderAsNormalBlock() ? true : false;
        }
        else
        {
            return true;
        }
    }
}
