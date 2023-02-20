package utils;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

public class RandomUtil {

    private static String password;
    private static String email;

    public static String generatePassayPassword() {

        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };

        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        CharacterData cyrillicChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "АБВГДЕЖꙂꙀИІКЛМНОПРСТОѴФХѠЦЧШЩЪЪІЬѢꙖѤЮѪѬѦѨѮѰѲѴҀ";
            }
        };

        CharacterRule cyrilicCharRule = new CharacterRule(cyrillicChars);
        cyrilicCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule, cyrilicCharRule);
        return password;
    }

    public static String getPassword() {
        password = generatePassayPassword();
        return password;
    }

    public static String getEmail() {
        email = generatePassayPassword();
        return email;
    }

    public static String getDomain() {
        return generatePassayPassword();
    }

}
