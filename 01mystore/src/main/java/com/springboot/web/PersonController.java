package com.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.model.User;
import com.springboot.service.UserServervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
//    private PersonService personService;
    private UserServervice userServervice;

    @GetMapping("/getAllPerson")
    //http://localhost:8085/getAllPerson?pageNum=1&pageSize=3
    //查看第一页的三行数据 pageNum：页数 pageSize：每页中的多少行
    public ModelAndView getAllPerson(Model model,
                                     @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                     @RequestParam(name = "pageSize",required = true,defaultValue = "3") int pageSize){
        ModelAndView mv=new ModelAndView();
        List<User> list = userServervice.getAlluser(pageNum,pageSize);
        System.out.println(list);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        System.out.println(pageInfo);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("userList.html");
        return mv;//返回结果给前端页面

    }
}