package com.troll.telegramdemo.project

import android.util.Base64

/**
 * @author TangPeng
 * @since 2018/8/29
 */
object Base64Util {
    /**
     * Base64加密
     */
    fun encode2Byte(input: ByteArray): ByteArray {
        return Base64.encode(input, Base64.NO_WRAP)
    }

    fun encode2String(input: ByteArray): String {
        return Base64.encodeToString(input, Base64.NO_WRAP)
    }

    /**
     * Base64解密
     */
    fun decode2Byte(input: ByteArray): ByteArray {
        return Base64.decode(input, Base64.NO_WRAP)
    }

    fun decode2String(input: ByteArray): String {
        return String(decode2Byte(input))
    }
}