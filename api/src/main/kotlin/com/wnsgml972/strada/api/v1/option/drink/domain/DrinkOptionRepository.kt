package com.wnsgml972.strada.api.v1.option.drink.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DrinkOptionRepository : JpaRepository<DrinkOption, String>
