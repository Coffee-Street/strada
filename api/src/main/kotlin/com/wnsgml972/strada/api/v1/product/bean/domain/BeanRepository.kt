package com.wnsgml972.strada.api.v1.product.bean.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BeanRepository : JpaRepository<Bean, String>
