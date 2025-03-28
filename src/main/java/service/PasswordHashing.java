package service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {

    // ✅ Method to hash the password
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }

    // ✅ Method to verify the password
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
