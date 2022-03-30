package com.springboot.web;

import com.springboot.model.Product;
import com.springboot.model.ProductImage;
import com.springboot.service.CateService;
import com.springboot.service.ProductImgServerice;
import com.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ImgController {
    @Autowired
    private ProductService productService;
    private Product product;

    @Autowired
    private ProductImgServerice productImgServerice;
    private ProductImage productImage;

    @Autowired
    private CateService cateService;
    private com.springboot.model.Cate cate;
//    删改

    @GetMapping("/ImgProduct")
    //通过sellerId和产品id查询图片
    public ModelAndView selectImgByPrimaryKey(Model model, HttpSession session,int sellerId,int productId){
        ModelAndView mv=new ModelAndView();
        Product p=new Product();
        p.setSellerId(sellerId);
        p.setProductId(productId);
        List<ProductImage> pImg=productImgServerice.selectImgByPrimaryKey(p.getProductId(),p.getSellerId());
        session.setAttribute("FinishPImglist",pImg);
        System.out.println(pImg.size());
        mv.setViewName("text.html");
        return mv;//返回结果给前端页面
    }
    //通过sellerId和产品id修改图片
    @GetMapping("/updataBySellerAndProductId")
    //通过sellerId和产品id查询图片
    public ModelAndView updataBySellerAndProductId(Model model, HttpSession session,int sellerId,int productId){
        ModelAndView mv=new ModelAndView();
        Product p=new Product();
        p.setSellerId(sellerId);
        p.setProductId(productId);
        List<ProductImage> pImg=productImgServerice.selectImgByPrimaryKey(p.getProductId(),p.getSellerId());
        session.setAttribute("FinishPImglist",pImg);
        System.out.println(pImg.size());
        mv.setViewName("text.html");
        return mv;//返回结果给前端页面
    }
//
        @GetMapping("/deleteByPrimaryKey")
        //通过sellerId和产品id查询图片
        public ModelAndView deleteByPrimaryKey(Model model, HttpSession session,int id){
            ModelAndView mv=new ModelAndView();
            Product p=new Product();
            p.setProductId(id);
            productImgServerice.deleteByPrimaryKey(p.getProductId());
            mv.setViewName("productList.html");
            return mv;//返回结果给前端页面
        }

}
