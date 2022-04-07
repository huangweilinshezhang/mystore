package com.springboot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.model.PageRequest;
import com.springboot.model.User;
//import com.springboot.service.UserServerice;
import com.springboot.model.UserAddress;
import com.springboot.service.UserAddressService;
import com.springboot.service.UserServervice;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
//@RestController
//@RequestMapping("user")
public class UserController {
    @Autowired//把数据往Java容器中注入
    private UserServervice userServervice;
//    private User user;
    @Autowired//把数据往Java容器中注入
    private UserAddressService userAddressService;
    //用户登录http://localhost:8085/userLogin?id=1&password='123456'
    @RequestMapping(value = "/userindex")
    public ModelAndView userIndex(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("userindex.html");
//        mv.addObject("userStaus","主页面");
        return mv;
    }
    @RequestMapping(value = "/about")
    public ModelAndView about(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("about.html");
//        mv.addObject("userStaus","主页面");
        return mv;
    }
    @RequestMapping(value = "/index")
    public ModelAndView html(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index.html");
//        mv.addObject("userStaus","主页面");
        return mv;
    }
    @RequestMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login.html");
//        mv.addObject("userStaus","主页面");
        return mv;
    }
    //用户登录http://localhost:8085/userLogin?id=1&password='1'
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public Object login(HttpSession session,Integer id, String password){
        ModelAndView mv=new ModelAndView();


        User user1=userServervice.selectUserByIdTo(id);
        System.out.println("user:"+user1.getUserStat()+user1.getUserNumber());
        int Staus=user1.getUserPassword().compareTo(password);//（因为密码当中String）比较的是字符串不能用逻辑符 使用函数compareTo()函数 0：相同 1：不相同
//        System.out.println("Staus:"+Staus);
        if(Staus==0){
            mv.setViewName("index.html");
            session.setAttribute("isLogin", "1");
            session.setAttribute("user", user1);
            String isLogin= (String) session.getAttribute("isLogin");
            System.out.println("session有效期是：---->"+session.getMaxInactiveInterval());
            System.out.println("isLogin:"+isLogin);

        }else {
            mv.setViewName("index.html");
        }

        return "/index";
    }
    @RequestMapping(value = "/layout")
    public ModelAndView layout(HttpSession session){
        ModelAndView mv=new ModelAndView();
        session.removeAttribute("isLogin");
        mv.setViewName("index.html");
//        mv.addObject("userStaus","主页面");
        String isLogin= (String) session.getAttribute("isLogin");
        System.out.println("isLogin:"+isLogin);
        return mv;
    }

    //根据用户id查看用户http://localhost:8085/user
    @RequestMapping(value = "/user")
    public ModelAndView  user(Model model, Integer id){
        ModelAndView mv=new ModelAndView();
        User user=userServervice.selectUserByIdTo(id);
//        System.out.println(user.getUserId());
//        System.out.println(user.getUserName());
//        System.out.println(user.getUserSex());
        if(user!=null){
            mv.addObject("user",user);
        }else{
            mv.addObject("user","没有这个用户");
        }
        mv.setViewName("test.html");
        return mv;//返回结果给前端页面
    }
    //查看用户列表http://localhost:8085/userList
    @RequestMapping(value="/userList")
    public @ResponseBody ModelAndView userList(Model model,Integer pageNum,Integer pageSize){
        ModelAndView mv=new ModelAndView();
        List<User> userList=new ArrayList<>();
//        Integer userNumber=userServervice.queryUserNumber();
//        System.out.println("userNumber:"+userNumber);
        User user=userServervice.selectUserList(pageNum,pageSize);
        userList.add(user);
        System.out.println(userList);
//        model.addAttribute("userList",userList);
        mv.addObject("userList",userList);
        mv.setViewName("userList.html");
        return mv;//返回结果给前端页面
    }

    //根据id删除用户
    @RequestMapping(value = "/deleteByUserIdKey")
    public ModelAndView deletetUser(Model model,Integer id){
        Integer deleteStaus=userServervice.queryUserNumber();
//        User u=userServervice.selectUserByIdTo(id);
        System.out.println(deleteStaus);
        ModelAndView mv=new ModelAndView();

        if(deleteStaus>=1){
            userServervice.deleteByUserIdKey(id);//删除用户信息
            userAddressService.deleteByUserId(id);//删除地址表中的地址
//            String delete="用户删除成功";
//            System.out.println(delete);
            model.addAttribute("userData","delete");
        }
        if(deleteStaus == null){
            String deleteFail="删除失败没有这个用户";
            model.addAttribute("userData","deleteFail");
            System.out.println(deleteFail);
        }
        mv.setViewName("userList.html");
        return mv;
    }
    @RequestMapping(value = "/upUser")
    public ModelAndView upUser(Integer id){
        ModelAndView mv=new ModelAndView();
        User user=userServervice.selectUserByIdTo(id);
        if(user!=null){
            mv.addObject("user",user);
        }else{

            mv.addObject("user","没有这个用户");
        }
        System.out.println("user:"+user);
        mv.setViewName("upDateUser.html");
        return mv;
    }

    @Value("${file.path}")
    private String filePath;
    //根据用户id修改用户数据
    @RequestMapping(value = "/upDateUser",method = RequestMethod.POST)
    public ModelAndView upDateUser(Integer userId,MultipartFile file,String userName,String userPassword,Integer userAge,
                                   String userEmail,Integer userSex,String userNumber,String address){
        ModelAndView mv=new ModelAndView();
        //判断修改的时候先图片名字是否越界
        System.out.println(file.getOriginalFilename().lastIndexOf("."));
        if(file.getOriginalFilename().lastIndexOf(".") == -1){
            String extName="3";
            String fileName= UUID.randomUUID().toString()+extName;

            String imgName=file.getOriginalFilename();
            String moren="3.jpg";
            System.out.println("imgName："+imgName);
            System.out.println("userId："+userId);

            //判断是否为默认图像
            if(imgName.compareTo(moren)==0){
                System.out.println("imgName："+imgName);
                System.out.println("moren："+moren);
                System.out.println("file.getOriginalFilename():"+file.getOriginalFilename());
                //用id获取原本的头像文件路径，并且根据路径去文件中删除图片
                fileName=imgName;
            }else{
                System.out.println("不是默认图片");
                User user1=userServervice.selectUserByIdTo(userId);
                System.out.println(user1);

                //删除文件夹中的图片
                String oldImg=user1.getUserTouxiang();
                System.out.println("file1:路径"+filePath+oldImg);
                File file1=new File(filePath+oldImg);
                System.out.println("file1:路径"+filePath+user1.getUserTouxiang());
                file1.delete();
                System.out.println("原本用户头像图片删除成功:"+user1.getUserTouxiang());

                System.out.println("file.getOriginalFilename():"+file.getOriginalFilename());
                try {
                    FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(new File(filePath+fileName)));
                    System.out.println("图片存储成功");

                } catch (IOException e) {
                    System.out.println("抛出异常:"+fileName);
                }
            }
            String userTouxiang=fileName;
            System.out.println("userNumber:"+userNumber);
            //修改页面应当先是修改完成地址获得id之后返回id，再去修改用户表
//            String userAddressId=address;
            int userStaus=1;
            UserAddress UAdd=new UserAddress();
            String userTel=userNumber;
            userAddressService.updateByPrimaryKey(userTel,address,userId,userStaus);
            UAdd=userAddressService.selectByUserIdAnduserStaus(userId,userStaus);
            int userAddressId=UAdd.getAddressId();
            userServervice.updateByPrimaryKey(userId,userTouxiang,userName,userPassword,userAge,userEmail,userSex,userNumber,userAddressId);

        }else{
            String extName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName= UUID.randomUUID().toString()+extName;

            String imgName=file.getOriginalFilename();
            String moren="3.jpg";
            System.out.println("imgName："+imgName);
            System.out.println("userId："+userId);

            //判断是否为默认图像
            if(imgName.compareTo(moren)==0){
                System.out.println("imgName："+imgName);
                System.out.println("moren："+moren);
                System.out.println("file.getOriginalFilename():"+file.getOriginalFilename());
                //用id获取原本的头像文件路径，并且根据路径去文件中删除图片
                fileName=imgName;
            }else{
                System.out.println("不是默认图片");
                User user1=userServervice.selectUserByIdTo(userId);
                System.out.println(user1);

                //删除文件夹中的图片
                String oldImg=user1.getUserTouxiang();
                System.out.println("file1:路径"+filePath+oldImg);
                File file1=new File(filePath+oldImg);
                System.out.println("file1:路径"+filePath+user1.getUserTouxiang());
                file1.delete();
                System.out.println("原本用户头像图片删除成功:"+user1.getUserTouxiang());

                System.out.println("file.getOriginalFilename():"+file.getOriginalFilename());
                try {
                    FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(new File(filePath+fileName)));
                    System.out.println("图片存储成功");

                } catch (IOException e) {
                    System.out.println("抛出异常:"+fileName);
                }
            }
            String userTouxiang=fileName;
            System.out.println("userNumber2222:"+userNumber);
            int userStaus=1;
            UserAddress UAdd=new UserAddress();
            String userTel=userNumber;
            userAddressService.updateByPrimaryKey(userTel,address,userId,userStaus);
            UAdd=userAddressService.selectByUserIdAnduserStaus(userId,userStaus);
            int userAddressId=UAdd.getAddressId();
            userServervice.updateByPrimaryKey(userId,userTouxiang,userName,userPassword,userAge,userEmail,userSex,userNumber,userAddressId);
        }
//       model.addAttribute("updateByPrimaryKey","修改成功");
        mv.setViewName("upDateUser.html");
        return mv;
    }

    //前端进入页面接口，注册页面
    @RequestMapping(value = "/reg")
    public ModelAndView userAdd(HttpSession session){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("reg.html");
//        mv.addObject("userStaus","主页面");
        //过滤器验证码输入开始
//        HttpSession session=req.getSession();
//        String verycode=req.getParameter("veryCode");
        String sysCode=(String)session.getAttribute("code");
        mv.addObject("sysCode",sysCode);
        return mv;
    }
    //增加用户,应当添加默认头像图片
    @RequestMapping(value = "/userAdd")
    public ModelAndView userAdd( String userName, String userPassword, Integer touxiang, Integer userAge, String userEmail,
                                 Integer userSex, String userNumber, String address,
                                 @RequestParam(name = "userStat",required = true,defaultValue = "0") Integer userStat){
//        Integer userId=userServervice.queryUserNumber();
        String userTouxiang="3.jpg";
//        int userStat=0;
        System.out.println("userStat:"+userStat);
        //地址栏增加地址信息，并且返回地址的id
        Integer userAddressId=0;
        String userTel=userNumber;
        String consignee="ok";
        int addressStaus=1;
        int userId;
        List<UserAddress> UA=new ArrayList<>();

        userServervice.userAdd(userName,userTouxiang,userPassword,userAge,userEmail,userSex,userNumber,userAddressId,userStat);
        User u1=userServervice.selectUserByEmailAndPwd(userEmail,userPassword);
        userId=u1.getUserId();
        int s= userAddressService.insertSelective(userId,consignee,address,addressStaus,userTel);
//        根绝用户id和用户staus修改用户表中的addressId
        UA=userAddressService.selectByUserId(userId);
        System.out.println("UA:"+UA.size());
        if(UA.size()>0){
            for(int i=0;i<UA.size();i++){
                int addressId=UA.get(i).getAddressId();
                userServervice.updateUserAddress(addressId,userId,userStat);
            }
        }
//        查询地址表格中的地址id和用户id

//        System.out.println("UserAddId:"+u1.getUserId());
        ModelAndView mv=new ModelAndView();
//        mv.addObject("userStaus","登录成功");
        mv.setViewName("/login");
        return mv;
    }

    //验证码阶段
    @RequestMapping(value = "/getcode")
    public void getcode(HttpSession session,HttpServletResponse resp){
        ModelAndView mv=new ModelAndView();
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
        // 将四位数字的验证码保存到Session中。
        session.setAttribute("code", codeMap.get("code").toString());
//        System.out.println("code:"+session.getAttribute("code"));
//        session.setAttribute("code", codeMap.get("code").toString());
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        mv.setViewName("userindex.html");
//        mv.addObject("userStaus","主页面");
        return ;
    }
    @RequestMapping(value = "checkusernum")
    public void checkusernum(HttpSession session,HttpServletResponse resp,String num) throws IOException {
//        ModelAndView mv=new ModelAndView();
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
        // 将四位数字的验证码保存到Session中。
//        session.setAttribute("code", codeMap.get("code").toString());
        String sysCode=(String)session.getAttribute("code");
		System.out.println(sysCode+"###"+num);
        PrintWriter out=resp.getWriter();

        if(sysCode.equals(num)) {
            out.print("true");
        }else {
            out.print("false");
        }
        out.close();
    }



}