package com.wnsgml972.strada.api.auth

import com.wnsgml972.strada.AbstractWebTest
import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.account.domain.UserRepository
import com.wnsgml972.strada.api.v1.account.service.UserService
import com.wnsgml972.strada.exception.StradaNotFoundException
import io.mockk.*
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class AccountServiceTest : AbstractWebTest() {

    lateinit var sut: UserService

    lateinit var userRepository: UserRepository
    private val userMap = mutableMapOf<String, User>()
    private var capturedId = ""

    @BeforeEach
    fun initialize() {
        // clear test environment
        userMap.clear()
        unmockkAll()

        // set mock test
        userRepository = mockk()
        every {
            userRepository.findById(any())
        } answers {
            Optional.of(userMap[capturedId] ?: throw StradaNotFoundException())
        }

        every {
            userRepository.save(any())
        } answers {
            userMap[capturedId] = User.of(capturedId, true)
            userMap[capturedId] ?: throw StradaNotFoundException()
        }

        every {
            userRepository.findAll()
        } answers {
            userMap.values.toList()
        }

        // set unit test service
        sut = UserService(userRepository)
    }

    @Test
    fun `sign up & get user`() {
        // given: 유저가 아이디를 입력합니다
        capturedId = "010-1234-5678"

        // when: 가입 후 유저를 가져옵니다.
        val user1 = sut.signUp(capturedId)
        val user2 = sut.findById(capturedId)
        val totalUserCount = sut.findAll().size

        // then: 검증합니다
        user1 `should be equal to` user2
        totalUserCount `should be equal to` (1)

        verify(exactly = 1) { userRepository.save(any()) }
        verify(exactly = 1) { userRepository.findById(any()) }
        verify(exactly = 1) { userRepository.findAll() }
        confirmVerified(userRepository)
    }
}