package com.sweetitech.ird.common.Security;

import com.sweetitech.ird.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Avijit Barua
 * @created_on 12/24/18 at 12:11 PM
 * @project InternalResourcesDivision
 */
public class CustomEncoder implements PasswordEncoder {

    private static final String BCRYPT_TYPE = "$";
    private static final PasswordEncoder BCRYPT = new BCryptPasswordEncoder();
    private static final SymfonySha512PasswordEncoder SHA512 = new SymfonySha512PasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return BCRYPT.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (!encodedPassword.startsWith(BCRYPT_TYPE)) {
            System.out.println("================================");
            System.out.println(rawPassword + "\n" + encodedPassword);
            System.out.println("================================");
            // old SHA512
            String[] saltHash = encodedPassword.split(User.SPLIT_CHAR);
            String salt = saltHash[0];
            String shaEncodedPassword = saltHash[1];
            try {
                return SHA512.matches(rawPassword.toString(), shaEncodedPassword, salt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return BCRYPT.matches(rawPassword, encodedPassword);
    }
}