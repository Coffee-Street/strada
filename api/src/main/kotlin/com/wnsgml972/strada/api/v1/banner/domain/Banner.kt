package com.wnsgml972.strada.api.v1.banner.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@SuppressWarnings("LongParameterList")
class Banner(

    @Id
    @Column(length = 25)
    override var id: Long?,

    @Column(unique = true)
    val code: String,

    val evalOrder: Int,

    val title: String,

    val imageUrl: String,

    val message: String,

    val backgroundColor: String,

    val fontColor: String

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is Banner &&
                id == other.id &&
                code == other.code &&
                evalOrder == other.evalOrder &&
                title == other.title &&
                imageUrl == other.imageUrl &&
                message == other.message &&
                backgroundColor == other.backgroundColor &&
                fontColor == other.fontColor
    }

    companion object {
        fun of(
            id: Long,
            code: String,
            evalOrder: Int,
            title: String,
            imageUrl: String,
            message: String,
            backgroundColor: String,
            fontColor: String
        ) = Banner(id, code, evalOrder, title, imageUrl, message, backgroundColor, fontColor)
    }
}
