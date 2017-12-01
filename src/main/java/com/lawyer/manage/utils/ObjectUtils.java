package com.lawyer.manage.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqiang on 2017/6/19.
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    public static Map<String, Object> transObjectToMap(Object object) throws IllegalArgumentException, IllegalAccessException,
            NoSuchMethodException, SecurityException, InvocationTargetException {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
            String type = fields[i].getGenericType().toString(); // 获取属性的类型

            if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                Method m = object.getClass().getMethod("get" + name);
                String value = (String) m.invoke(object); // 调用getter方法获取属性值
                resultMap.put(name, value);
            }

            if (type.equals("class java.lang.Integer")) {
                Method m = object.getClass().getMethod("get" + name);
                Integer value = (Integer) m.invoke(object);
                resultMap.put(name, value);
            }
            if (type.equals("class java.lang.Long")) {
                Method m = object.getClass().getMethod("get" + name);
                Long value = (Long) m.invoke(object);
                resultMap.put(name, value);
            }

            if (type.equals("class java.util.Date")) {
                Method m = object.getClass().getMethod("get" + name);
                Date value = (Date) m.invoke(object);
                resultMap.put(name, value);
            }
        }
        // 返回Map<String,object>
        return resultMap;
    }

    public static Map<String, Object> transObject2Map(Object object)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String type = fields[i].getGenericType().toString();
            if (type.equals("class java.lang.String")) {
                Method method = object.getClass().getMethod("get" + name);
                String value = (String) method.invoke(object);
                resultMap.put(name, value);
            } else if (type.equals("class java.lang.Long")) {
                Method method = object.getClass().getMethod("get" + name);
                Long value = (Long) method.invoke(object);
                resultMap.put(name, value);
            } else if (type.equals("class java.lang.Integer")) {
                Method m = object.getClass().getMethod("get" + name);
                Integer value = (Integer) m.invoke(object);
                resultMap.put(name, value);
            } else if (type.equals("class java.util.Date")) {
                Method m = object.getClass().getMethod("get" + name);
                Date value = (Date) m.invoke(object);
                resultMap.put(name, value);
            }

        }
        return resultMap;
    }

}
