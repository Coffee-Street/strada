package com.wnsgml972.strada.example

import spock.lang.Specification

import java.math.RoundingMode

class SpockTestExampleTest extends Specification {
    void setup() {
    }

    void cleanup() {
    }

    def "Calculate"() {

        given:
        RoundingMode roundingMode = RoundingMode.DOWN

        when:
        def calculate = SpockTestExample.calculate(10000L, 0.1f, roundingMode)

        then:
        calculate == 10L
    }
}
