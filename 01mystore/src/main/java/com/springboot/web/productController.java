package com.springboot.web;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Cate;
import com.springboot.model.Product;
import com.springboot.service.CateService;
import com.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class productController {
    @Autowired
    private ProductService productServervice;
    private CateService cateService;
    private Product product;
    private Cate cate;
//    切记service要在bean上面才能扫描的到

    @GetMapping("/getAllProduct")
    public ModelAndView product(Model model,
                                     @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                     @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        System.out.println("进入链接");
        List<Product> list = productServervice.getAllProduct(pageNum,pageSize);
//        System.out.println(list);
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
//        System.out.println(Product.);
        System.out.println(pageInfo);
        mv.addObject("pageInfo",pageInfo);
//        System.out.println(list);
        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面

    }
    //根据id查找商品
    @GetMapping("/selectByPrimaryKey")
    public ModelAndView product(Integer id){
        ModelAndView mv=new ModelAndView();
        System.out.println("进入链接");
        Product list = productServervice.selectByPrimaryKey(id);
        System.out.println(list.getSellerId());
//        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
//        System.out.println(pageInfo);
        mv.addObject("pageInfo",list);
        System.out.println(list);
        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面
    }
    //增加商品
    @GetMapping("/addPro")
    public ModelAndView addPro(HttpSession session,Model model,
                               @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                               @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        //暂时不做，这是增加商品页面的时候加载分类使用的
        //查询商品中的fid和cid
//        select distinct product_fid from proudct
//        select distinct product_cid from proudct
        Cate flist1 = cateService.selectByPrimaryKey(1);
        List<String> listFid = productServervice.selectFid();
        List<String> listCid = productServervice.selectCid();
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        session.setAttribute("listFid", listFid);
        session.setAttribute("listCid", listCid);

        //尝试遍历出阿莱
        //首先查询父类
        for(int i=0;i<listFid.size();i++){
            System.out.println(listFid.get(i));
            mv.addObject("pageInfo",listFid.get(i));
            //根据fid和子id去分类查询分类名字
            Cate a=new Cate();
            int cateId=Integer.valueOf(listFid.get(i));
            a.setCateId(Integer.valueOf(cateId));
            System.out.println("a.getCateId():"+a.getCateId());
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            Cate flist1 = cateService.selectByPrimaryKey(cateId);
//            System.out.println("a.getCateId():"+a.getCateId());
            //根据id查询子类
//        System.out.println("flist.getCateName()"+flist.getCateName()+"|flist.getCateId()：|"+flist.getCateId()+"flist.getCateParentId()|："+flist.getCateParentId());
//            if(Integer.valueOf(flist.getCateParentId())!=0){
//                int cateParentId=flist.getCateParentId();
//                Cate clist = cateService.selectByclist(cateParentId);
//                System.out.println("进入查询子类：clist.getCateName()"+clist.getCateName()+"clist.getCateId()"+clist.getCateId());
//            }
        }
//        for(int i=0;i<listCid.size();i++){
//            System.out.println(listCid.get(i));
//        }

//        List list=new List();
//        List<Cate> list = cateService.getAllCate(pageNum,pageSize);
//        PageInfo<Cate> pageInfo = new PageInfo<Cate>(listFid);
//        PageInfo<Cate> pageInfo2 = new PageInfo<Cate>(listCid);
//        System.out.println(pageInfo2);
//        mv.addObject("pageInfo",listFid);
//        mv.addObject("pageInfo2",pageInfo2);
        mv.setViewName("productAdd.html");
        return mv;//返回结果给前端页面
    }
    @GetMapping("/productAdd")
    public ModelAndView productAdd(String productName,Integer productCid,Integer productFid,
                                   String productBiaoqian,String productDetail,Integer productTupian,Integer productNumber,
                                   Integer productPrice,String productAddress,String productKuaidi,Integer sellerId){
        ModelAndView mv=new ModelAndView();
        System.out.println(productName+productCid+productFid+productBiaoqian+productDetail+productTupian+
                productNumber+productPrice+productAddress+productKuaidi+sellerId);
        productServervice.productAdd(productName,productCid,productFid,productBiaoqian,productDetail,productTupian,
                productNumber,productPrice,productAddress,productKuaidi,sellerId);
        mv.addObject("pageInfo","yes");
        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面
    }
    //根据id删除商品
    @GetMapping("/deleteByProductIdKey")
    public ModelAndView deleteByPrimaryKey(Integer id){
        ModelAndView mv=new ModelAndView();
        System.out.println("进入链接");
        productServervice.deleteByPrimaryKey(id);
        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面
    }
    //跳转修改商品页面
    @GetMapping("/upPro")
    public ModelAndView upPro(Integer id){
        ModelAndView mv=new ModelAndView();
        System.out.println("进入链接");
        Product product=productServervice.selectByPrimaryKey(id);
        mv.addObject("product",product);
        mv.setViewName("upDateProduct.html");
        return mv;//返回结果给前端页面
    }
    //根据id修改商品
    @GetMapping("/updateByPrimaryKeySelective")
    public ModelAndView updateByPrimaryKeySelective(Integer productId,String productName,Integer productCid,Integer productFid,
                                                    String productBiaoqian,String productDetail,Integer productTupian,Integer productNumber,
                                                    Integer productPrice,String productAddress,String productKuaidi,Integer sellerId){
        ModelAndView mv=new ModelAndView();
        System.out.println("进入链接");
        productServervice.updateByPrimaryKeySelective(productId,productName,productCid,productFid,productBiaoqian,productDetail,productTupian,
                productNumber,productPrice,productAddress,productKuaidi,sellerId);
        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面
    }
}
