package com.sports.filip.widget.clip;

/**
 * 作者：王鹏飞
 * 创建时间：2016/3/5 14:58
 * 邮箱：15291967179@163.com
 * 描述：
 */
public class ClipUtil
{
    private static byte[] clipPhotoByte;

    public static byte[] getClipPhotoByte() {
        return clipPhotoByte;
    }

    public static void setClipPhotoByte(byte[] clipPhotoByte) {
        ClipUtil.clipPhotoByte = clipPhotoByte;
    }
}
