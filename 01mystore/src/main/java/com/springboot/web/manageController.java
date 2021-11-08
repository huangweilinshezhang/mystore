package com.springboot.web;

import com.springboot.model.User;
import com.springboot.service.UserServervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class manageController {
    @Autowired
    private UserServervice userServervice;

//    @RequestMapping(value = "/userList1")
//    public ModelAndView userManage(){
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("userManage.html");
////        mv.addObject("userStaus","主页面");
//        return mv;
//    }
    //查看用户列表http://localhost:8085/userList1
    @RequestMapping(value="/userList1")
    public @ResponseBody
    ModelAndView userList(Model model){
        ModelAndView mv=new ModelAndView();
        List<User> userList=new ArrayList<>();
        Integer userNumber=userServervice.queryUserNumber();
        System.out.println("userNumber:"+userNumber);
//        User user=userServervice.selectUserList();
        for(int i=0;i<userNumber;i++){
            User user=new User();
            User user1=userServervice.selectUserByIdTo(i+1);
            user.setUserId(user1.getUserId());
            user.setUserName(user1.getUserName());
            user.setUserAge(user1.getUserAge());
            user.setUserTouxiang(user1.getUserTouxiang());
            user.setUserAddressId(user1.getUserAddressId());
            user.setUserEmail(user1.getUserEmail());
            user.setUserNumber(user1.getUserNumber());
            user.setUserPassword(user1.getUserPassword());
            user.setUserSex(user1.getUserSex());

//            System.out.println("i:"+i+user.getUserId()+user.getUserName()+user.getUserTouxiang());
            userList.add(user);
            System.out.println(user);
        }
        System.out.println(userList);
//        model.addAttribute("userList",userList);
        mv.addObject("userList1",userList);
        mv.setViewName("userManage.html");
        return mv;//返回结果给前端页面
    }


}
