package com.troll.telegramdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.troll.telegramdemo.project.*


class MainActivity : AppCompatActivity() {
    val message = "你好你好你好你好你好你好"
    private val key = "123111"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getJavaData()
//7ECA689F0D3389D9DEA66AE112E5CFD7ras
//        getData()


//        println("AES加密：${AESOperator.getInstance().encrypt(message,"SHhw4OoafE8yvSA9KGghIFpkwcnSP2r0")}")
//        println("AES加密：${AESOperator.getInstance().decrypt(pass,"SHhw4OoafE8yvSA9KGghIFpkwcnSP2r0")}")


        //创建密钥对
//        val keyMap = RSAUtils.createKeys(1024)
//        var publicKey = keyMap["publicKey"]
//        var privateKey = keyMap["privateKey"]
//val      publicKey1 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/nlTj7f3YlF9EDanzVSfrIQudJEiCSNCPpLKyzN8AVkAe5IxisWr4S6SvqcYcLaNhxgOMyutl+jGfKRwRRdBypMCgwXV/BJdUAxNzWpdgEY8bYq3UL0GBY8W9NhMDQ99XyUpU1GEEjlNJfiOEXkfvzkx39Tw+g/Y8wboNPR5RcQIDAQAB"

//        var privateKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK5UGtLoWjfdyb1FNjXI3WlOTL831YSrubX0cI7OIVk/67LNiV3Qc2h4T9dUOG2g5+R0c6vYE6jCMHdArRMpm1RzIzDctS51rpO84qfbqN6sxStA8nTJz2JxvdmrMmg/Oswx0Ug3fIiAPkEF5FrDcuU6s2bS18CZcoZRM+NDUC/7AgMBAAECgYA3nuIIFNZkKMFrQB/xvSrXpIHcKMZZVCkmot0e0ynUvg5HuEhxxEZuOKTQwTBX2hTi0TzuwTxUhdDM27fc+1NxcUp6EPl87CEprF5uBJdiaNTgOop7jSXk1tKf+67vrIACyPpgp/FNkN/b4dCNE5jx3x1TwYpA+n9kiSKvWb8jQQJBAOGRuoSfXQAzHQvCuR7kDdwl7CCvryLcHNmokOSGTBRq0dNWQ4NfNgoDxScV4tilYn1quv3JPxopFD3WxTAJW6sCQQDF2LleYjDilyKWrcAHwH5xFjwrnRMt00sefiXB6SlGKsGGc6cQy0zmv4fr4G4e+2EYVQzof5NKDM5OuiIovqzxAkEAh+3ujR8PxyAGH+Eu90uMgp6jparZClPEWvG2ps+7Jw6b9RPlyVPl17Ef7IbG1bEMIfHgTzEoApbAqOc0mtb40wJBAIWJNAR6bdhifhwJhhU1XaJiiZY+XJWPiaI5Lq7k+tC7RwfCw75YGb2dBNa2El2i+fP8Y8wBX56Zah5FD41EtWECQQDIOrj+GJ+EJhT9Gh2McGPeWDnnJ2siSBVNp1+F6GAHu7RBBxoaoUCPdSfsezq7V10BsgdT4XCAlVi3hYn08Fys"
//        println(" RSA加密  公钥: $publicKey1")
//        println(" RSA加密  公钥: $publicKey")
//        println(" RSA加密  私钥：$privateKey")
//        val str = "之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；。童子何知，躬逢胜饯。"
//        var doEncryptMD5ToString = MD5Util.doEncryptMD5ToString(str)
//        val encodedData = RSAUtils.getPublicEncrypt(str, RSAUtils.getPublicKey(publicKey))
//        val encodedData = "XN5ck734HSqYj+F3uYa0EYY8x9yMU4Ch3ULhNPaaLNhj4ruAj90Tc9ls4BeWgF4wdnnCrB22LKMxerAKCNoY5FARQRV9AP23z1uBoDBAiQqH+lPozHzt/y9du+QoWX37oTmItjMfpG7YDooC5kWTwfvbMTNpXgOttinyn7SqljAQDNIE99EQMlr2c5+1L6pSRNtyEDi1gXSbxi+aYPnMV2Mq1PtKYgdRzKC+qk50HV1wdDXYvIpi+04qqnAXA5SN+T+knNsB3wwLgKkJUO+bWEKW8t91cTtlL/12MpHsgYdCD2GObK+X1Y0kPvL2AT22xCsmZBuDf4mycAQC3bHOTQ=="
//        println(" RSA加密  密文：$encodedData")
//        println(" RSA加密  密文：${encodedData.toByteArray().size}")
//        val decodedData = RSAUtils.getPrivateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey))
//        println(" RSA加密  解密后文字: $decodedData")

        var str="测试测试测试测试测试测试测试测试测试测试测试测试测试"
//        var rsaPublicKey = MessageEncryptUtil.getRsaPublicKey()
         var rsaPublicKSting= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/nlTj7f3YlF9EDanzVSfrIQudJEiCSNCPpLKyzN8AVkAe5IxisWr4S6SvqcYcLaNhxgOMyutl+jGfKRwRRdBypMCgwXV/BJdUAxNzWpdgEY8bYq3UL0GBY8W9NhMDQ99XyUpU1GEEjlNJfiOEXkfvzkx39Tw+g/Y8wboNPR5RcQIDAQAB"
//        MessageEncryptUtil.getRsaPublicKey()
//         var rsaPublicKSting: String = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDuRiDC/7ycvTsVcRorTNtM+ZLv9tYDl3WrdAWboFvU4UYqyUvkoSxnfDlTPzBuJY1ZiBZLFhKelfNrHOZS4HnlTXlyjWIbhmZnt8gifOI2c7DsMtddOnCl+lA/lxpFDmlroOrUsngeLa64Q0ra0bir5h1DVObv/9r3o65ohJWQ5wIDAQAB"

//        println("公钥： ${MessageEncryptUtil.sendServiceData(str,rsaPublicKSting)}")
        val ss="I7q+JklWt0hXd0mh4iCpPY5kJVzKszf2l+a5O/1Rnq8CTWj/xSi2aeh/f0Z3E6HiQrN61n1Av2kDq+Fktr/R4tQcAEnKRt8uv9b9Yvts7KQV1hDJcm9I7vWBxdEbrAXCcWu+F0UoJYyYPTOOcmK5x2G8QmvKUIkwqWqdJAIoKFU=iofBVzrXaeH5SuGV5TzabhJFkNjeO/7JQE57mXQFX/nfgXIszinzumlBi/pK5hI2iHMuKh620uBVvC50sNkRDCyJUI0L4740CMEef2gs7eEW+pCqdjylqq/lEwHdkcN/xugXGagBQ8QE8XdImDIPOf92txsW4rNWi1VQK+lu+qg="
      println("解密后：${MessageEncryptUtil.getMessageContent(ss)}")



    }

}
