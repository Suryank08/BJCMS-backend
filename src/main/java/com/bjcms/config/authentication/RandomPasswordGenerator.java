package com.bjcms.config.authentication;

import java.security.SecureRandom;

public class RandomPasswordGenerator {

    // Define character sets for the password
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "@#$%^&+=!";

    // Combine all character sets
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
    private static final Integer length=12;
    // SecureRandom for cryptographically secure random values
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword( String fName, String mobileNum, String email) {
        StringBuilder password = new StringBuilder(length);

        // Use part of first name or last name
        if (fName.length() > 2) {
            password.append(fName.substring(0, 2).toUpperCase()); // Take first 2 letters of the first name
        }
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        // Add some digits from the mobile number
        if (mobileNum.length() >= 2) {
            password.append(mobileNum.substring(3, 5)); // Take first 2 digits from mobile number
        }
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
        // Add part of the email before '@'
        if (email.contains("@")) {
            String emailPrefix = email.split("@")[0]; // Get the part before '@'
            if (emailPrefix.length() >= 2) {
                password.append(emailPrefix.substring(0, 2)); // Take first 2 characters
            }
        }

        // Fill the remaining length with random characters from all character sets
        while (password.length() < length) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        // Return the generated password as a string
        return new String(password);
    }
}