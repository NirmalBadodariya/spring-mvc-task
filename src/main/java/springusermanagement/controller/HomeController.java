package springusermanagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.validation.Valid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import springusermanagement.model.AddressBean;
import springusermanagement.model.ForgotPassBean;
import springusermanagement.model.UserBean;
import springusermanagement.model.UserRoles;
import springusermanagement.service.SignupService;

@Controller
public class HomeController {
    Logger log = Logger.getLogger(HomeController.class.getName());

    @Autowired
    private SignupService signupServiceImpl;

    @RequestMapping(path = { "/", "/index" })
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

    @RequestMapping({ "/userHome", "/backToHome" })
    public String userHome(HttpSession session) {
        if ((int) session.getAttribute("role") == 0) {
            return "userHome";
        } else {
            return "index";
        }
    }

    @RequestMapping("/adminHome")
    public String adminHome(HttpSession session) {
        if ((int) session.getAttribute("role") == 2 || session.getAttribute("role") == null) {
            return "adminHome";
        } else {
            return "index";
        }
    }

    @RequestMapping(path = "/Signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute UserBean user, BindingResult bindingResult, HttpSession session,
            @RequestParam("aid") String[] id,
            @RequestParam("profileimage") MultipartFile profilepic, Model model) {
        BasicConfigurator.configure();
        int usertypeforedit = 0;
        System.out.println("86th line");
        if (bindingResult.hasErrors()) {
            System.out.println("backend fail");

            List<FieldError> error = bindingResult.getFieldErrors();
            System.out.println(error);
            List<String> errorList = new ArrayList();
            for (FieldError err : error) {
                System.out.println("came if backend fail");
                errorList.add(err.getDefaultMessage());
                model.addAttribute("error", errorList);
                model.addAttribute("user", user);
            }
            return "register";
        } else {
            System.out.println("came in else");
        }

        session.setAttribute("user", user);
        String profile = null;
        try {
            profile = Base64.getEncoder().encodeToString(profilepic.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (profile != null) {
            user.setProfilepic(profile);
        }

        if (user.getId() != 0) {

            usertypeforedit = (int) session.getAttribute("role");
            System.out.println("user: " + usertypeforedit);
            List<AddressBean> addresses = user.getAddresses();
            for (int i = 0; i < id.length; i++) {
                log.info(id[i]);
                if (!id[i].isEmpty()) {
                    int Addressid = Integer.parseInt(id[i]);
                    addresses.get(i).setAid(Addressid);

                }
            }

            System.err.println("Update User");  
            for (AddressBean address : user.getAddresses()) {
                address.setUserBean(user);
            }
            for (UserRoles roles : user.getRoles()) {
                roles.setUser(user);
            }
            signupServiceImpl.updateUser(user);

        } else {

            System.err.println("New User");
            signupServiceImpl.addNewUser(user);
        }
        if (usertypeforedit == 0) {
            session.setAttribute("role", 0);
            System.out.println("user Logged in");
            return "redirect:userHome";
        } else if (usertypeforedit == 2) {
            System.out.println("Admin Logged in");
            log.info("Admin Logged in");
            return "redirect:adminHome";

        }
        return null;
    }

    @RequestMapping(path = "/Login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String pass, HttpSession session, Model model) {
        int usertype = signupServiceImpl.checkUser(email, pass);
        String noUser = "noUser";
        session.setAttribute("email", email);
        if (usertype == 1) {
            session.setAttribute("role", 0);
            return "redirect:userHome";

        } else if (usertype == 2) {
            session.setAttribute("role", 2);
            return "redirect:adminHome";

        } else {
            model.addAttribute(noUser, "User Doesn't Exist");
            return "index";
        }
    }

    @RequestMapping(path = "/UsersDetails", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String getUserDetails() {

        ArrayList<UserBean> userDetails = signupServiceImpl.getUserDetails();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        String JSONObject = gson.toJson(userDetails);
        return JSONObject;
    }

    @RequestMapping(path = "/DeleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestParam int userId) {
        log.info("Id:   " + userId);
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
            System.out.println("email got" + session.getAttribute("email"));
            String email = (String) session.getAttribute("email");
            UserBean userDetails = signupServiceImpl.getLoggedinUserDetails(email);
            session.setAttribute("user", userDetails);
        }

        return "redirect:register";
    }
    
    @RequestMapping(path = "/ForgotPass", method = RequestMethod.POST)
    public String forgotPass(@RequestParam String dob, @RequestParam String securityAns, HttpSession session,
            Model model) {
        boolean isValid = signupServiceImpl.checkForgotpassDetails(dob, securityAns);
        String didntmatch = "didntmatch";
        session.setAttribute("dob", dob);
        session.setAttribute("securityAns", securityAns);
        if (isValid == true) {

            return "changePass";
        } else {
            model.addAttribute(didntmatch, "User Doesn't Exist");
            return "forgotpass";
        }
    }

    @RequestMapping(path = "/ChangePass", method = RequestMethod.POST)
    public String changePass(@RequestParam String newPass, HttpSession session) {

        ForgotPassBean forgotPass = new ForgotPassBean();
        String dob = (String) session.getAttribute("dob");
        String securityAns = (String) session.getAttribute("securityAns");
        forgotPass.setDob(dob);
        forgotPass.setNewPass(newPass);
        forgotPass.setSecurityAns(securityAns);
        signupServiceImpl.changePass(forgotPass);

        return "index";

    }

    @RequestMapping("/Logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:index";
    }

    @RequestMapping("/emailFromUser")
    public ModelAndView emailFromUser(@RequestParam("email") String userEmail, HttpSession session) {
        session.setAttribute("email", userEmail);
        System.out.println("usersideemailser" + userEmail);
        return new ModelAndView("redirect:/EditDetails");
    }

    @RequestMapping(path = "/torecentlyregistered")
    public String torecentlyregistered() {
        return "recentlyRegisteredUsers";
    }

    @RequestMapping(path = "/getRecentUsers", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String getRecentUsers() {
        ArrayList<UserBean> recentUsers = signupServiceImpl.getRecentUsersList();

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        String JSONObject = gson.toJson(recentUsers);
        return JSONObject;

    }

    @RequestMapping("/CheckEmailAvailability")
    @ResponseBody
    public String checkEmailAvailability(@RequestParam("email") String email, HttpSession session) {
        boolean emailExists = signupServiceImpl.checkEmail(email);
        if (emailExists && session.getAttribute("email") == null) {
            return "false";
        } else {
            return "true";
        }
    }
}
