package com.wnsgml972.strada.api.base

import java.io.Serializable

interface ValueObject : Serializable

abstract class AbstractValueObject : ValueObject {

    protected abstract fun equalProperties(other: Any): Boolean

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false

        return equalProperties(other)
    }

    override fun hashCode(): Int = System.identityHashCode(this)
}
