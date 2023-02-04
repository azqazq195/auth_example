package com.moseoh.auth.domain.member.controller.dto

import com.moseoh.auth.domain.member.entity.Member

data class MemberDto(
    val email: String,
    val password: String,
    val roles: List<String>,
    val isEnable: Boolean,
) {
    companion object {
        fun of(member: Member): MemberDto {
            return MemberDto(
                email = member.email,
                password = member.password,
                roles = member.roles,
                isEnable = member.isEnable
            )
        }
    }

}
