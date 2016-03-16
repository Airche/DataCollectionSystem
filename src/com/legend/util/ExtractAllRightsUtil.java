package com.legend.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.legend.service.RightService;

/**
 *	提取所有权限的工具类 
 *
 */
public class ExtractAllRightsUtil {

		public static void main(String[] args) throws URISyntaxException, ClassNotFoundException{
			ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
			RightService rightService = (RightService) ctx.getBean("rightService");
			ClassLoader loader = ExtractAllRightsUtil.class.getClassLoader();
			URL url = loader.getResource("com/legend/action");
			File dir = new File(url.toURI());
			File[] files = dir.listFiles();
			String fileName = null;
			for(File file : files){
				fileName = file.getName();
				if(fileName.endsWith(".class")&&!fileName.equals("BaseAction.class")){
					processAction(rightService,fileName);
				}
			}
		}

		
		private static void processAction(RightService rightService,String fileName) throws ClassNotFoundException {
			String url = null;
			String pkgName="com.legend.action";
			String simpleClassName = fileName.substring(0,fileName.indexOf("."));
			String className = pkgName + "." + simpleClassName;
			Class clazz = Class.forName(className);
			Method[] methods = clazz.getDeclaredMethods();
			Class retType = null;
			String methodName = null;
			Class[] paramType = null;
			for(Method m : methods){
				retType = m.getReturnType();
				methodName = m.getName();
				paramType = m.getParameterTypes();
				if(retType == String.class 
						&& (paramType==null || paramType.length==0)
						&& Modifier.isPublic(m.getModifiers())
						&&!methodName.startsWith("get")
						&&!methodName.startsWith("set")){
					if(!methodName.equals("execute")){
						url = "/"+simpleClassName+"_"+methodName;
					}else{
						url = "/"+simpleClassName;
					}
					rightService.appendRightByURL(url);
				}
			}
		}
}
