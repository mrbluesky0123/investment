package com.exam.user.repository

import com.exam.user.entity.UserAccount
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@MybatisTest
class UserAccountRepositoryTest {

    @Autowired
    lateinit var userAccountMapper: UserAccountMapper

    @Test
    fun 사용자_계좌_조회_정상() {
        var userAccount: UserAccount? = userAccountMapper.selectOneUserAccount("2021050001")
        assertThat(userAccount?.balanceAmount, `is`(BigDecimal.valueOf(24000000)))
    }

    @Test
    fun 사용자_계좌_조회_미존재() {
        var userAccount: UserAccount? = userAccountMapper.selectOneUserAccount("2021050006")
        assertThat(userAccount, `is`(nullValue()))
    }

    @Test
    @Transactional
    fun 사용자_계좌_잔액_갱신() {
        var userAccount: UserAccount = UserAccount()
        userAccount.userId = "2021050001"
        userAccount.balanceAmount = BigDecimal.valueOf(0)
        this.userAccountMapper.updateUserAccountBalance(userAccount)
        userAccount = userAccountMapper.selectOneUserAccount("2021050001")!!
        assertThat(userAccount.balanceAmount, `is`(BigDecimal.ZERO))

    }

}