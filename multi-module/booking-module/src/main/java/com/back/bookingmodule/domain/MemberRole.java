package com.back.bookingmodule.domain;

public enum MemberRole {

    ROLE_USER("USER"),ROLE_ADMIN("ADMIN"),ROLE_OWNER("OWNER");


    private final String role;

    MemberRole(String role){
        this.role =role;
    }

    public String getRole(){
        return this.role;
    }

}
