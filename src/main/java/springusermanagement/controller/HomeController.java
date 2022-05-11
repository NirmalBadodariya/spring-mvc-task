package springusermanagement.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.validation.constraints.Email;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import springusermanagement.model.UserBean;
import springusermanagement.service.SignupServiceImpl;

@Controller
public class HomeController {
    @Autowired
    private SignupServiceImpl signupServiceImpl;

    @RequestMapping("/")
    public String loginPage() {
        return "index";
    }

    @RequestMapping("/register")
    public String gotoRegister() {
        System.out.println("register");
        return "register";
    }

    @RequestMapping("/forgotpass")
    public String gotoForgotpass() {
        return "forgotpass";
    }

    @RequestMapping(path = "/Signup", method = RequestMethod.POST)
    public String Signup(@ModelAttribute UserBean user, HttpSession session) {
        session.setAttribute("user", user);
        // user.setId(id);
        // System.out.println(user.getEmail());
        if (user.getId() != 0) {

            System.out.println(user.getId());
            signupServiceImpl.updateUser(user);
        } else {
            System.out.println(user);
            signupServiceImpl.addNewUser(user);
        }
        return "userHome";
    }

    @RequestMapping(path = "/Login", method = RequestMethod.POST)
    public String Login(@RequestParam String email, @RequestParam String pass, HttpSession session) {
        boolean isUserFound = signupServiceImpl.checkUser(email, pass);
        session.setAttribute("email", email);
        if (isUserFound == true) {

            return "userHome";
        } else {
            return "index";
        }
    }

    @RequestMapping(path = "/UsersDetails", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String getUserDetails() {

        ArrayList<UserBean> userDetails = signupServiceImpl.getUserDetails();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(userDetails);
        return JSONObject;
    }

    @RequestMapping(path = "/DeleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestParam int userId) {
        System.out.println("Id:   " + userId);
        try {
            signupServiceImpl.deleteUser(userId);
        } catch (SecurityException | RollbackException | HeuristicMixedException | HeuristicRollbackException
                | SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    @RequestMapping(path = "/EditDetails")
    public String editDetails(HttpSession session) {
        if (session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
            UserBean userDetails = signupServiceImpl.getLoggedinUserDetails(email);
            session.setAttribute("user", userDetails);
        }
        // System.err.println("mail: " + user.getEmail());

        return "redirect:register";
    }

}
