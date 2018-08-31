package com.troll.telegramdemo.project

import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * @author TangPeng
 * @since 2018/8/30
 */
object EncryptUtils {
    private const val CODED_FORMAT = "utf-8"  //UTF-8编码格式

    private const val AES_IV_PARAMETER = "0000000000000000"// AES加密偏移量,可自行修改
    private const val AES_TRANSFORMATION = "AES/CBC/PKCS5Padding"// AES加密transformation
    private const val AES_KEY = "AES"// AES加密key
    private const val RSA_KEY = "RSA"
    private const val RSA_ALGORITHM = "RSA/ECB/PKCS1Padding"

    private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    @Throws(Exception::class)
    fun createRSAKeys(): Map<String, String> {
        val kpg: KeyPairGenerator = KeyPairGenerator.getInstance(RSA_KEY) //为RSA算法创建一个KeyPairGenerator对象
        kpg.initialize(1024)  //初始化KeyPairGenerator对象,密钥长度可变
        val keyPair = kpg.generateKeyPair()   //生成密匙对
        val publicKey = keyPair.public    //得到公钥
        val publicKeyStr = Base64Util.encode2String(publicKey.encoded)
        val privateKey = keyPair.private  //得到私钥
        val privateKeyStr = Base64Util.encode2String(privateKey.encoded)
        val keyPairMap = HashMap<String, String>()
        keyPairMap["publicKey"] = publicKeyStr
        keyPairMap["privateKey"] = privateKeyStr
        return keyPairMap
    }

    /**
     * 得到公钥
     */
    @Throws(Exception::class)
    fun getRSAPublicKey(publicKey: String): RSAPublicKey {
        //通过X509编码的Key指令获得公钥对象
        val keyFactory = KeyFactory.getInstance(RSA_KEY)
        var x509KeySpec = X509EncodedKeySpec(Base64Util.decode2Byte(publicKey.toByteArray(charset(CODED_FORMAT))))
        return keyFactory.generatePublic(x509KeySpec) as RSAPublicKey
    }

    /**
     * 得到私钥
     */
    @Throws(Exception::class)
    fun getRSAPrivateKey(privateKey: String): RSAPrivateKey {
        //通过PKCS#8编码的Key指令获得私钥对象
        val keyFactory = KeyFactory.getInstance(RSA_KEY)
        var keySpec = PKCS8EncodedKeySpec(Base64Util.decode2Byte(privateKey.toByteArray(charset(CODED_FORMAT))))
        return keyFactory.generatePrivate(keySpec) as RSAPrivateKey
    }

    /**
     * 公钥加密
     */
    fun getPublicEncrypt(data: String, publicKey: RSAPublicKey): String {
        val cipher = Cipher.getInstance(RSA_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return Base64Util.encode2String(doRsaSplitCode(cipher, Cipher.ENCRYPT_MODE, data.toByteArray(charset(CODED_FORMAT)), publicKey.modulus.bitLength()))
    }

    /**
     * 私钥解密
     */
    fun getPrivateDecrypt(data: String, privateKey: RSAPrivateKey): String {
        val cipher = Cipher.getInstance(RSA_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        return String(doRsaSplitCode(cipher, Cipher.DECRYPT_MODE, Base64Util.decode2Byte(data.toByteArray(charset(CODED_FORMAT))), privateKey.modulus.bitLength()), charset(CODED_FORMAT))
    }

    /**
     * 处理RSA长度问题
     */
    private fun doRsaSplitCode(cipher: Cipher, opmode: Int, datas: ByteArray, keySize: Int): ByteArray {
        var maxBlock: Int = if (opmode == Cipher.DECRYPT_MODE) {
            keySize / 8
        } else {
            keySize / 8 - 11
        }
        val out = ByteArrayOutputStream()
        var offSet = 0
        var buff: ByteArray
        var i = 0
        while (datas.size > offSet) {
            buff = if (datas.size - offSet > maxBlock) {
                cipher.doFinal(datas, offSet, maxBlock)
            } else {
                cipher.doFinal(datas, offSet, datas.size - offSet)
            }
            out.write(buff, 0, buff.size)
            i++
            offSet = i * maxBlock
        }
        val resultData = out.toByteArray()
        try {
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return resultData
    }

    /**
     * 产生长度32的随机AES的密钥
     */
    fun getAesSecretKey(): String {
        val str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890"
        val random = Random()
        val sb = StringBuffer()
        for (i in 0 until 32) {
            val number = random.nextInt(62)
            sb.append(str[number])
        }
        return sb.toString()
    }

    /**
     * AES加密
     */
    @Throws(Exception::class)
    fun doAesEncrypt(content: String, key: String): String {
        val cipher = Cipher.getInstance(AES_TRANSFORMATION)
        val keySpec = SecretKeySpec(key.toByteArray(), AES_KEY)
        val iv = IvParameterSpec(AES_IV_PARAMETER.toByteArray())// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv)
        val encrypted = cipher.doFinal(content.toByteArray(charset(CODED_FORMAT)))
        return Base64.encodeToString(encrypted, Base64.NO_WRAP)
    }


    /**
     * AES 解密
     */
    @Throws(Exception::class)
    fun doAesDecrypt(content: String, key: String): String? {
        val raw = key.toByteArray(charset("ASCII"))
        val keySpec = SecretKeySpec(raw, "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = IvParameterSpec(AES_IV_PARAMETER.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv)
        val encrypted1 = Base64.decode(content, Base64.NO_WRAP)// 先用base64解密
        val original = cipher.doFinal(encrypted1)
        return String(original)

    }


    /**
     * MD5加密
     */
    fun doEncryptMD5ToString(data: String?): String {
        return if (data == null || data.isEmpty()) "" else doEncryptMD5ToString(data.toByteArray())
    }

    private fun doEncryptMD5ToString(data: ByteArray): String {
        return bytes2HexString(encryptMD5(data))
    }

    private fun encryptMD5(data: ByteArray): ByteArray? {
        return hashTemplate(data, "MD5")
    }

    private fun bytes2HexString(bytes: ByteArray?): String {
        if (bytes == null) return ""
        val len = bytes.size
        if (len <= 0) return ""
        val ret = CharArray(len shl 1)
        var i = 0
        var j = 0
        while (i < len) {
            ret[j++] = HEX_DIGITS[bytes[i].toInt() shr 4 and 0x0f]
            ret[j++] = HEX_DIGITS[bytes[i].toInt() and 0x0f]
            i++
        }
        return String(ret)
    }

    private fun hashTemplate(data: ByteArray?, algorithm: String): ByteArray? {
        if (data == null || data.isEmpty()) return null
        return try {
            val md = MessageDigest.getInstance(algorithm)
            md.update(data)
            md.digest()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        }

    }

    /**
     * 分割字符串
     */
    fun substring(str: String, beginIndex: Int, endIndex: Int): String {
        if (beginIndex > str.length)
            return ""
        return if (endIndex > str.length) {
            str.substring(beginIndex, str.length)
        } else {
            str.substring(beginIndex, endIndex)
        }
    }
}