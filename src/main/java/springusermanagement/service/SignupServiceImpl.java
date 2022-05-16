package springusermanagement.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springusermanagement.dao.UserDao;
import springusermanagement.dao.UserDaoImpl;
import springusermanagement.model.AddressBean;
import springusermanagement.model.ForgotPassBean;
import springusermanagement.model.UserBean;
import springusermanagement.model.UserRoles;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private UserDao userDaoimpl;

    @Override
    public int addNewUser(UserBean user) {

        List<UserRoles> roles = user.getRoles();
        for (AddressBean address : user.getAddresses()) {
            address.setUserBean(user);
        }
        for (UserRoles role : roles) {
            role.setUser(user);
        }
        user.setRoles(roles);
        return (int) userDaoimpl.addNewUser(user);
    }

    public int checkUser(String email, String pass) {
        UserBean user = userDaoimpl.getLoggedinUserDetails(email);
        user.setPass(pass);
        System.out.println("id:  " + user.getId());
        return userDaoimpl.checkUser(user);
    }

    public ArrayList getUserDetails() {
        return userDaoimpl.getUserDetails();
    }

    public void deleteUser(int userId) throws SecurityException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException, SystemException {
        UserBean user = new UserBean();
        user.setId(userId);
        userDaoimpl.deleteUser(user);
    }

    public UserBean getLoggedinUserDetails(String email) {
        return userDaoimpl.getLoggedinUserDetails(email);
    }

    public void updateUser(UserBean user) {

        userDaoimpl.updateuser(user);
    }

    public boolean checkForgotpassDetails(String dob, String securityAns) {
        return userDaoimpl.checkForgotpassDetails(dob, securityAns);
    }

    public void changePass(ForgotPassBean forgotPass) {
        userDaoimpl.changePass(forgotPass);
    }

    public ArrayList getRecentUsersList() {
        return userDaoimpl.getRecentUsersList();
    }

    public boolean checkEmail(String email) {
        return userDaoimpl.checkEmail(email);
    }

}
