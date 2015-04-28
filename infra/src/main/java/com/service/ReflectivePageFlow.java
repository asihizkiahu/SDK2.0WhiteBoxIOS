package com.service;

import com.util.genutil.GeneralUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by asih on 28/04/2015.
 */
public class ReflectivePageFlow {

    private ReflectivePageFlow(){
    }

    public static <T> void invoke(Class<T> targetClazz, T targetObj, List<String> methodNames){
        for(String methodName : methodNames){
            for(Method m : targetClazz.getMethods()){
                if(m.getName().equalsIgnoreCase(methodName)){
                    try {
                        m.invoke(targetObj, null);
                    } catch (Throwable t) {
                        GeneralUtils.handleError(
                                "Can't invoke method " + methodName + " in class " + targetClazz.getName(), t);
                    }
                }
            }
        }
    }

}