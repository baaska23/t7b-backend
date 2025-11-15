package org.t7b.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    public void logout(String token) {
        if (token == null) {
            return;
        }
        revokeToken(token);
    }
    
    private void revokeToken(String token) {
    
    }
}
