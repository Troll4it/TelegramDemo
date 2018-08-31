package com.troll.telegramdemo.project

import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

/**
 * @author TangPeng
 * @since 2018/8/29
 */
object RSAEncryptUtil {





    /**
     * 公钥加密
     */
    @Throws(Exception::class)
    fun publicEncrypt(content: ByteArray, publicKey: PublicKey): ByteArray {
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(content)
    }

    /**
     * 私钥解密
     */
    @Throws(Exception::class)
    fun privateDecrypt(content: ByteArray, privateKey: PrivateKey): ByteArray {
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return cipher.doFinal(content)
    }

    /**
     * 将Base64编码后的公钥转换成PublicKey对象
     */
    @Throws(Exception::class)
    fun string2PublicKey(pubStr: String): PublicKey {
        val keyBytes = Base64Util.decode2Byte(pubStr.toByteArray())
        val keySpec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(keySpec)
    }


    /**
     * Base64编码后的私钥转换成PrivateKey对象
     */
    @Throws(Exception::class)
    fun string2PrivateKey(priStr: String): PrivateKey {
        val keyBytes = Base64Util.decode2Byte(priStr.toByteArray())
        val keySpec = PKCS8EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePrivate(keySpec)
    }

    /**
     * 获取公钥
     */
     fun getPublicKey(keyPair: KeyPair): String {
        val publicKey = keyPair.public
        val bytes = publicKey.encoded
        return String(Base64Util.encode2Byte(bytes))
    }

    /**
     * 获取私钥
     */
     fun getPrivateKey(keyPair: KeyPair): String {
        val privateKey = keyPair.private
        val bytes = privateKey.encoded
        return String(Base64Util.encode2Byte(bytes))
    }

    /**
     * 生成秘钥对
     */
    @Throws(Exception::class)
     fun getKeyPair(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        return keyPairGenerator.generateKeyPair()
    }
}