package com.shashi.example.passwordhash.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shashi.example.passwordhash.domain.Password;
import com.shashi.example.passwordhash.domain.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String firstName;
    private String userid;
    private String lastName;
    private String email;
    private PasswordDTO passwordDetails;

    public UserDTO(User user) {
        this.id = user.getId();
        this.userid = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.passwordDetails = setValue(user.getPassword());
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private PasswordDTO setValue(Password userProfile) {
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setSalt(userProfile.getSalt());
        passwordDTO.setPassword(userProfile.getPassword());
        return passwordDTO;
    }

    public PasswordDTO getPasswordDetails() {
        return passwordDetails;
    }

    public void setPasswordDetails(PasswordDTO passwordDTO) {
        this.passwordDetails = passwordDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
