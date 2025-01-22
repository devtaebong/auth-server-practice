package com.taebong.auth_server.transaction

import org.springframework.transaction.support.TransactionCallback
import org.springframework.transaction.support.TransactionOperations

fun <T> TransactionOperations.executeOrThrow(action: TransactionCallback<T>): T {
    return this.execute(action) ?: throw IllegalStateException("Transaction returned null unexpectedly")
}
