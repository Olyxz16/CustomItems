package customitemsapi.nbt;

import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTTagUtils
{


    public static int getNBTTagInt(ItemStack item, String tag)
    {
        var nmsStack = CraftItemStack.asNMSCopy(item);
        if(!nmsStack.hasTag())
        {
            return -1;
        }
        var compound = nmsStack.getTag();
        return compound.getInt(tag);
    }
    public static ItemStack setNBTTagInt(ItemStack item, String tag, int value)
    {
        var nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound;
        if(!nmsStack.hasTag())
        {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
        }
        compound = nmsStack.getTag();
        compound.setInt(tag, value);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    public static String getNBTTagString(ItemStack item, String tag)
    {
        var nmsStack = CraftItemStack.asNMSCopy(item);
        if(!nmsStack.hasTag())
        {
            return "";
        }
        var compound = nmsStack.getTag();
        return compound.getString(tag);
    }
    public static ItemStack setNBTTagString(ItemStack item, String tag, String value)
    {
        var nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound;
        if(!nmsStack.hasTag())
        {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
        }
        compound = nmsStack.getTag();
        compound.setString(tag, value);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

}

