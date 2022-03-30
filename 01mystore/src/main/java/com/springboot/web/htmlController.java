package com.springboot.web;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Cate;
import com.springboot.model.Product;
import com.springboot.model.ProductImage;
import com.springboot.service.CateService;
import com.springboot.service.ProductImgServerice;
import com.springboot.service.ProductService;
import com.springboot.service.UserServervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class htmlController {
    @Autowired
    private ProductService productService;
    private Product product;

    @Autowired
    private ProductImgServerice productImgServerice;
    private ProductImage productImage;

    @Autowired
    private CateService cateService;
    private com.springboot.model.Cate cate;
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
    public ModelAndView portfolio(Model model, HttpSession session,
                                  @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                  @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
            ModelAndView mv=new ModelAndView();
            List<ProductImage> PImglist = new ArrayList<>();//初始化图片数组
            List<com.springboot.model.ProductImage> NoPImglist = new ArrayList<>();//初始化图片数组
            List<ProductImage> FinishPImglist = new ArrayList<>();//最终的分解图片数组
            int a=0;
            int b=2;
            System.out.println("进入商品链接");
            List<Product> list = productService.getAllProduct(pageNum,pageSize);
            PageInfo<Product> pageInfo = new PageInfo<Product>(list);
            System.out.println(pageInfo);
            mv.addObject("pageInfo",pageInfo);
            List<com.springboot.model.Cate> flist1 = new ArrayList<>();
            List<com.springboot.model.Cate> flist2 = new ArrayList<>();

            for(int i=0;i<list.size();i++){
                Product p=new Product();
                p=list.get(i);
                //根据id查询分类列表中的名字
                Cate cate=cateService.selectByPrimaryKey(p.getProductFid());
//           分类功能开始
                flist1.add(cate);
                if(p.getProductCid()!=0){
//                cate2.setCateName("..");
                    com.springboot.model.Cate cate2=cateService.selectByPrimaryKey(p.getProductCid());
                    flist2.add(cate2);
//                System.out.println("子类id不为零");
                }
                if(p.getProductCid()==0){
                    com.springboot.model.Cate cateT=new Cate();
                    flist2.add(cateT);
//                System.out.println("子类id为0");
                }
                if(p.getProductCid()==null){
                    com.springboot.model.Cate cateT=new Cate();
                    flist2.add(cateT);
//                System.out.println("子类id为null");//SQL语句不行
                }
                session.setAttribute("CilFlist",flist1);
                session.setAttribute("CilClist",flist2);
                //            根据商品id查抄商品图片
                List<ProductImage> pImg=productImgServerice.selectImgByPrimaryKey(p.getProductId(),p.getSellerId());
                if(pImg.size() == 0){//当没有查询到图片的情况
                    ProductImage pImg3=new ProductImage();
                    pImg3.setImgNumber("没有图片.jpg");
                    pImg.add(pImg3);
                    NoPImglist=pImg;
                    PImglist.addAll(NoPImglist);
                }else {
                    PImglist.add(pImg.get(0));
                }
                session.setAttribute("FinishPImglist",PImglist);
            }
            mv.setViewName("portfolio.html");
            return mv;//返回结果给前端页面

        }
    //指向产品详情页面
    @RequestMapping(value = "/portfolioDetails")
    public ModelAndView portfolioDetails(Model model, HttpSession session,
                                         @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                         @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize,
                                         int sellerId,int productId){
        ModelAndView mv=new ModelAndView();
        List<ProductImage> PImglist = new ArrayList<>();//初始化图片数组
        List<com.springboot.model.ProductImage> NoPImglist = new ArrayList<>();//初始化图片数组
        List<ProductImage> FinishPImglist = new ArrayList<>();//最终的分解图片数组
        Product p=new Product();
        p.setProductId(productId);
        p.setSellerId(sellerId);

        System.out.println("进入商品链接");
        Product pp=productService.selectByPrimaryKey(p.getProductId());
        List<Product> list = new ArrayList<>();
        list.add(pp);
        session.setAttribute("pageInfo",list);
//        System.out.println("pp"+pp.getProductId()+pp.getProductCid()+pp.getProductFid()+pp.getSellerId());

        List<com.springboot.model.Cate> flist1 = new ArrayList<>();
        List<com.springboot.model.Cate> flist2 = new ArrayList<>();

            //根据id查询分类列表中的名字
            Cate cate=cateService.selectByPrimaryKey(pp.getProductFid());
//            System.out.println(cate.getCateName());
//           分类功能开始
            flist1.add(cate);
            if(pp.getProductCid()!=0){
//                cate2.setCateName("..");
                Cate cate2=cateService.selectByPrimaryKey(pp.getProductCid());
                flist2.add(cate2);
//                System.out.println("子类id不为零");
            }
            if(pp.getProductCid()==0){
                com.springboot.model.Cate cateT=new Cate();
                flist2.add(cateT);
//                System.out.println("子类id为0");
            }
            if(pp.getProductCid()==null){
                com.springboot.model.Cate cateT=new Cate();
                flist2.add(cateT);
//                System.out.println("子类id为null");//SQL语句不行
            }
            session.setAttribute("CilFlist",flist1);
//            session.setAttribute("CilClist",flist2);
            //            根据商品id查抄商品图片
        List<ProductImage> pImg=productImgServerice.selectImgByPrimaryKey(pp.getProductId(),pp.getSellerId());

        if(pImg.size() == 0){//当没有查询到图片的情况
            ProductImage pImg3=new ProductImage();
            pImg3.setImgNumber("没有图片.jpg"+pp.getProductCid());
            pImg.add(pImg3);
            NoPImglist=pImg;
            PImglist.addAll(NoPImglist);
        }else {
            PImglist=pImg;
        }
//        System.out.println("处理完成");
        session.setAttribute("FinishPImglist",PImglist);
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
