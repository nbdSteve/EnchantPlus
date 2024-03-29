package dev.nuer.enchantplus.utils.nbtapi.utils.nmsmappings;

import dev.nuer.enchantplus.utils.nbtapi.NbtApiException;
import dev.nuer.enchantplus.utils.nbtapi.utils.MinecraftVersion;

import java.lang.reflect.Constructor;
import java.util.logging.Level;

import static dev.nuer.enchantplus.utils.nbtapi.utils.MinecraftVersion.logger;

/**
 * This Enum wraps Constructors for NMS classes
 * 
 * @author tr7zw
 *
 */
@SuppressWarnings("javadoc")
public enum ObjectCreator {
    NMS_NBTTAGCOMPOUND(null, null, ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz()),
    NMS_BLOCKPOSITION(null, null, ClassWrapper.NMS_BLOCKPOSITION.getClazz(), int.class, int.class, int.class),
    NMS_COMPOUNDFROMITEM(MinecraftVersion.MC1_11_R1, null, ClassWrapper.NMS_ITEMSTACK.getClazz(), ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz()),
    ;

    private Constructor<?> construct;
    private Class<?> targetClass;

    ObjectCreator(MinecraftVersion from, MinecraftVersion to, Class<?> clazz, Class<?>... args){
       if(from != null && MinecraftVersion.getVersion().getVersionId() < from.getVersionId())
    	   return;
       if(to != null && MinecraftVersion.getVersion().getVersionId() > to.getVersionId())
    	   return;
    	try{
        	this.targetClass = clazz;
            construct = clazz.getDeclaredConstructor(args);
            construct.setAccessible(true);
        }catch(Exception ex){
            logger.log(Level.SEVERE, "Unable to find the constructor for the class '" + clazz.getName() + "'", ex);
        }
    }
    
    /**
     * Creates an Object instance with given args
     * 
     * @param args
     * @return Object created
     */
    public Object getInstance(Object... args){
        try{
            return construct.newInstance(args);
        }catch(Exception ex){
        	throw new NbtApiException("Exception while creating a new instance of '" + targetClass + "'", ex);
        }
    }
    
}
