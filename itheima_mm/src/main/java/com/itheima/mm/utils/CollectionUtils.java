package com.itheima.mm.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 集合工具类
 */
public class CollectionUtils {

    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection){
        return !isNotEmpty(collection);
    }

    /**
     * 判断集合是否不为空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection){
        return collection != null && collection.size() > 0;
    }


}
