package com.springboot.web;

import com.springboot.service.UserServervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class htmlController {
    @Autowired
//把数据往Java容器中注入
    private UserServervice userServervice;
    //指向服务页面
    @RequestMapping(value = "/service")
    public ModelAndView service(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("service.html");
        return mv;
    }
    //指向服务页面
    @RequestMapping(value = "/serviceDetails")
    public ModelAndView serviceDetail(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("service-details.html");
        return mv;
    }

    //指向产品页面
    @RequestMapping(value = "/portfolio")
    public ModelAndView portfolio(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("portfolio.html");
//        mv.addObject("userStaus","主页面");
        return mv;
    }
    //指向产品详情页面
    @RequestMapping(value = "/portfolioDetails")
    public ModelAndView portfolioDetails(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("portfolio-details.html");
        return mv;
    }
    //指向产品博客页面
    @RequestMapping(value = "/blog")
    public ModelAndView blog(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("blog-grid.html");
        return mv;
    }
    //指向产品博客详情页面
    @RequestMapping(value = "/blogDetails")
    public ModelAndView blogDetails(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("blog-details.html");
        return mv;
    }
    //指向产品博客侧边栏详情页面
    @RequestMapping(value = "/blogDetailsSidebar")
    public ModelAndView blogDetailsSidebar(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("blog-details-sidebar.html");
        return mv;
    }
    //指向产品联系页面
    @RequestMapping(value = "/contact")
    public ModelAndView contact(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("contact.html");
        return mv;
    }



}
