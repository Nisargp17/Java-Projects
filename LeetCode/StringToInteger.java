package Projects.LeetCode;

public class StringToInteger {
    public static void main(String[] args) {
        String str = "-123";
        int integer = InnerStringToInteger.stringToInt(str);
        System.out.println(integer);
    }
}

class InnerStringToInteger {
    static int stringToInt(String s) {
        int result = 0;
        s.replaceAll(" ", "");
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                break;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                result = s.charAt(i);
            } else {
                result = Integer.parseInt(s);
            }
        }
        return result;
    }
}
