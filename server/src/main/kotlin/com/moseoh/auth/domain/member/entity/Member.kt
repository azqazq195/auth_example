package com.moseoh.auth.domain.member.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity
data class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 255, nullable = false, unique = true)
    @Comment("이메일")
    val email: String,

    @Column(length = 255, nullable = false)
    @Comment("비밀번호")
    private val password: String, // getPassword 를 override 하기 위해 private 으로 선언

    @ElementCollection(fetch = FetchType.EAGER)
    val roles: List<String> = arrayListOf(),

    @Column(length = 255, nullable = false)
    @Comment("회원가입 이메일 인증 토큰")
    val verifyToken: String,

    @Column(length = 1, nullable = false)
    @Comment("활성화 여부")
    val isEnable: Boolean = true

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.roles.stream()
            .map { role: String -> SimpleGrantedAuthority(role) }
            .toList()
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = isEnable

    override fun isAccountNonLocked(): Boolean = isEnable

    override fun isCredentialsNonExpired(): Boolean = isEnable

    override fun isEnabled(): Boolean = isEnable
}
