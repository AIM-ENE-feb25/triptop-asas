package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginPort loginPortV1;
    private final LoginPort loginPortV2;
    private final LoginRepo loginRepo;

    @Autowired
    public LoginService(@Qualifier("wireMockAdapterV1") LoginPort loginPortV1,
                        @Qualifier("wireMockAdapterV2") LoginPort loginPortV2,
                        LoginRepo loginRepo) {
        this.loginPortV1 = loginPortV1;
        this.loginPortV2 = loginPortV2;
        this.loginRepo = loginRepo;
    }

    public AuthToken authenticateV1(Reiziger reiziger) throws AuthenticationException {
        // Retrieve token via v1 adapter
        AuthToken token = loginPortV1.authenticateExternal(reiziger);
        // If token contains extra "haar", convert it by removing "haar"
        if (token.getToken().endsWith("haar")) {
            token = convertToNewFormat(token);
        }
        // Optionally, save non-sensitive user data (password is not stored)
        if (loginRepo.findByUsername(reiziger.getUsername()) == null) {
            loginRepo.save(reiziger);
        }
        return token;
    }

    public AuthToken authenticateV2(Reiziger reiziger) throws AuthenticationException {
        // Retrieve token via v2 adapter (returns token as provided)
        AuthToken token = loginPortV2.authenticateExternal(reiziger);
        if (loginRepo.findByUsername(reiziger.getUsername()) == null) {
            loginRepo.save(reiziger);
        }
        return token;
    }

    public AuthToken convertToNewFormat(AuthToken token) {
        // Remove the extra string "haar" to convert to new format
        String converted = token.getToken().replace("haar", "");
        return new AuthToken(converted);
    }
}
