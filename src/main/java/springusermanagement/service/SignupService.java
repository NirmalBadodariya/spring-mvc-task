package springusermanagement.service;

import org.springframework.stereotype.Service;

import springusermanagement.model.UserBean;

public interface SignupService {
    public int addNewUser(UserBean user);
}
