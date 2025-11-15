package org.t7b.services;

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
