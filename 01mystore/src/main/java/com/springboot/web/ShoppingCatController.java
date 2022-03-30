package com.springboot.web;

import com.springboot.mapper.OrderMapper;
import com.springboot.mapper.ShoppingCatMapper;
import com.springboot.model.Order;
import com.springboot.model.Product;
import com.springboot.model.ProductImage;
import com.springboot.model.ShoppingCat;
import com.springboot.service.*;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ShoppingCatController {
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

//    添加购物车列表1.自带添加一条数据，2，查看用户自己购物车的东西。
    @RequestMapping(value = "/cart")
    public ModelAndView cart(HttpSession session, int productId, int sellerId, int productNumber,int userId){
        int total = 0;//共计
        int totalNumber=0;//共计多少件商品
//        简单点
//        total=number*price+运费（暂时定5块钱）;
        //将其添加到购物车表格中
        if(sellerId ==0){
            System.out.println("用户没有登录！所以没有购物车！");
        }else{
            System.out.println("正常！");
        }
        ShoppingCat sc=new ShoppingCat();
        int k;
        k = shoppingCatService.ShoppingCatAdd(userId,productId,sellerId,productNumber);

//        根据用户id和产品id，查看购物车中的产品信息(简略)
        List<ShoppingCat> scList=new ArrayList<>();
        List<Product> plist=new ArrayList<>();
        List<ProductImage> FinishpImg=new ArrayList<>();
        List<ProductImage> FinishpImg2=new ArrayList<>();
        List<String> fishImg=new ArrayList<>();
        System.out.println("userId:"+userId);

        scList=shoppingCatService.selectUserId(userId);//应该是查询用户的才对，这两个参数的话就不能实现多商品添加，参数不对
//        for(int i=0;i<scList.l)
        System.out.println("scList.size():"+scList.size());
//        3.根绝用户id和产品id分别查询信息，添加到会话中返回
        for(int i=0;i<scList.size();i++){
//            System.out.println("scList:"+scList.get(i).getShoppingId());
            ShoppingCat s=new ShoppingCat();
            Product p=new Product();
            ProductImage pImage=new ProductImage();
            s=scList.get(i);
            p=productService.selectByPrimaryKey(s.getProductId());
//            System.out.println("s.getProductId():"+s.getProductId());
            plist.add(p);
            List<ProductImage> pImg=new ArrayList<>();
            pImg=productImgServerice.selectImgByPrimaryKey(s.getProductId(),s.getSellerId());//获取到的是一个图片的list，选第一个
            FinishpImg.addAll(pImg);
            total=total+s.getProductNumber()*p.getProductPrice();
            totalNumber=totalNumber+s.getProductNumber();
        }
        System.out.println("共计:"+total);
//        System.out.println("FinishpImg.size:"+FinishpImg.size());
        int a=0;//设置初始的图片查询索检
        for(int i=0;i<FinishpImg.size();i++){
//            System.out.println("FinishpImg:"+FinishpImg.get(i).getImgNumber());
            ProductImage pI=new ProductImage();
            if(a<FinishpImg.size()){//设置索检值a不能超过图片数组大小
                pI=FinishpImg.get(a);//获取图片列表中的第一个，变量名a，从0开始索检，每次增加三个间隔
//                System.out.println(pI.getImgNumber());
                FinishpImg2.add(pI);
//                System.out.println("i:"+i);
                a=a+3;
            }
        }
        session.setAttribute("scList",scList);
        session.setAttribute("plist",plist);
        session.setAttribute("FinishpImg",FinishpImg2);
        session.setAttribute("total",total);//返回共计回话
        session.setAttribute("totalNumber",totalNumber);//返回数量回话

        ModelAndView mv=new ModelAndView();
        mv.setViewName("cart.html");
        return mv;
    }
//    增删查
//    添加删除购物车商品连接

    @RequestMapping(value = "/delShoppingCat")
    public ModelAndView delShoppingCat(HttpSession session,int userId,int productId,int sellerId,int productNumber,int shoppingId){
//        System.out.println("userId:"+userId);
//        System.out.println("ProductId:"+productId);
//        System.out.println("sellerId:"+sellerId);
//        System.out.println("productNumber:"+productNumber);
//        System.out.println("shoppingId:"+shoppingId);
        int totalNumber=0;
        int k=shoppingCatService.deleteByPrimaryKey(shoppingId);
        System.out.println("k:"+k);
        //删除完应当刷新数据！
        //        根据用户id和产品id，查看购物车中的产品信息(简略)
        int total=0;
        List<ShoppingCat> scList=new ArrayList<>();
        List<Product> plist=new ArrayList<>();
        List<ProductImage> FinishpImg=new ArrayList<>();
        List<ProductImage> FinishpImg2=new ArrayList<>();
        List<String> fishImg=new ArrayList<>();
        System.out.println("userId:"+userId);

        scList=shoppingCatService.selectUserId(userId);//应该是查询用户的才对，这两个参数的话就不能实现多商品添加，参数不对
//        for(int i=0;i<scList.l)
        System.out.println("scList.size():"+scList.size());
//        3.根绝用户id和产品id分别查询信息，添加到会话中返回
        for(int i=0;i<scList.size();i++){
            System.out.println("scList:"+scList.get(i).getShoppingId());
            ShoppingCat s=new ShoppingCat();
            Product p=new Product();
            ProductImage pImage=new ProductImage();
            s=scList.get(i);
            p=productService.selectByPrimaryKey(s.getProductId());
//            System.out.println("s.getProductId():"+s.getProductId());
            plist.add(p);
            List<ProductImage> pImg=new ArrayList<>();
            pImg=productImgServerice.selectImgByPrimaryKey(s.getProductId(),s.getSellerId());//获取到的是一个图片的list，选第一个
            FinishpImg.addAll(pImg);
            total = total + s.getProductNumber() * p.getProductPrice();
            totalNumber=totalNumber+s.getProductNumber();
        }
//        System.out.println("FinishpImg.size:"+FinishpImg.size());
        int a=0;//设置初始的图片查询索检
        for(int i=0;i<FinishpImg.size();i++){
//            System.out.println("FinishpImg:"+FinishpImg.get(i).getImgNumber());
            ProductImage pI=new ProductImage();
            if(a<FinishpImg.size()){//设置索检值a不能超过图片数组大小
                pI=FinishpImg.get(a);//获取图片列表中的第一个，变量名a，从0开始索检，每次增加三个间隔
//                System.out.println(pI.getImgNumber());
                FinishpImg2.add(pI);
//                System.out.println("i:"+i);
                a=a+3;
            }
        }
        session.setAttribute("scList",scList);
        session.setAttribute("total",total);
        session.setAttribute("totalNumber",totalNumber);//返回数量回话
        session.setAttribute("plist",plist);
        session.setAttribute("FinishpImg",FinishpImg2);


        ModelAndView mv=new ModelAndView();
        mv.setViewName("cart.html");
        return mv;
    }
//    添加订单列表
    @RequestMapping(value = "/order")
    public ModelAndView order(HttpSession session,int userId){
        /*
        * 用户结算之后进入订单支付界面（传入用户id，根绝id查询购物车情况），
        * 支付成功，则清空购物车
          并且在商品页面进行数量减少*/
        List<ShoppingCat> scList=new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("时间："+df.format(new Date()));// new Date()为获取当前系统时间
        int expressId=1;//设置默认快递
        int total=100;       //共计多少钱
        String evaluate="添加评价";
        int orderState=1;//初始订单状态

        scList=shoppingCatService.selectUserId(userId);//应该是查询用户的才对，这两个参数的话就不能实现多商品添加，参数不对
        System.out.println("scList.size():"+scList.size());
//        3.根绝用户id和产品id分别查询信息，添加到会话中返回
        for(int i=0;i<scList.size();i++){
            ShoppingCat sc=new ShoppingCat();
            Product p=new Product();
            sc=scList.get(i);
            p=productService.selectByPrimaryKey(sc.getProductId());
            total=sc.getProductNumber()*p.getProductPrice();
//            查询到没个购物车把要弄的东西放进order
            System.out.println("k:"+userId+sc.getProductId()+sc.getProductNumber()+df.format(new Date())+expressId+total+evaluate+orderState);
           int k=orderService.insertOrder(userId,sc.getProductId(),sc.getProductNumber(),df.format(new Date()),expressId,total,evaluate,orderState);

        }
        ModelAndView mv=new ModelAndView();
        mv.setViewName("order.html");
        return mv;
    }
    @RequestMapping(value = "/ok")
    public ModelAndView ok(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("ok.html");
        return mv;
    }
}
