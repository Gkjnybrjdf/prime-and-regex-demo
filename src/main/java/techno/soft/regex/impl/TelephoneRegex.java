package techno.soft.regex.impl;

import techno.soft.regex.ITelephoneRegex;

import java.util.regex.Pattern;

public class TelephoneRegex implements ITelephoneRegex {
    // Начинается на +7 или 8
    // Затем (111) или 111
    // Затем "111 11 11" или "111 1111" или "1111111"
    private final Pattern regex = Pattern.compile("^(\\+7|8)\s?((\\(?\\d{3}\\)?)|\\d{3})\s?\\d{3}\s?\\d{2}\s?\\d{2}$");

    @Override
    public boolean isValid(String phone) {
        return regex.matcher(phone).matches();
    }
}
