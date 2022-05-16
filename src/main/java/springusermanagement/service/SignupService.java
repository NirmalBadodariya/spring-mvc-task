package springusermanagement.service;

import java.util.ArrayList;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.stereotype.Service;

import springusermanagement.model.ForgotPassBean;
import springusermanagement.model.UserBean;

public interface SignupService {
    public int addNewUser(UserBean user);
    
    public int checkUser(String email, String pass);
    public ArrayList getUserDetails();
    public void deleteUser(int userId) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException;
    public void updateUser(UserBean user);
    public boolean checkForgotpassDetails(String dob, String securityAns);
    public void changePass(ForgotPassBean forgotPass);
    public ArrayList getRecentUsersList();
    public boolean checkEmail(String email);

    public UserBean getLoggedinUserDetails(String email);
}
