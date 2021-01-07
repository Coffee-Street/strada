package com.wnsgml972.strada.api.base

interface PersistenceObject : ValueObject {

    var persisted: Boolean

    @JvmDefault
    fun onPostPersist() {
        persisted = true
    }

    @JvmDefault
    fun onPostLoad() {
        persisted = true
    }

    fun makeTransient() {
        persisted = false
    }
}

abstract class AbstractPersistenceObject : AbstractValueObject(), PersistenceObject {

    override var persisted: Boolean = false
}
