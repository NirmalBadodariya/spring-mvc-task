package springusermanagement.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String dob;
    private String pass;
    private String securityAns;

    public String getSecurityAns() {
        return securityAns;
    }

    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }
    
    // private String security_ans;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressBean> addresses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "uid")
    private List<UserRoles> roles;

    public List<UserRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoles> roles) {
        this.roles = roles;
    }

    public List<AddressBean> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressBean> addresses) {
        this.addresses = addresses;
    }

    // private InputStream image;
    // private String base64Image;

    // public String getBase64Image() {
    // return base64Image;
    // }

    // public void setBase64Image(String base64Image) {
    // this.base64Image = base64Image;
    // }
    // public InputStream getImage() {
    // return image;
    // }

    // public void setImage(InputStream image) {
    // this.image = image;
    // }

    @Override
    public String toString() {
        return "UserBean [SecurityAns=" + securityAns + ", addresses=" + addresses + ", dob=" + dob + ", email=" + email
                + ", firstName=" + firstName + ", gender=" + gender + ", id=" + id + ", lastName=" + lastName
                + ", pass=" + pass + ", phone=" + phone + ", roles=" + roles + "]";
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    // private String user_profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    // public String getUser_profile() {
    // return user_profile;
    // }

    // public void setUser_profile(String user_profile) {
    // this.user_profile = user_profile;
    // }

}
