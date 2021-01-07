package com.wnsgml972.strada.security.user.domain

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.SignatureVerificationException
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@ConstructorBinding
@ConfigurationProperties("jwt")
class JwtProperties(
    val keyPair: List<JwtKeyPair>,
    val header: String,
    val headerPrefix: String
) {

    fun decodeJWT(token: String) = keyPair.mapNotNull { pair ->
        try {
            doDecodeJWT(token, pair.publicKey, pair.privateKey)
        } catch (e: SignatureVerificationException) {
            null
        }
    }

    private fun doDecodeJWT(
        token: String,
        jwtPublicKey: RSAPublicKey,
        jwtPrivateKey: RSAPrivateKey
    ) = JWT.require(Algorithm.RSA256(jwtPublicKey, jwtPrivateKey))
        .build()
        .verify(token.substring(headerPrefix.length + 1))

    companion object {
        const val ISSUER: String = "strada"
        const val AUDIENCE: String = "strada-api"
    }
}

class JwtKeyPair(
    public: String,
    private: String
) {
    val privateKey: RSAPrivateKey
    val publicKey: RSAPublicKey

    init {
        this.privateKey = createPrivateKey(private)
        this.publicKey = createPublicKey(public)
    }

    private fun createPrivateKey(private: String): RSAPrivateKey {
        val keyFactory = KeyFactory.getInstance("RSA")
        val pkcs8EncodedKeySpec = PKCS8EncodedKeySpec(Base64.getDecoder().decode(private))
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec) as RSAPrivateKey
    }

    private fun createPublicKey(public: String): RSAPublicKey {
        val keyFactory = KeyFactory.getInstance("RSA")
        val x509EncodedKeySpec = X509EncodedKeySpec(Base64.getDecoder().decode(public))
        return keyFactory.generatePublic(x509EncodedKeySpec) as RSAPublicKey
    }
}
