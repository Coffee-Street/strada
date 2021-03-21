package com.wnsgml972.strada.api.base

import com.wnsgml972.strada.api.v1.item.domain.Bean
import com.wnsgml972.strada.api.v1.item.domain.BeanCoffee
import com.wnsgml972.strada.api.v1.item.domain.Coffee
import com.wnsgml972.strada.api.v1.item.domain.CoffeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class StradaRunner @Autowired constructor(

    private val coffeeRepository: CoffeeRepository,
) : ApplicationRunner {
    @SuppressWarnings("MagicNumber")
    override fun run(args: ApplicationArguments?) {

        //test bean
        var bean = Bean("케냐AA", "test", "test", "test", "test", "test", "test", "test")

        //test coffee
        var coffee = Coffee("americano", "http://localhost:80/americano", 10000, "test", "black")
        var coffee2 = Coffee("latte", "http://localhost:80/latte", 10000, "test", "milk")

        //beancoffee test
        var beanCoffee = BeanCoffee("1", coffee, bean)
        var beanCoffee2 = BeanCoffee("2", coffee2, bean)

        var beanCoffees = ArrayList<BeanCoffee>()
        var beanCoffees2 = ArrayList<BeanCoffee>()
        beanCoffees.add(beanCoffee)
        beanCoffees2.add(beanCoffee2)

        coffee.beanCoffees = beanCoffees
        coffee2.beanCoffees = beanCoffees2

        beanCoffees.add(beanCoffee2);
        bean.beanCoffees = beanCoffees


        coffeeRepository.save(coffee)
    }
}
