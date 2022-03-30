package com.springboot.web;

import com.github.pagehelper.PageInfo;
import com.springboot.model.Cate;
import com.springboot.model.ProductImage;
import com.springboot.model.Cate;
import com.springboot.model.Product;
//import com.springboot.model.ProductImage;
import com.springboot.service.CateService;
import com.springboot.service.ProductImgServerice;
import com.springboot.service.ProductService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
public class productController {
    @Autowired
    private ProductService productService;
    private Product product;

    @Autowired
    private ProductImgServerice productImgServerice;
    private ProductImage productImage;

    @Autowired
    private CateService cateService;
    private com.springboot.model.Cate cate;


//    切记service要在bean上面才能扫描的到
    @GetMapping("/text")
    public ModelAndView text(Model model,HttpSession session){
        ModelAndView mv=new ModelAndView();
        int a=0;
        int b=2;
        List<ProductImage> textList = new ArrayList<>();//初始化图片数组
        List<ProductImage> textList2 = new ArrayList<>();//初始化图片数组
        textList= (List<ProductImage>) session.getAttribute("FinishPImglist");


        for(int i=0;i<textList.size();i++){
            textList2=textList.subList(a,(b+1));
            session.setAttribute("text",textList2);
            a=a+3;
            b=b+3;
        }

        mv.setViewName("text.html");
        return mv;//返回结果给前端页面
    }
    @GetMapping("/getAllProduct")
    public ModelAndView product(Model model,HttpSession session,
                                @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        List<com.springboot.model.ProductImage> PImglist = new ArrayList<>();//初始化图片数组
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

//            flist2.add(cate);
//             System.out.println("CateName():"+flist3.get(0).getCateName());
//            System.out.println("flist3():"+flist3.size());
            session.setAttribute("CilFlist",flist1);
            session.setAttribute("CilClist",flist2);
//            System.out.println("flist2"+flist2.get(i).getCateName());

            //            根据商品id查抄商品图片
            List<ProductImage> pImg=productImgServerice.selectImgByPrimaryKey(p.getProductId(),p.getSellerId());

//            System.out.println("查询产品的ID："+p.getProductId()+"用户ID："+p.getSellerId());
//            System.out.println("pImg："+pImg);
//            System.out.println("pImg.size："+pImg.size());
            if(pImg.size() == 0){//当没有查询到图片的情况
                ProductImage pImg3=new ProductImage();
                pImg3.setImgNumber("没有图片.jpg");
                pImg.add(pImg3);
                pImg.add(pImg3);
                pImg.add(pImg3);
                NoPImglist=pImg;
                PImglist.addAll(NoPImglist);
//                System.out.println("没有图片的对象pImg"+pImg.toString());
//                System.out.println("发送完毕！！！！！！！！！！！");
//                session.setAttribute("ProdoctImage",PImglist);
            }else {
                PImglist.addAll(pImg);
//                list1 = list2.subList(start, end); start,end分别是第几个到第几个。

            }

//            System.out.println("FinishPImglist:"+FinishPImglist.size()+"---FinishPImglist:"+FinishPImglist.toString());
            FinishPImglist=PImglist.subList(a,(b+1));
//            System.out.println("FinishPImglist:"+FinishPImglist.size()+"---FinishPImglist:"+FinishPImglist.toString());
//            System.out.println("a:"+a+"---b:"+(b+1));
//            System.out.println("图片的对象PImglist"+PImglist.subList(a,b).toString()+PImglist.size()+"-------------第几遍i:"+i);

            session.setAttribute("FinishPImglist",FinishPImglist);
//            for(int q=0;q<FinishPImglist.size();q++){
//                ProductImage pp=new ProductImage();
//                pp=FinishPImglist.get(q);
//                System.out.println(pp.getImgNumber());
//            }
//            for(int q=0;q<PImglist.size();q++){
//                ProductImage pp=new ProductImage();
//                pp=PImglist.get(q);
//                System.out.println(pp.getImgNumber());
//            }
            a=a+3;
            b=b+3;
//            System.out.println("a:"+a+"b"+(b+1));

        }

        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面

    }
    //根据id查找商品
    @GetMapping("/selectByPrimaryKey")
    public ModelAndView product(Integer id,
                                @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        System.out.println("进入链接");
        Product list = productService.selectByPrimaryKey(id);
        System.out.println(list.getSellerId());
//        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
//        System.out.println(pageInfo);
        mv.addObject("pageInfo",list);
        System.out.println(list);
        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面
    }
    //后台接受值的方法
    //根据id查询父类中的子类
    @RequestMapping("/getCList")
    @ResponseBody
    public List<com.springboot.model.Cate> getCList(HttpSession session, Integer a,
                                                    @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                                    @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){

        System.out.println("输入的父类id："+a);
        int cateParentId=a;
//        List<String> listCateFid;
        //根据id查询子类id数列（）
        List<Cate> CateCilList=cateService.selectCateClist(cateParentId);
        System.out.println("CateCilList.size:"+CateCilList.size());
        if (CateCilList.size()!=0){
            for(int i=0;i<=CateCilList.size();i++){
//            System.out.println("查询子类名字：clistName:"+clist.getCateName()+"   |查询子类ID：clistId:"+clist.getCateId()+"   |clistParentId:"+clist.getCateParentId());
                System.out.println("CateCilList:"+CateCilList.get(i));
                Cate cliList=new Cate();
                cliList=CateCilList.get(i);
                session.setAttribute("cliList",cliList);
                return CateCilList;
            }
        }else {
            System.out.println("没有子类目录!");
        }

        return null;//返回结果给前端页面，并且是没有父类的情况下
    }
    //增加商品
    @GetMapping("/addPro")
    public ModelAndView addPro(HttpSession session,Model model,
                               @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                               @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize,
             @RequestParam(name = "productCid",required = true,defaultValue = "0") int productCid){
        ModelAndView mv=new ModelAndView();
        //暂时不做，这是增加商品页面的时候加载分类使用的
        //查询商品中的fid和cid
//        select distinct product_fid from proudct
//        select distinct product_cid from proudct
        //select * from cate_gory where cate_parent_id!=0;
        //select * from cate_gory where cate_parent_id=0;

        List<String> listFid = productService.selectFid();
        List<String> listCid = productService.selectCid();
        List<String> listCateFid=cateService.selectCateFid();//查询分类目录的一级目录

        System.out.println("listCateFid:"+listCateFid);
        session.setAttribute("listCateFid", listCateFid);
//        List<String> listCateCid=cateService.selectCateCid();//查询分类目录的二级目录
        List<com.springboot.model.Cate> listFname =new ArrayList<>();
        session.setAttribute("listFid", listFid);
        session.setAttribute("listCid", listCid);
        //查询一级目录
//        select * from cate_gory where cate_parent_id=0;
//        List<String> listOne=productService.selectOne();

        //尝试遍历出阿莱
        //首先查询父类
        for(int i=0;i<listFid.size()-1;i++){//切记这里离要减一！！！！！
            //根据fid和子id去分类查询分类名字
            com.springboot.model.Cate a=new Cate();
            if(listFid.get(i)!=null){
                int cateId=Integer.valueOf(listFid.get(i));
                a.setCateId(Integer.valueOf(cateId));
                //先查询父类的id（不重复）,根绝父类id查找子类
                System.out.println("这是查询的数字："+listFid.get(i));
                mv.addObject("pageInfo",listFid.get(i));
            }
            com.springboot.model.Cate flist=new Cate();
            flist=cateService.selectByPrimaryKey(a.getCateId());
            listFname.add(flist);
            session.setAttribute("listFname", listFname);
        }
        mv.setViewName("productAdd.html");
        return mv;//返回结果给前端页面
    }
//    @GetMapping("/productAdd")
    @Value("${file2.path}")
    private String file2Path;
    @RequestMapping(value = "/productAdd",method = RequestMethod.POST)
    public ModelAndView productAdd(String productName ,Integer productFid,
                                   String productBiaoqian,String productDetail,MultipartFile[] file,Integer productNumber,
                                   Integer productPrice,String productAddress,String productKuaidi,Integer sellerId,@RequestParam(name = "productCid",required = true,defaultValue = "0") int productCid) throws IOException {
        ModelAndView mv=new ModelAndView();
        int productTupian = 0;//初始化的图片变量值
        int yuliuId=0;//预留的产品id的变量值
//        System.out.println(productName+productCid+productFid+productBiaoqian+productDetail+productTupian+
//                productNumber+productPrice+productAddress+productKuaidi+sellerId);
        productService.productAdd(productName,productCid,productFid,productBiaoqian,productDetail,productTupian,
                productNumber,productPrice,productAddress,productKuaidi,sellerId);

        //存储图片开始
        int i=0;
        for (MultipartFile file2 : file) {    //循环保存文件
            System.out.println("file2.getName:"+file2.getOriginalFilename());
//            System.out.println("file2.getSize:"+file2.getSize());
//            System.out.println("file2.file2Path:"+file2Path);//存储路径
//            String extName=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
//            String fileName= UUID.randomUUID().toString()+extName;
//            String imgName=file2.getOriginalFilename();

            Map<String,Object> result=new HashMap<String, Object>();//一个文件上传的结果

            if (file2.getSize() / 1000 > 1000){//图片大小不能超过1000KB
//                result_msg="图片大小不能超过1000KB";
                System.out.println("图片太大:"+file2.getSize() / 1000);
            }
            else{
                //判断上传文件格式
                String fileType = file2.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                    // 要上传的目标文件存放的绝对路径
//                    final String localPath=file2Path;
                    //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                    //获取文件名
                    String fileName = file2.getOriginalFilename();
                    //获取文件后缀名
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    //重新生成文件名
                    fileName = UUID.randomUUID()+suffixName;
//                    System.out.println("fileName:"+fileName);
                    if (FileCopyUtils.copy(file2.getInputStream(),new FileOutputStream(new File(file2Path+fileName)))==0) {
//                        result_msg="图片上传失败";
                        System.out.println("图片上传失败"+file2.getSize());
                    } else {
                        productTupian=0;
                        //文件存放的相对路径(一般存放在数据库用于img标签的src)
                        String relativePath="img/"+fileName;
                        result.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
//                        result_msg="图片上传成功";
                        System.out.println("图片上传成功"+fileName);
//                        System.out.println("file.length:"+(file.length));
                        //存储图片结束
                        //先添加产品信息得到产品的id，保存好产品图片中的id。
//                        存储好之后应该先添加到产品数据表当中
        System.out.println("sellerId"+sellerId+"productTupian"+productTupian+"fileNake:"+fileName);
        i++;
        System.out.println("这是第【"+i+"】个图片");
        if(sellerId!=null){
            System.out.println("sellerId:"+sellerId+"productTupian:"+productTupian);
            Integer productId=productService.selectProductIdBysellerAndProductTuPian(sellerId,productTupian);
            yuliuId=productId;//把查询到的id保存在预留的id上
            if(productId!=null ||productId!=0&& productId.toString()!=null){
                System.out.println("查询到的productId："+productId);

            }else{
                System.out.println("productId放回为空！！！");
            }
                                    productImgServerice.productImgAdd(sellerId,productId,fileName);
        }else{
            System.out.println("SellerId或者产品id为0");
        }
                    }
                }
                else{
//                    result_msg="图片格式不正确";
                }
            }
        }
        System.out.println("yuliuId："+yuliuId);
        productService.updateTupianByProId(yuliuId);//
        mv.addObject("pageInfo","yes");
        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面
    }


    //test
//    @PostMapping("/myUpload")
    @RequestMapping(value = "/myUpload",method = RequestMethod.POST)
    public ModelAndView uploadFile(MultipartFile[] file) throws IOException {
//        log.info(uploadConfig.getBaseUrl());
        ModelAndView mv=new ModelAndView();
        System.out.printf("这是上传的文件:"+file);
        mv.setViewName("img.html");
        return mv;
    }
    @RequestMapping(value = "/img")
    public ModelAndView img(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("img.html");
        return mv;
    }
    @RequestMapping(value = "/img2",method = RequestMethod.POST)
    public ModelAndView img2(MultipartFile[] file) throws IOException {
//        log.info(uploadConfig.getBaseUrl());
//        ModelAndView mv=new ModelAndView();
//        System.out.println("file:"+file.length);
//        System.out.println("file:"+file.toString());
        List<Map<String,Object>> root=new ArrayList<Map<String,Object>>();
        for (MultipartFile file2 : file) {    //循环保存文件
//            System.out.println("file2.getName:"+file2.getOriginalFilename());
//            System.out.println("file2.getSize:"+file2.getSize());
//            System.out.println("file2.file2Path:"+file2Path);
//            String extName=file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
//            String fileName= UUID.randomUUID().toString()+extName;
//            String imgName=file2.getOriginalFilename();

            Map<String,Object> result=new HashMap<String, Object>();//一个文件上传的结果
            String result_msg="";//上传结果信息

            if (file2.getSize() / 1000 > 1000){
                result_msg="图片大小不能超过1000KB";
                System.out.println("图片太大:"+file2.getSize() / 1000);
            }
            else{
                //判断上传文件格式
                String fileType = file2.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                    // 要上传的目标文件存放的绝对路径
//                    final String localPath=file2Path;
                    //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                    //获取文件名
                    String fileName = file2.getOriginalFilename();
                    //获取文件后缀名
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    //重新生成文件名
                    fileName = UUID.randomUUID()+suffixName;
                    System.out.println("fileName:"+fileName);
                    if (FileCopyUtils.copy(file2.getInputStream(),new FileOutputStream(new File(file2Path+fileName)))==0) {
                        result_msg="图片上传失败";
                        System.out.println("图片上传失败"+file2.getSize());
                    } else {
                        //文件存放的相对路径(一般存放在数据库用于img标签的src)
                        String relativePath="img/"+fileName;
                        result.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
                        result_msg="图片上传成功";
                        System.out.println("图片上传成功"+file2.getSize());
                    }
                }
                else{
                    result_msg="图片格式不正确";
                }
            }
        }
//        System.out.println("extName:"+extName);
//        System.out.println("fileName："+fileName);
//        System.out.println("imgName："+imgName);
//        mv.setViewName("productList.html");
        ModelAndView mv=new ModelAndView();
        mv.setViewName("productAdd.html");
        return mv;
    }
    //商品添加图片
//    @PostMapping("/")
    @RequestMapping(value = "/multipleImageUpload",method = RequestMethod.POST)
    public ModelAndView multipleImageUpload(MultipartFile[] file) throws IOException {
        System.out.println("上传的图片数："+file.length);

        List<Map<String,Object>> root=new ArrayList<Map<String,Object>>();

        for (MultipartFile file1 : file) {    //循环保存文件

            Map<String,Object> result=new HashMap<String, Object>();//一个文件上传的结果
            String result_msg="";//上传结果信息

            if (file1.getSize() / 1000 > 1000){
                result_msg="图片大小不能超过1000KB";
            }
            else{
                //判断上传文件格式
                String fileType = file1.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                    // 要上传的目标文件存放的绝对路径
//                    final String localPath=file2Path;
                    //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                    //获取文件名
                    String fileName = file1.getOriginalFilename();
                    //获取文件后缀名
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    //重新生成文件名
                    fileName = UUID.randomUUID()+suffixName;
                    if (FileCopyUtils.copy(file1.getInputStream(),new FileOutputStream(new File(file2Path+fileName)))==0) {
                        result_msg="图片上传失败";
                    } else {
                        //文件存放的相对路径(一般存放在数据库用于img标签的src)
                        String relativePath="img/"+fileName;
                        result.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
                        result_msg="图片上传成功";
                    }
                }
                else{
                    result_msg="图片格式不正确";
                }
            }
            result.put("result_msg",result_msg);
            root.add(result);
        }
//        session.setAttribute("root_json", root);
//        String root_json=JSON.toJSONString(root);
//        System.out.println(root_json);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index.html");
        return mv;//返回结果给前端页面
    }
    //根据id删除商品
    @GetMapping("/deleteByProductIdKey")
    public ModelAndView deleteByPrimaryKey(Integer id,Integer sellerId){
        ModelAndView mv=new ModelAndView();
        System.out.println("进入链接");
        //删除产品
        productService.deleteByPrimaryKey(id);
        int productId=id;
        System.out.println("productId！:"+productId);
        List<ProductImage> pImg=productImgServerice.selectImgByPrimaryKey(productId,sellerId);
        //1.先去删除图片根据文件路径和文件名
        for(int k=0;k<pImg.size();k++){
            ProductImage p=new ProductImage();
            p.setImgNumber(pImg.get(k).getImgNumber());
            try {
                java.io.File myDelFile = new java.io.File(file2Path+p.getImgNumber());
                myDelFile.delete();
                System.out.println("删除原图片成功！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //删除产品图片
        productImgServerice.deleteByPrimaryKey(id);

        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面
    }
    //跳转修改商品页面
    @GetMapping("/upPro")
    public ModelAndView upPro(HttpSession session,Model model,Integer id,int sellerId,
                              @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                              @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize){
        ModelAndView mv=new ModelAndView();
        System.out.println("进入链接");
        Product product=productService.selectByPrimaryKey(id);
        List<String> listFid = productService.selectFid();
        List<String> listCid = productService.selectCid();
        List<String> listCateFid=cateService.selectCateFid();//查询分类目录的一级目录

        System.out.println("listCateFid:"+listCateFid);
        session.setAttribute("listCateFid", listCateFid);
//        List<String> listCateCid=cateService.selectCateCid();//查询分类目录的二级目录
        List<com.springboot.model.Cate> listFname =new ArrayList<>();
        session.setAttribute("listFid", listFid);
        session.setAttribute("listCid", listCid);
        //查询一级目录
//        select * from cate_gory where cate_parent_id=0;
//        List<String> listOne=productService.selectOne();

        //尝试遍历出阿莱
        //首先查询父类
        for(int i=0;i<listFid.size()-1;i++){//切记这里离要减一！！！！！
            //根据fid和子id去分类查询分类名字
            com.springboot.model.Cate a=new Cate();
            if(listFid.get(i)!=null){
                int cateId=Integer.valueOf(listFid.get(i));
                a.setCateId(Integer.valueOf(cateId));
                //先查询父类的id（不重复）,根绝父类id查找子类
                System.out.println("这是查询的数字："+listFid.get(i));
                mv.addObject("pageInfo",listFid.get(i));
            }
            com.springboot.model.Cate flist=new Cate();
            flist=cateService.selectByPrimaryKey(a.getCateId());
            listFname.add(flist);
            session.setAttribute("listFname", listFname);
        }
        Product p=new Product();
        p.setSellerId(sellerId);
        p.setProductId(id);
        List<ProductImage> pImg=productImgServerice.selectImgByPrimaryKey(p.getProductId(),p.getSellerId());
        session.setAttribute("FinishPImglist",pImg);
        System.out.println(pImg.size());
        mv.addObject("product",product);
        mv.setViewName("upDateProduct.html");
        return mv;//返回结果给前端页面
    }
    //根据id修改商品
//    @PostMapping("/updateByPrimaryKeySelective")
    @RequestMapping(value = "/updateByPrimaryKeySelective",method = RequestMethod.POST)
    public ModelAndView updateByPrimaryKeySelective(HttpSession session,Integer productId,String productName,Integer productCid,Integer productFid,
                                                    String productBiaoqian,String productDetail,MultipartFile[] file, Integer productNumber,
                                                    Integer productPrice,String productAddress,String productKuaidi,Integer sellerId,
                                                    @RequestParam(name = "pageNum",required = true,defaultValue = "1") int pageNum,
                                                    @RequestParam(name = "pageSize",required = true,defaultValue = "10") int pageSize) throws IOException {

        int productTupian=0;
        int yuliuId=0;
        int i=0;
        productService.updateByPrimaryKeySelective(productId,productName,productCid,productFid,productBiaoqian,productDetail,
                productNumber,productPrice,productAddress,productKuaidi,sellerId);
        //1.先查看是否有图片，如果没就添加，如果有，就修改，添加修改的服务
        List<ProductImage> pImg=productImgServerice.selectImgByPrimaryKey(productId,sellerId);
        System.out.println("pImg.size():"+pImg.size());
        System.out.println("file.length():"+file.length==null);
        if(pImg.size() ==0){
//            int i=0;
            System.out.println("i:"+i);
            for (MultipartFile file3 : file) {    //循环保存文件
                System.out.println("file3.getName:"+file3.getOriginalFilename());
                Map<String,Object> result=new HashMap<String, Object>();//一个文件上传的结果
                if (file3.getSize() / 1000 > 1000){//图片大小不能超过1000KB
                    System.out.println("图片太大:"+file3.getSize() / 1000);
                }
                else{
                    //判断上传文件格式
                    String fileType = file3.getContentType();
                    if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                        // 要上传的目标文件存放的绝对路径
//                    final String localPath=file2Path;
                        //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                        //获取文件名
                        String fileName = file3.getOriginalFilename();
                        //获取文件后缀名
                        String suffixName = fileName.substring(fileName.lastIndexOf("."));
                        //重新生成文件名
                        fileName = UUID.randomUUID()+suffixName;
//                    System.out.println("fileName:"+fileName);
                        if (FileCopyUtils.copy(file3.getInputStream(),new FileOutputStream(new File(file2Path+fileName)))==0) {
//                        result_msg="图片上传失败";
                            System.out.println("图片上传失败"+file3.getSize());
                        } else {
                            productTupian=0;
                            //文件存放的相对路径(一般存放在数据库用于img标签的src)
                            String relativePath="img/"+fileName;
                            result.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
                            System.out.println("图片上传成功"+fileName);
                            //存储图片结束
                            //先添加产品信息得到产品的id，保存好产品图片中的id。
                            //存储好之后应该先添加到产品数据表当中
                            i++;
                                yuliuId=productId;//把查询到的id保存在预留的id上
                                productImgServerice.productImgAdd(sellerId,productId,fileName);
                            }
                        }

                }
            }
            System.out.println("yuliuId："+yuliuId);
            productService.updateTupianByProId(yuliuId);//
        }else{ System.out.println("有图片就修改图片信息,图片数量："+pImg.size());

            //有图片就修改图片信息
            //        updateByPrimaryKeySelective 通过img_id修改图片信息
            //1.先去删除图片根据文件路径和文件名
            for(int k=0;k<pImg.size();k++){
                ProductImage p=new ProductImage();
                p.setImgNumber(pImg.get(k).getImgNumber());
                try {
                    java.io.File myDelFile = new java.io.File(file2Path+p.getImgNumber());
                    myDelFile.delete();
                    System.out.println("删除原图片成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            //2.去产品图片表中修改数据
            //        productImgServerice.updataBySellerAndProductId(sellerId,productId,);

            for (MultipartFile file3 : file) {    //循环保存文件

                System.out.println("file3.getName:"+file3.getOriginalFilename());
                Map<String,Object> result=new HashMap<String, Object>();//一个文件上传的结果
                if (file3.getSize() / 1000 > 1000){//图片大小不能超过1000KB
                    System.out.println("图片太大:"+file3.getSize() / 1000);
                }
                else{
                    //判断上传文件格式
                    String fileType = file3.getContentType();
                    if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                        // 要上传的目标文件存放的绝对路径
//                    final String localPath=file2Path;
                        //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
                        //获取文件名
                        String fileName = file3.getOriginalFilename();
                        //获取文件后缀名
                        String suffixName = fileName.substring(fileName.lastIndexOf("."));
                        //重新生成文件名
                        fileName = UUID.randomUUID()+suffixName;

//                    System.out.println("fileName:"+fileName);
                        if (FileCopyUtils.copy(file3.getInputStream(),new FileOutputStream(new File(file2Path+fileName)))==0) {
//                        result_msg="图片上传失败";
                            System.out.println("图片上传失败"+file3.getSize());
                        } else {
                            //文件存放的相对路径(一般存放在数据库用于img标签的src)
                            String relativePath="img/"+fileName;
                            result.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
                            System.out.println("图片上传成功"+fileName);
                            //存储图片结束
                            String ImgNumber=fileName;
                            ProductImage p=new ProductImage();
                            p.setImgNumber(fileName);
                            p.setImgId(pImg.get(i).getImgId());
                            System.out.println("pImg的id"+p.getImgId());
                            int Res=productImgServerice.updateBySellerAndProductId(sellerId,productId,p.getImgNumber(),p.getImgId());//id也要查出来
                            System.out.println("res:"+Res);
                            i++;
                        }

                    }

                }
            }
            System.out.println("yuliuId："+yuliuId);
            productService.updateTupianByProId(yuliuId);//
        }
        ModelAndView mv=new ModelAndView();
        mv.setViewName("productList.html");
        return mv;//返回结果给前端页面
    }
}
