package jb.service;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

@Extension
public class StrExtension {

    public static String palindrome(@This String thiz) {
        StringBuilder stringBuffer = new StringBuilder(thiz.length());
        thiz.chars().forEach(stringBuffer::append);
        return stringBuffer.toString();
    }

}
