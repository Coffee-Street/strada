package com.wnsgml972.strada.api.v1.menu.domain

import com.wnsgml972.strada.api.v1.account.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface MenuRepository : JpaRepository<CoffeeMenu, String>{
    //override fun findById(id: String): Optional<CoffeeMenu>

}


