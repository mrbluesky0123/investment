package com.exam.user.repository

import com.exam.user.entity.User
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@MybatisTest
class UserRepositoryTest {

    @Autowired
    lateinit var userMapper: UserMapper

    @Test
    fun 단일_회원_조회() {
        var user: User? = userMapper.selectOneUser("2021050001")
        assertThat(user?.userName, `is`("KOGILDONG"))
    }

    @Test
    fun 미존재_회원_조회() {
        var user: User? = userMapper.selectOneUser("20210500014")
        assertThat(user, `is`(nullValue()))
    }

}