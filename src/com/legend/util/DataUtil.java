package com.legend.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;

import com.legend.model.BaseEntity;

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
	
	public static  String extractWhereStringIds(Set<? extends BaseEntity> baseEntitys) {
		String where = "";
		if(baseEntitys!=null&&baseEntitys.size()!=0){
			String temp = "" ;
			for(BaseEntity baseEntity: baseEntitys){
				temp += baseEntity.getId()+",";
			}
			temp = temp.substring(0, temp.lastIndexOf(','));
			where = "where id not in ( "+ temp +")";
		}
		return where;
	}
	
}
