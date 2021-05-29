package com.wnsgml972.strada.api.v1.item.coffee.domain

import com.wnsgml972.strada.api.base.AbstractJpaEntity
import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Immutable
@SuppressWarnings("LongParameterList")
class Bean(
    @Id
    @Column(length = 25)
    override var id: String?,

    @Column(length = 128)
    val origin: String,

    @Column(length = 128)
    val farm: String,

    @Column(length = 128)
    val description: String,

    @Column(length = 128)
    val processing: String,

    @Column(length = 128)
    val roasting: String,

    @Column(length = 128)
    val kind: String,

    @Column(length = 128)
    val grade: String,

    ) : AbstractJpaEntity<String>() {

    override fun equalProperties(other: Any): Boolean {
        return other is Bean &&
                id == other.id &&
                origin == other.origin &&
                farm == other.farm &&
                description == other.description &&
                processing == other.processing &&
                roasting == other.roasting &&
                kind == other.kind &&
                grade == other.grade
    }

    companion object {
        fun of(
            id: String,
            origin: String,
            farm: String,
            description: String,
            processing: String,
            roasting: String,
            kind: String,
            grade: String
        ) = Bean(id, origin, farm, description, processing, roasting, kind, grade)
    }
}
