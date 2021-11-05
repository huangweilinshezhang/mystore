package com.springboot.web;

import com.springboot.model.User;
import com.springboot.service.UserServervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;
@Controller
public class fileController {

    @Value("${file.path}")
    private String filePath;
    private UserServervice userServervice;
//增加用户的头像的图片
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
//    @Autowired
//把数据往Java容器中注入
    public ModelAndView upload(Integer userId,MultipartFile file){
        ModelAndView mv=new ModelAndView();
        User user=new User();
        String extName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName= UUID.randomUUID().toString()+extName;
        try {
            FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(new File(filePath+fileName)));
        } catch (IOException e) {
            System.out.println("抛出异常:"+fileName);
        }
        System.out.println("fileName:"+fileName);
        String userTouxiang=fileName;
        System.out.println("userId"+userId);
        System.out.println("userTouxiang："+userTouxiang);
        //用id获取原本的头像文件路径，并且根据路径去文件中删除图片
        User user1=userServervice.selectUserByIdTo(userId);
        //删除文件夹中的图片
        File file1=new File(filePath+user1.getUserTouxiang());
        System.out.println("file1:路径"+filePath+user1.getUserTouxiang());
        file1.delete();
        System.out.println("原本用户头像图片删除成功");
        userServervice.upUserTouxiang(userId,userTouxiang);
        mv.addObject("userImage","上传图片失败");
        mv.setViewName("test.html");
        return mv;
//        System.out.println("imgUrl:"+imgUrl);
    }

//    @RequestMapping(value = "/selectImg")
//    @ResponseBody
//    public ModelAndView selectImg() throws FileNotFoundException {
//        String imgUrl="C:\\Users\\huangweilin\\Desktop\\01mystore\\src\\main\\resources\\static\\img\\userImage\\c8fd4eda-325f-458c-9444-e77404887860.jpg";
//        ModelAndView mv=new ModelAndView();
//        File img=new File("imgUrl");
//        OutputStream outputStream = new FileOutputStream(imgUrl);
//        mv.addObject("userImage","outputStream");
//        return mv;
//    }
@RequestMapping("/toFindImg")
public void picToJSP(HttpServletRequest request, HttpServletResponse response){
    FileInputStream in;
    response.setContentType("application/octet-stream;charset=UTF-8");
    try {
        //图片读取路径
        String imgUrl="C:\\Users\\huangweilin\\Desktop\\01mystore\\src\\main\\resources\\static\\img\\userImage\\1.jpg";

        in=new FileInputStream(imgUrl);
        int i=in.available();
        byte[]data=new byte[i];
        in.read(data);
        in.close();
        //写图片
        OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
