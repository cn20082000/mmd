package com.cn.mmd.util

import com.cn.mmd.model.other.TokenModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.codec.binary.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object Security {

    private const val KEY = "makemE@4792Disgu"
    private val iv: ByteArray =
        arrayOf<Byte>(-82, -5, 97, -122, 45, -81, 90, -94, 106, 105, -75, -14, -85, 33, 59, -57).toByteArray()

    fun encrypt(token: TokenModel): String {
        val mapper = ObjectMapper().findAndRegisterModules()
        val json = mapper.writeValueAsString(token)

        val ivSpec = IvParameterSpec(iv)
        val key = SecretKeySpec(KEY.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)

        return String(Base64.encodeBase64(cipher.doFinal(json.toByteArray())))
    }

    fun decrypt(token: String): TokenModel? {
        val icSpec = IvParameterSpec(iv)
        val key = SecretKeySpec(KEY.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, icSpec)

        val mapper = ObjectMapper().findAndRegisterModules()

        return try {
            val json = String(cipher.doFinal(Base64.decodeBase64(token)))
            mapper.readValue(json, TokenModel::class.java)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun hash(username: String, password: String): String {
        val keySpec = PBEKeySpec(username.toCharArray() + password.toCharArray(), iv, 65536, 256)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        val hash = factory.generateSecret(keySpec).encoded

        return String(Base64.encodeBase64(hash))
    }
}