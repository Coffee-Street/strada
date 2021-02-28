package com.wnsgml972.strada.api.v1.menu.service

import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.account.service.toDto
import com.wnsgml972.strada.api.v1.menu.domain.CoffeeMenu
import com.wnsgml972.strada.api.v1.menu.domain.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class MenuService (
    private val menuRepository: MenuRepository
){

//    @Transactional(readOnly = true)
//    fun findAllMenu() :List<CoffeeMenu>? =
//        menuRepository.findAllMenu();

    fun findAll(): List<MenuDTO> {
        val it = menuRepository.findAll();
        val coffees = ArrayList<MenuDTO>()

        it.forEach { e -> coffees.add(e.toDto()) }

        return coffees
    }
    fun findById(id: String) : Optional<MenuDTO>? {
        val it =  menuRepository.findById(id).map { v->v.toDto() }
        println("------------data--------")
        println(it)
        return it;
    }




}