package com.wnsgml972.strada.api.v1.banner.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.banner.service.BackgroundColor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Banner(

    @Id
    @Column(length = 25)
    override var id: Long?,

    @Column(unique = true)
    val evalOrder: Int,

    val topLatters: String,

    val imageUrl: String,

    val bottomLatters: String,

//    @Enumerated(EnumType.STRING)
//    val backgroundColor: BackgroundColor,
//
//    @Enumerated(EnumType.STRING)
//    val latterFont: LatterFont,


) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is Banner &&
                id == other.id &&
                evalOrder == other.evalOrder &&
                topLatters == other.topLatters &&
                imageUrl == other.imageUrl &&
                bottomLatters == other.bottomLatters
    }

    companion object {
        fun of(
            id: Long,
            evalOrder: Int,
            topLatters: String,
            imageUrl: String,
            bottomLatters: String,
        ) = Banner(id, evalOrder, topLatters, imageUrl, bottomLatters)
    }
}
