package com.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.model.User;
import com.springboot.model.Product;
import com.springboot.model.UserAddress;
import com.springboot.service.UserAddressService;
import com.springboot.service.UserServervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private UserServervice userServervice;

    @Autowired
    private UserAddressService userAddressService;
    private UserAddress userAddress;
    @GetMapping("/getAllPerson")
    //http://localhost:8085/getAllPerson?pageNum=1&pageSize=3
    //查看第一页的三行数据 pageNum：页数 pageSize：每页中的多少行
    public ModelAndView getAllPerson(Model model, HttpSession session,
                                     @RequestParam(name = "pageNum", required = true, defaultValue = "1") int pageNum,
                                     @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        List<User> list = userServervice.getAlluser(pageNum,pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        System.out.println(pageInfo);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("userList.html");
        return mv;//返回结果给前端页面
    }
}