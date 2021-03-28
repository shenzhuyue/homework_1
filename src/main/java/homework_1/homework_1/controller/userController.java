package homework_1.homework_1.controller;

import homework_1.homework_1.model.user;
import homework_1.homework_1.repository.UserRepository;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
//@Tag注解用来定义一组API
@Tag(name = "userController v1",description = "第一次作业的尝试")
@RequestMapping("/userController")
public class userController {
    final UserRepository userRepository;
    @Autowired
    public userController(UserRepository userRepository){this.userRepository=userRepository;}
    private List<user> userList = new ArrayList<user>();


    //@Operation用来对API添加说明
    @Operation(summary = "register", description = "返回注册界面的API")
    //@ApiResponses对返回值添加说明

    @GetMapping("/register")
    public ModelAndView Register(Model model) {
        model.addAttribute("user", new user());
        ModelAndView modelAndView = new ModelAndView("register", "userModel", model);
        return modelAndView;
    }

    //@Operation用来对API添加说明
    @Operation(summary = "register.action", description = "提交注册信息的API")
    @PostMapping("register.action")
    public String register(user temp) {
        userList.add(temp);
        userRepository.save(temp);
        return "redirect:/login";
    }

    //@Operation用来对API添加说明
    @Operation(summary = "login", description = "返回登陆界面的API")
    @GetMapping("/login")
    public ModelAndView Login(Model model) {
        return new ModelAndView("/login");
    }


    //@Operation用来对API添加说明
    @Operation(summary = "login.action", description = "提交登录信息的API")
    @PostMapping("/login.action")
    public String Login(HttpServletRequest request, HttpServletResponse response, @RequestParam("admin") String admin, @RequestParam("password") String password) throws IOException {
        user User = new user();
        User.setUsername(admin);
        User.setPassword(password);
        user loginuser = userRepository.findByUsernameAndAndPassword(User.getUsername(), User.getPassword());
        HttpSession session = request.getSession();
        if (loginuser == null) {
            session.setAttribute("flag", 0);
            session.setAttribute("loggedin",false);
            return "/login";
        } else {

            session.setAttribute("user", admin);
            session.setAttribute("flag", 1);
            session.setAttribute("loggedin",true);
            return "redirect:/mainPage";
        }
    }

    @Operation(summary = "users",description = "获得用户信息的API")
    @GetMapping("/users")
    public ModelAndView users(Model model) {
        return new ModelAndView("/users");
    }

    @Operation(summary = "mianPage",description = "好友动态列表的API")
    @GetMapping("/mianPage")
    public ModelAndView mainPage(Model model) {
        return new ModelAndView("/mainPage");
    }


    @Operation(summary = "active",description = "获得动态发表页面的API")
    @GetMapping("/active")
    public ModelAndView avtive(Model model) {
        return new ModelAndView("/active");
    }

    @Operation(summary = "active.action",description = "发表动态的API")
    @PostMapping("/active.action")
    public String active_cation(user temp) {
        userList.add(temp);
        userRepository.save(temp);
        return "redirect:/mainPage";
    }

    @Operation(summary = "friendList",description = "获得好友列表的API")
    @GetMapping("/friendList")
    public ModelAndView friendList(Model model) {
        return new ModelAndView("/friendList");
    }

    @Operation(summary = "friendSearch",description = "搜索好友的API")//在这个页面不管是不是好友都可以搜索到，并选择添加非好友的user为好友
    @GetMapping("/friendSearch")
    public ModelAndView friendSearch(Model model) {
        return new ModelAndView("/friendSearch");
    }



}
