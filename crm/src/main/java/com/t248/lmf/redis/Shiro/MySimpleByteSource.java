package com.t248.lmf.redis.Shiro;

import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;
public class MySimpleByteSource implements ByteSource, Serializable{

    private static final long serialVersionUID = -6814382603612799610L;
    private volatile byte[] bytes;
    private String cachedHex;
    private String cachedBase64;

         public MySimpleByteSource() {

         }

         public MySimpleByteSource(String string) {
             this.bytes = CodecSupport.toBytes(string);
         }

         public void setBytes(byte[] bytes) {
             this.bytes = bytes;
         }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public String toHex() {
        return null;
    }

    @Override
    public String toBase64() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    public static ByteSource of(String string) {
             return new MySimpleByteSource(string);
         }
}
