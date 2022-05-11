package springusermanagement.service;

import java.util.ArrayList;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springusermanagement.dao.UserDaoImpl;
import springusermanagement.model.AddressBean;
import springusermanagement.model.UserBean;
import springusermanagement.model.UserRoles;

@Service
public class SignupServiceImpl {

    @Autowired
    private UserDaoImpl userDaoimpl;

    public int addNewUser(UserBean user) {

        for (AddressBean address : user.getAddresses()) {
            address.setUserBean(user);
        }
        // for (UserRoles role : user.getRoles()) {
        // role.setUser(user);
        // }

        return (int) userDaoimpl.addNewUser(user);
    }

    public boolean checkUser(String email, String pass) {
        return userDaoimpl.checkUser(email, pass);
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

}
