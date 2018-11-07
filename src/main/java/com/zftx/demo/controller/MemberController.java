package com.zftx.demo.controller;

import com.zftx.demo.mapper.MemberMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MemberController {

    private MemberMapper memberMapper;

    @RequestMapping("/getOne/{id}")
    @ResponseBody
    public Map getOneMember(@PathVariable("id") int id) {
        return memberMapper.findMemberById(id);
    }
}
