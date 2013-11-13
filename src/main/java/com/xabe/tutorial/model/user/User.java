package com.xabe.tutorial.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xabe.tutorial.model.Model;

@Entity
@Table(name="t_user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT m FROM User m"),
    @NamedQuery(name = "User.findById", query = "SELECT m FROM User m WHERE m.id = :id"),
    @NamedQuery(name = "User.findByName", query = "SELECT m FROM User m WHERE m.name = :name")})
//@EntityListeners(UserListener.class)
public class User extends Model {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String surname1;

    private String email;

    private Integer telephone;

    private Integer enable;

    private Integer blocked;

    private Integer attemptsLogin;

    private String surname2;

    @Column(unique=true, nullable=false) 
    private String username;

    private String password;

    private String picture;

    @Temporal(TemporalType.TIMESTAMP)
//    @Column( 
//    updatable = false,
//    columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date dateLastPassword;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1 == null ? null : surname1.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    public Integer getAttemptsLogin() {
        return attemptsLogin;
    }

    public void setAttemptsLogin(Integer attemptsLogin) {
        this.attemptsLogin = attemptsLogin;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2 == null ? null : surname2.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Date getDateLastPassword() {
        return dateLastPassword;
    }

    public void setDateLastPassword(Date dateLastPassword) {
        this.dateLastPassword = dateLastPassword;
    }

    public Date getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(Date dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSurname1() == null ? other.getSurname1() == null : this.getSurname1().equals(other.getSurname1()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getBlocked() == null ? other.getBlocked() == null : this.getBlocked().equals(other.getBlocked()))
            && (this.getAttemptsLogin() == null ? other.getAttemptsLogin() == null : this.getAttemptsLogin().equals(other.getAttemptsLogin()))
            && (this.getSurname2() == null ? other.getSurname2() == null : this.getSurname2().equals(other.getSurname2()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture()))
            && (this.getDateLastPassword() == null ? other.getDateLastPassword() == null : this.getDateLastPassword().equals(other.getDateLastPassword()))
            && (this.getDateLastLogin() == null ? other.getDateLastLogin() == null : this.getDateLastLogin().equals(other.getDateLastLogin()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSurname1() == null) ? 0 : getSurname1().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getBlocked() == null) ? 0 : getBlocked().hashCode());
        result = prime * result + ((getAttemptsLogin() == null) ? 0 : getAttemptsLogin().hashCode());
        result = prime * result + ((getSurname2() == null) ? 0 : getSurname2().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getDateLastPassword() == null) ? 0 : getDateLastPassword().hashCode());
        result = prime * result + ((getDateLastLogin() == null) ? 0 : getDateLastLogin().hashCode());
        return result;
    }
}
