package com.springboot.web;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Cate;
import com.springboot.model.Product;
import com.springboot.service.CateService;
import com.springboot.service.ProductService;
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
public class CateController {
    @Autowired
    private CateService cateService;
    private Cate cate;

    @Autowired
    private ProductService productService;
    private Product product;

//    切记service要在bean上面才能扫描的到
    @GetMapping("/cateSelect")
    public ModelAndView CateList(HttpSession session, Model model,
                                 @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                 @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        List<Cate> list = cateService.getAllCate(pageNum,pageSize);
        System.out.println("这是cateList:"+list);
        PageInfo<Cate> pageInfo = new PageInfo<Cate>(list);
        System.out.println(pageInfo);
        System.out.println("进入链接");
        List<Product> productList = productService.getAllProduct(pageNum,pageSize);
        for(int i=0;i<productList.size();i++){
            System.out.println("productList:"+productList);

        }
        System.out.println("productList:"+productList);

        mv.addObject("pageInfo",pageInfo);
        session.setAttribute("cateList", list);
        session.setAttribute("productList", productList);
        mv.setViewName("cateList.html");
        return mv;//返回结果给前端页面
    }

    //根据分类id查询分类
    @GetMapping("/selectByCateId")
    public ModelAndView selectByCateId(HttpSession session,Integer cateId){
        ModelAndView mv=new ModelAndView();
        List<String> listFid = productService.selectFid();
        List<String> listCid = productService.selectCid();
        List<Cate> listFname =new ArrayList<>();
        session.setAttribute("listFid", listFid);
        session.setAttribute("listCid", listCid);

       Cate flist = cateService.selectByPrimaryKey(cateId);
        listFname.add(flist);
        //根据id查询子类
        System.out.println("进入一级目录,分类名称："+flist.getCateName()+"  |父分类id"+flist.getCateId()+"  |父分类的ID："+flist.getCateParentId());
        //使用父类id检查是否parentId里面有
            int cateParentId=flist.getCateParentId();
            Cate clist = cateService.selectByPrimaryKey(cateParentId);
        if(clist !=null){
            System.out.println("进入二级类目，进入查询子类目，根绝父类id查询得到的子类名称："+clist.getCateName());
        }
//        System.out.println(flist.getCateName());
        session.setAttribute("listFname",listFname);
        session.setAttribute("clist",clist);
        mv.setViewName("cateAdd.html");
        return mv;//返回结果给前端页面
    }
    //增加分类
    @GetMapping("/cateAdd")
    public ModelAndView cateAdd(HttpSession session, Cate cate){
        ModelAndView mv=new ModelAndView();
        List<String> listCateFid = cateService.selectCateFid();
        session.setAttribute("listFid", listCateFid);
        //尝试遍历出阿莱
        //首先查询父类
        mv.setViewName("cateAdd.html");
        return mv;//返回结果给前端页面
    }
    @GetMapping("/toCateAdd")
    public ModelAndView toCateAdd(HttpSession session,int cateParentId,String CateName){
        ModelAndView mv=new ModelAndView();
        Cate cate=new Cate();
        cate.setCateName(CateName);
        cate.setCateParentId(cateParentId);
        int i = cateService.toCateAdd(cate.getCateParentId(), cate.getCateName());
        //尝试遍历出阿莱
        //首先查询父类
        mv.setViewName("cateList.html");
        return mv;//返回结果给前端页面
    }
    //删除分类

    @GetMapping("/deleteByCateIdKey")
    public ModelAndView deleteByCateIdKey(HttpSession session,int cateId,
                                          @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
    @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        Cate cate=new Cate();
        cate.setCateId(cateId);
        Integer i = cateService.deleteByPrimaryKey(cate.getCateId());
        //尝试遍历出阿莱
        //首先查询父类
        mv.setViewName("cateList.html");
        return mv;//返回结果给前端页面
    }



}
