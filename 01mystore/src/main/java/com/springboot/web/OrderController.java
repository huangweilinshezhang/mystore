package com.springboot.web;

import com.github.pagehelper.PageInfo;
import com.springboot.model.*;
import com.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private UserServervice userServervice;
    private User user;

    @Autowired
    private ProductService productService;
    private Product product;

    @Autowired
    private ProductImgServerice productImgServerice;
    private ProductImage productImage;

    @Autowired
    private CateService cateService;
    private com.springboot.model.Cate cate;

    @Autowired
    private ShoppingCatService shoppingCatService;
    private ShoppingCat shoppingCat;

    @Autowired
    private OrderService orderService;
    private Order order;
    @RequestMapping(value = "/getAllOrder")
    public ModelAndView getAllOrder(HttpSession session,
                                    @RequestParam(name = "pageNum", required = true, defaultValue = "1") int pageNum,
                                    @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        //查询全部的订单
        ModelAndView mv=new ModelAndView();
        List<Order> orderList = orderService.getAllOrder(pageNum,pageSize);
        session.setAttribute("orderList",orderList);

        System.out.println("orderList:"+orderList.size());
        PageInfo<Order> pageInfo2 = new PageInfo<Order>(orderList);
        System.out.println(pageInfo2);
        mv.addObject("pageInfo2",pageInfo2);
        mv.setViewName("orderList.html");
        return mv;
    }
    @RequestMapping(value = "/delOrder")
    public ModelAndView delOrder(HttpSession session,int orderId){
        //查询全部的订单
        ModelAndView mv=new ModelAndView();
        orderService.delOrder(orderId);
        mv.setViewName("orderList.html");
        return mv;
    }
}
