package com.taebong.auth_server.annotaion

import com.taebong.auth_server.service.EncryptService
import org.apache.commons.lang3.reflect.FieldUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import java.lang.reflect.Modifier

@Aspect
@Component
class PasswordEncryptionAspect(private val encryptService: EncryptService) {
    @Around("execution(* com.taebong.auth_server.controller.*.*(..))")
    fun passwordEncryptionAspect(pjp: ProceedingJoinPoint): Any {
        pjp.args.forEach { fieldEncryption3(it) }
        return pjp.proceed()
    }

    private fun fieldEncryption(any: Any) {
        if (ObjectUtils.isEmpty(any)) {
            return
        }

        FieldUtils.getAllFieldsList(any.javaClass)
            .filter { (Modifier.isFinal(it.modifiers) && Modifier.isStatic(it.modifiers)).not() }
            .forEach {
                try {
                    val encryptionTarget = it.isAnnotationPresent(PasswordEncryption::class.java)
                    if (encryptionTarget.not()) {
                        return
                    }

                    val encryptionField = FieldUtils.readField(it, any, true)
                    if ((encryptionField is String).not()) {
                        return
                    }
                    val encrypted = encryptService.encrypt(encryptionField as String)
                    FieldUtils.writeField(it, any, encrypted)
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            }
    }

    private fun fieldEncryption2(any: Any) {
        if (ObjectUtils.isEmpty(any)) {
            return
        }

        FieldUtils.getAllFieldsList(any.javaClass)
            .filter { field ->
                !(Modifier.isFinal(field.modifiers) && Modifier.isStatic(field.modifiers))
            }
            .forEach label@ {
                try {
                    if (it.isAnnotationPresent(PasswordEncryption::class.java).not()) {
                        return@label
                    }
                    val encryptionField = FieldUtils.readField(it, any, true)

                    if (encryptionField is String) {
                        val encrypted = encryptService.encrypt(encryptionField)
                        FieldUtils.writeField(it, any, encrypted)
                    }
                } catch (e: Exception) {
                    throw RuntimeException("Error while encrypting field: ${it.name}", e)
                }
            }
    }

    private fun fieldEncryption3(any: Any) {
        if (ObjectUtils.isEmpty(any)) {
            return
        }

        FieldUtils.getAllFieldsList(any.javaClass)
            .filter { field ->
                !(Modifier.isFinal(field.modifiers) && Modifier.isStatic(field.modifiers))
            }
            .forEach { field ->
                val encryptionField = runCatching { FieldUtils.readField(field, any, true) }.getOrNull()
                if (encryptionField is String && field.isAnnotationPresent(PasswordEncryption::class.java)) {
                    val encrypted = runCatching { encryptService.encrypt(encryptionField) }.getOrNull()
                    if (encrypted != null) {
                        runCatching { FieldUtils.writeField(field, any, encrypted) }
                    }
                }
            }
    }
}
