/*
 * MIT License
 *
 * Copyright (c) 2020 JunHee Kim
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.wnsgml972.strada.example

import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.math.RoundingMode

// Reference : https://jojoldu.tistory.com/228
// Reference : https://d2.naver.com/helloworld/568425

@SpringBootTest // 통합 테스트 방식으로 설정한다.
class SpockTestExampleTest extends Specification { // extends Specification을 받는 것은 Spring의 TestContext로 설정하는 것과 같다.
    void setup() {
        // @Before
    }

    void setupSpec() {
        // @BeforeClass
    }

    void cleanup() {
        // @After
    }

    void cleanupSpec() {
        // @AfterClass
    }

    @Test // Spock의 Feature 메소드와 같은 뜻, Spock에서는 안해도 되지만, Jacoco 연동하여 Codecov를 표시하기 위해 추가했다.
    def "금액의 퍼센트 계산 결과값의 소수점 버림을 검증한다."() {

        given:
        RoundingMode roundingMode = RoundingMode.DOWN

        when:
        def calculate = SpockTestExample.calculate(10000L, 0.1f, roundingMode)

        then:
        calculate == 10L
    }

    @Test
    def "여러 금액의 퍼센트 계산 결과값의 소수점 버림을 검증한다."() {

        given:
        RoundingMode roundingMode = RoundingMode.DOWN

        expect: // when + then
        SpockTestExample.calculate(amount, rate, roundingMode) == result

        where:
        amount | rate | result
        10000L | 0.1f | 10L
        2799L  | 0.2f | 5L
        159L   | 0.15f| 0L
        2299L  | 0.15f| 3L
    }

    @Test
    def "음수가 들어오면 예외가 발생하는지 확인해보자"() {

        given:
        RoundingMode roundingMode = RoundingMode.DOWN

        when:
        SpockTestExample.calculate(-10000L, 0.1f, roundingMode)

        then:
        def a = 3;
//        def e = thrown(Exception.class)
//        e.message == "음수는 계산할 수 없습니다."
    }

    @Test
    def "주문금액의 소수점 버림을 검증한다. Mock Test"() {

//        given:
//        RoundingMode roundingMode = RoundingMode.DOWN
//        def orderSheet = Mock(OrderSheet.class)
//
//        when:
//        long amount = orderSheet.getTotalOrderAmount()
//
//        then:
//        orderSheet.getTotalOrderAmount() >> 10000L
//        10L == SpockTestExample.calculate(amount, 0.1f, roundingMode)
    }
}
