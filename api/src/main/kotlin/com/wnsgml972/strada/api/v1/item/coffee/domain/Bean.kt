package com.wnsgml972.strada.api.v1.item.coffee.domain

import com.wnsgml972.strada.api.base.AbstractJpaEntity
import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@SuppressWarnings("LongParameterList")
class Bean(

    @Id
    @Column(length = 25)
    override var id: String?,

    val origin: String,

    val farm: String,

    val description: String,

    val processing: String,

    val roasting: String,

    val kind: String,

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
