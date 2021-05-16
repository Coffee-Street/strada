package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.BeanCoffee
import com.wnsgml972.strada.api.v1.item.coffee.domain.Coffee

fun Coffee.toDto() = CoffeeDTO(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
    this.beanCoffees
        .let {
            return@let it
                .map { v -> v.bean }
                .toList()
        }
)
fun Coffee.toBannerDto() = CoffeeBannerDTO(
    this.id,
    this.url,
    this.description
)

// 현재 동작하는 방식
/*
fun CoffeeDTO.toEntity(): Coffee {

    var coffee = Coffee(
        this.id,
        this.url,
        this.price,
        this.description,
        this.category,
    )

    var beanCoffee = ArrayList<BeanCoffee>()
    this.bean.forEach { v -> beanCoffee.add(BeanCoffee(0, coffee, v)) }
    coffee.beanCoffees = beanCoffee
    return coffee

}
*/

// 이렇게 하고싶은데, it(Coffee 클래스)의 beanCoffees에 add가 안된다.
fun CoffeeDTO.toEntity() = Coffee(
    this.id,
    this.url,
    this.price,
    this.description,
    this.category,
).let {
    this.bean.forEach { v -> it.beanCoffees.toMutableList().add(BeanCoffee(0, it, v)) }
    return@let it
}

//
// fun CoffeeDTO.toEntity() : Coffee{
//
//    val tt = Coffee(
//        this.id,
//        this.url,
//        this.price,
//        this.description,
//        this.category,
//
//    ).let{
//        it.beanCoffees = ArrayList<BeanCoffee>()
//            this.bean.forEach{ v-> (it.beanCoffees as ArrayList<BeanCoffee>).toMutableList().add(BeanCoffee(0,it,v))}
//
//        println("mintest2: "+it.beanCoffees.size)
//        return@let it
//        }
//
//    println("mintest2: "+tt.beanCoffees.size)
//    return tt
// }
