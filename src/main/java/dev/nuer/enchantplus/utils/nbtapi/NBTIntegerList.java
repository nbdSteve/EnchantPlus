package dev.nuer.enchantplus.utils.nbtapi;

import dev.nuer.enchantplus.utils.nbtapi.utils.nmsmappings.ClassWrapper;
import dev.nuer.enchantplus.utils.nbtapi.utils.nmsmappings.ReflectionMethod;

import java.lang.reflect.InvocationTargetException;

/**
 * Integer implementation for NBTLists
 * 
 * @author tr7zw
 *
 */
public class NBTIntegerList extends NBTList<Integer> {

	protected NBTIntegerList(NBTCompound owner, String name, NBTType type, Object list) {
		super(owner, name, type, list);
	}

	@Override
	protected Object asTag(Integer object) {
		try {
			return ClassWrapper.NMS_NBTTAGINT.getClazz().getConstructor(int.class).newInstance(object);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new NbtApiException("Error while wrapping the Oject " + object + " to it's NMS object!", e);
		}
	}

	@Override
	public Integer get(int index) {
		try {
			Object obj = ReflectionMethod.LIST_GET.run(listObject, index);
			return Integer.valueOf(obj.toString());
		} catch (NumberFormatException nf) {
			return 0;
		} catch (Exception ex) {
			throw new NbtApiException(ex);
		}
	}

}
