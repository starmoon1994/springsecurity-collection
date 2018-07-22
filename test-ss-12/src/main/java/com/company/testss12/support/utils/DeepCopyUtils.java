package com.company.testss12.support.utils;

import java.io.*;
import java.util.List;

/**
 * 深拷贝工具
 * 提供可靠的深拷贝
 * Created by hxy-company on 2018/3/23.
 */
public class DeepCopyUtils {

    /**
     * List相关的深拷贝工具
     * @param src 源list
     * @param <T> 泛型参数
     * @return dest 拷贝好的list
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> listDeepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}
