package com.wnsgml972.strada.api.v1.ordering.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderingRepository : JpaRepository<Ordering, Long>
