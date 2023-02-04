package com.moseoh.auth.domain.member.service

import com.moseoh.auth.domain.member.entity.Member
import com.moseoh.auth.domain.member.entity.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun findById(id: Long): Member =
        memberRepository.findById(id).orElseThrow { RuntimeException("Member not found") }

    fun findByEmail(email: String): Member =
        memberRepository.findByEmail(email).orElseThrow { RuntimeException("Member not found") }

    fun findAll(): List<Member> =
        memberRepository.findAll()
}