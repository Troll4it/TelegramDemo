package com.troll.telegramdemo.project

/**
 * @author TangPeng
 * @since 2018/8/31
 */
object MessageEncryptUtil {


    private var rsaPublicKSting: String = ""
    private var rsaPrivateKeySting: String = ""
    /**
     * 获取RSA的公钥
     */
    fun getRsaPublicKey(): String {
        var rsaKeys = EncryptUtils.createRSAKeys()
        rsaPublicKSting = rsaKeys["publicKey"].toString()
        rsaPrivateKeySting = rsaKeys["privateKey"].toString()
        return rsaPublicKSting
        return ""
    }

    /**
     * 发送服务器的数据
     */
    fun sendServiceData(content: String,publicKey:String): String {
        //获取AES的密钥
        val aesSecretKey = EncryptUtils.getAesSecretKey()
        //对消息体进行AES加密
        val aesEncryptStr = EncryptUtils.doAesEncrypt(content, aesSecretKey)
        //对消息体进行MD5加密
        val contentMd5 = EncryptUtils.doEncryptMD5ToString(content)
        //将MD5之后的消息体跟AES密钥合并
        val secretStr = "$contentMd5$aesSecretKey"
        //合并后的字符串进行RSA加密
        val resSecretMess = EncryptUtils.getPublicEncrypt(secretStr, EncryptUtils.getRSAPublicKey(publicKey))
        return "$aesEncryptStr$resSecretMess"
    }

    /**
     * 返回消息content String
     */
    fun getMessageContent(encryptStr: String): String {
        val rsaMess = EncryptUtils.substring(encryptStr, encryptStr.length-172, encryptStr.length)
        val aesMess = EncryptUtils.substring(encryptStr, 0, encryptStr.length-172)
        val decryptStr = EncryptUtils.getPrivateDecrypt(rsaMess, EncryptUtils.getRSAPrivateKey(rsaPrivateKeySting))
        val md5Str = EncryptUtils.substring(decryptStr, 0, 32)
        val aesKey = EncryptUtils.substring(decryptStr, 32, decryptStr.length)
        val content = EncryptUtils.doAesDecrypt(aesMess, aesKey)
        val md5content = EncryptUtils.doEncryptMD5ToString(content)
        //判断消息是否窜改
        return if (md5content == md5Str) {
            content.toString()
        } else {
            ""
        }
    }
}

