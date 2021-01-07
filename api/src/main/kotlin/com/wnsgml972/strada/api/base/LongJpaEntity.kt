package com.wnsgml972.strada.api.base

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class LongJpaEntity : AbstractJpaEntity<Long>() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
}
