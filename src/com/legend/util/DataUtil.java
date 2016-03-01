package com.legend.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataUtil {
	
	public static Serializable ObjectCopy(Serializable src) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(src);
		oos.close();
		baos.close();
		
		byte[] objectArray = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(objectArray);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Serializable copy = (Serializable) ois.readObject();
		ois.close();
		bais.close();
		return copy;
	}
	
	
}
