package com.moseoh.auth.domain.member.controller

import com.moseoh.auth.domain.member.controller.dto.MemberDto
import com.moseoh.auth.domain.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("")
    fun findAll(): ResponseEntity<List<MemberDto>> {
        val memberList = memberService.findAll()
        val memberDtoList = memberList.map { MemberDto.of(it) }
        return ResponseEntity.ok().body(memberDtoList)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<MemberDto> {
        val member = memberService.findById(id)
        val memberDto = MemberDto.of(member)
        return ResponseEntity.ok().body(memberDto)
    }

}