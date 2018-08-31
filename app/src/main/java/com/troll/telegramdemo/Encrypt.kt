package com.troll.telegramdemo


/**
 * @author TangPeng
 * @since 2018/8/28
 */
fun getAesMess(message: String, publicKey: String) {
    val aesPassword = "123"
    var aes = AESCrypt.encrypt(aesPassword, message)  //AES加密的消息密文




}