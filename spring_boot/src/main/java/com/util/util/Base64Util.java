package com.util.util;

import java.io.ByteArrayOutputStream;
import com.alibaba.druid.util.Base64;

public class Base64Util {
    public static String encode(ByteArrayOutputStream outputStream) {
        return Base64.byteArrayToBase64(outputStream.toByteArray());
    }

}
