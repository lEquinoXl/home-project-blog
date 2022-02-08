package com.softserve.model;


import javax.validation.constraints.Pattern;

public class ChangePassword {

    private String oldPassword;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")
    private String newPassword;
}
