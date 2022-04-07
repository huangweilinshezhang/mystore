package com.springboot.web;

import com.springboot.model.*;
import com.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserAddressController {
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
    //指向产品联系页面

}
