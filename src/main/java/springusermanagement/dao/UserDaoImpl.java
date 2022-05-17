package springusermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springusermanagement.encryotion.AES;
import springusermanagement.model.ForgotPassBean;
import springusermanagement.model.UserBean;
import springusermanagement.model.UserRoles;

@Repository
@Transactional
public class UserDaoImpl<T> implements UserDao<T> {

    Logger log = Logger.getLogger(UserDaoImpl.class.getName());

    @Autowired
    protected SessionFactory factory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public T addNewUser(UserBean user) {

        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedString = AES.encrypt(user.getPass(), secretKey);
        user.setPass(encryptedString);

        return (T) hibernateTemplate.save(user);
    }

    public int checkUser(UserBean user) {
        BasicConfigurator.configure();

        log.info("uid: " + user.getId());
        String QUERY1 = " from UserRoles  where user_id=" + user.getId();
        log.info(QUERY1);
        List<UserRoles> role = (List<UserRoles>) hibernateTemplate.find(QUERY1);
        log.info("role: " + role.get(0).getRole());

        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedString = AES.encrypt(user.getPass(), secretKey);
        log.info(encryptedString);
        log.info(user.getEmail());

        int usertype = 0;
        String QUERY = " from UserBean as o where o.email=?0 and o.pass=?1";
        List list = new ArrayList<>();
        SessionFactory factory = hibernateTemplate.getSessionFactory();
        Session session;
        if (factory != null) {
            session = factory.openSession();
            if (session != null) {
                list = session.createQuery(QUERY)
                        .setParameter(0, user.getEmail())
                        .setParameter(1, encryptedString).list();
            }
        }

        if (list != null && list.size() > 0 && role.get(0).getRole() == 1) {
            usertype = 1;
        } else if (list != null && list.size() > 0 && role.get(0).getRole() == 2) {
            usertype = 2;

        }
        return usertype;
    }

    public ArrayList<UserBean> getUserDetails() {
        BasicConfigurator.configure();

        ArrayList<UserBean> userDetails = new ArrayList<>();
        SessionFactory factory = hibernateTemplate.getSessionFactory();
        Session session;
        if (factory != null) {
            session = factory.openSession();
            if (session != null) {
                Query query = session.createQuery("from UserBean");
                userDetails = (ArrayList<UserBean>) query.getResultList();
            }
        }
        return userDetails;
    }

    public void deleteUser(T user) throws SecurityException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException, SystemException {

        hibernateTemplate.delete(user);

    }

    public UserBean getLoggedinUserDetails(String email) {
        BasicConfigurator.configure();

        ArrayList<UserBean> userDetails = new ArrayList<>();
        SessionFactory factory = hibernateTemplate.getSessionFactory();
        Query query = null;
        Session session;
        if (factory != null) {
            session = factory.openSession();
            if (session != null) {
                query = session.createQuery("from UserBean where email=:email").setParameter("email", email);
                userDetails = (ArrayList<UserBean>) query.getResultList();
                System.out.println("pass: " + userDetails.get(0).getPass());
                final String secretKey = "ssshhhhhhhhhhh!!!!";
                String decryptedPass = AES.decrypt(userDetails.get(0).getPass(), secretKey);
                userDetails.get(0).setPass(decryptedPass);
                log.info("decc: " + userDetails.get(0).getPass());
            }
        }
        return userDetails.get(0);
    }

    public void updateuser(UserBean user) {
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedString = AES.encrypt(user.getPass(), secretKey);
        log.info(encryptedString);
        user.setPass(encryptedString);
        hibernateTemplate.merge(user);
    }
        
    public boolean checkForgotpassDetails(String dob, String securityAns) {
        boolean isValid = false;
        List list = new ArrayList<>();
        SessionFactory factory = hibernateTemplate.getSessionFactory();
        Session session;
        if (factory != null) {
            session = factory.openSession();
            if (session != null) {
                String QUERY = " from UserBean as o where o.dob=?0 and o.securityAns=?1";
                list = session.createQuery(QUERY).setParameter(0, dob)
                        .setParameter(1, securityAns).list();
            }
        }

        if (list != null && list.size() > 0) {
            isValid = true;
        }
        return isValid;
    }

    @Transactional
    public void changePass(ForgotPassBean forgotPass) {
        Session session;
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedString = AES.encrypt(forgotPass.getNewPass(), secretKey);
        Transaction tx = null;
        SessionFactory factory = hibernateTemplate.getSessionFactory();
        if (factory != null) {
            session = factory.openSession();
            if (session != null) {
                tx = session.beginTransaction();
                Query q = session
                        .createQuery("update UserBean set pass=:newPass where dob=:dob and securityAns=:securityAns")
                        .setParameter("newPass", encryptedString)
                        .setParameter("dob", forgotPass.getDob())
                        .setParameter("securityAns", forgotPass.getSecurityAns());

                q.executeUpdate();
                tx.commit();
            }
        }
    }

    public ArrayList<UserBean> getRecentUsersList() {

        ArrayList<UserBean> userDetails = new ArrayList<>();
        List<Object[]> rows = new ArrayList<>();
        Session session;
        Transaction tx = null;
        SessionFactory factory = hibernateTemplate.getSessionFactory();
        if (factory != null) {
            session = factory.openSession();
            if (session != null) {
                tx = session.beginTransaction();
                SQLQuery query = session
                        .createSQLQuery("select * from users where CreatedTime  >= NOW() - INTERVAL 1 DAY");
                rows = query.list();
                for (int i = 0; i < rows.size(); i++) {
                    UserBean user = new UserBean();
                    user.setId(Integer.parseInt(rows.get(i)[0].toString()));
                    user.setEmail(rows.get(i)[2].toString());
                    user.setFirstName(rows.get(i)[3].toString());
                    userDetails.add(user);
                }
                tx.commit();

            }
        }
        return userDetails;
    }

    public boolean checkEmail(String email) {
        boolean emailExists = false;
        SessionFactory factory = hibernateTemplate.getSessionFactory();
        Session session;
        List list = new ArrayList<>();
        if (factory != null) {
            session = factory.openSession();
            if (session != null) {

                String QUERY = " from UserBean as o where o.email=?0";
                list = session.createQuery(QUERY)
                        .setParameter(0, email).list();

            }
        }
        if (list != null && list.size() > 0) {
            emailExists = true;
        }
        return emailExists;
    }

}
