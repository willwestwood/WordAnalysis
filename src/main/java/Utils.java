public class Utils {
    public static String RemoveNonAlphanumericChars(String input)
    {
        // replace the given string with empty string, except the pattern "[^a-zA-Z0-9]"
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static char[] UniqueCharacters(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (result.toString().indexOf(current) < 0)
                result.append(current);
        }
        return result.toString().toCharArray();
    }
}
