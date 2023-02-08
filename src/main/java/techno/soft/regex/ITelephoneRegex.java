package techno.soft.regex;

public interface ITelephoneRegex {
    /**
     * Проверка валидности номера телефона
     *
     * @param phone номер телефона
     * @return {@code true} в случае если телефон введен верно, {@code false} - иначе
     */
    boolean isValid(String phone);
}
