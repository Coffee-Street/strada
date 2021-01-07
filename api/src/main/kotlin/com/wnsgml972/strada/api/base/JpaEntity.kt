package com.wnsgml972.strada.api.base

import org.hibernate.Hibernate
import java.io.Serializable
import javax.persistence.MappedSuperclass
import javax.persistence.PostLoad
import javax.persistence.PostPersist

interface JpaEntity<TId: Serializable>: PersistenceObject {

    var id: TId?

    fun resetIdentifier() {
        id = null
        makeTransient()
    }
}

@MappedSuperclass
abstract class AbstractJpaEntity<TId: Serializable>: AbstractPersistenceObject(), JpaEntity<TId> {

    @PostPersist
    override fun onPostPersist() {
        persisted = true
    }

    @PostLoad
    override fun onPostLoad() {
        persisted = true
    }

    override fun equals(other: Any?): Boolean {
        val isSameType = other != null && (Hibernate.unproxy(this)).javaClass == (Hibernate.unproxy(other)).javaClass

        if (isSameType) {
            val entity = other as? JpaEntity<*>
            return hasSameNonDefaultIdAs(id, entity) ||
                (!persisted || !(entity?.persisted ?: false) && hasSameBusinessSignature<TId>(entity))
        }
        return false
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: System.identityHashCode(this)
    }

    private fun <TId> hasSameNonDefaultIdAs(id: TId, entity: JpaEntity<*>?): Boolean =
        entity != null && id == entity.id

    private fun <TId> hasSameBusinessSignature(other: JpaEntity<*>?): Boolean =
        other?.let { equalProperties(other) } ?: false
}
