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
import java.util.List;

@Controller
public class CateController {
    @Autowired
    private CateService cateService;
    private Cate cate;
    private ProductService productService;
    private Product product;
//    切记service要在bean上面才能扫描的到
    @GetMapping("/cateList")
    public ModelAndView CateList(HttpSession session, Model model,
                                 @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                 @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        List<Cate> list = cateService.getAllCate(pageNum,pageSize);
//        System.out.println(list);
        PageInfo<Cate> pageInfo = new PageInfo<Cate>(list);
        System.out.println(pageInfo);
        mv.addObject("pageInfo",pageInfo);
        session.setAttribute("cateList", list);
//        System.out.println(list);
        mv.setViewName("cateList.html");
        return mv;//返回结果给前端页面
    }

    //根据分类id查询分类
    @GetMapping("/selectByCateId")
    public ModelAndView selectByCateId(Integer cateId){
        ModelAndView mv=new ModelAndView();
        Cate flist = cateService.selectByPrimaryKey(cateId);
        //根据id查询子类
        System.out.println("进入一级目录,分类名称："+flist.getCateName()+"  |父分类id"+flist.getCateId()+"  |父分类的ID："+flist.getCateParentId());
        //使用父类id检查是否parentId里面有
            int cateParentId=flist.getCateId();
            Cate clist = cateService.selectByclist(cateParentId);
        if(clist !=null){
            System.out.println("进入二级类目，进入查询子类目，根绝父类id查询得到的子类名称："+clist.getCateName());
        }
//        System.out.println(flist.getCateName());
        mv.addObject("Cate",flist);
        mv.setViewName("cateList.html");
        return mv;//返回结果给前端页面
    }
    //增加分类

    //删除分类



}
