package com.wnsgml972.strada.api.v1.option.bean.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BeanOptionRepository : JpaRepository<BeanOption, String>
