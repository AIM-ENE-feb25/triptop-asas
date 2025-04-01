package com.example;


public interface LoginPort {
    AuthToken authenticateExternal(Reiziger reiziger) throws AuthenticationException;
}
