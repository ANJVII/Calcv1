import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }
    public static String calc (String input){
        ArrayList<String> result = new ArrayList<>();
        String[] array = input.split(",");
        for(String str: array){
            result.add(checkSign(str.toUpperCase()));
        }
        return join(result, ",");
    }

    private static String checkSign (String str){
        try {
            if (str.contains("+"))
                return addition(str);
            else if (str.contains("-"))
                return subtraction(str);
            else if (str.contains("*"))
                return multiplication(str);
            else if (str.contains("/"))
                return division(str);
            else
                throw new RuntimeException("Ошибка. Пожалуйста используйте *, /, -, +. Спасибо!");
        } catch (ArrayIndexOutOfBoundsException e){
            throw new RuntimeException("Результат работы с римскими числами меньше или равен нулю");
        }
    }

    private static String addition(String str){
        String[] arr = str.split("\\+");
        String regex = "(.*)[*|+|\\-|/](.*)";
        if(arr[0].matches(regex) || arr[1].matches(regex) || arr.length>2) {
            throw new RuntimeException("Калькулятор проводит арифметические действия только с двумя положительными числами.");
        }
        if(isRoman(str)) {
            RomanNum num0 = RomanNum.valueOf(arr[0].trim());
            RomanNum num1 = RomanNum.valueOf(arr[1].trim());
            if(num0.getValue()>10 || num1.getValue()>10){
                throw new RuntimeException("Пожалуйста используйте римские числа от I до X включительно. Спасибо!");
            }
            int result = num0.getValue() + num1.getValue();
            RomanNum num = RomanNum.values()[result - 1];
            return num.toString();
        } else if(Integer.parseInt(arr[0].trim())>10 || Integer.parseInt(arr[1].trim())> 10) {
            throw new RuntimeException("Пожалуйста используйте числа от 1 до 10 включительно. Спасибо!");
        } else {
            int result = Integer.parseInt(arr[0].trim()) + Integer.parseInt(arr[1].trim());
            return Integer.toString(result);
        }
    }

    private static String subtraction(String str){
        String[] arr = str.split("-");
        String regex = "(.*)[*|+|\\-|/](.*)";
        if(arr[0].matches(regex) || arr[1].matches(regex) || arr.length>2) {
            throw new RuntimeException("Калькулятор проводит арифметические действия только с двумя положительными числами.");
        }
        if(isRoman(str)) {
            RomanNum num0 = RomanNum.valueOf(arr[0].trim());
            RomanNum num1 = RomanNum.valueOf(arr[1].trim());
            if(num0.getValue()>10 || num1.getValue()>10){
                throw new RuntimeException("Пожалуйста используйте римские числа от I до X включительно. Спасибо!");
            }
            int result = num0.getValue() - num1.getValue();
            RomanNum num = RomanNum.values()[result - 1];
            return num.toString();
        }else if(Integer.parseInt(arr[0].trim())>10 || Integer.parseInt(arr[1].trim())> 10) {
            throw new RuntimeException("Пожалуйста используйте числа от 1 до 10 включительно. Спасибо!");
        } else {
            int result = Integer.parseInt(arr[0].trim()) - Integer.parseInt(arr[1].trim());
            return Integer.toString(result);
        }
    }

    private static String multiplication(String str){
        String[] arr = str.split("\\*");
        String regex = "(.*)[*|+|\\-|/](.*)";
        if(arr[0].matches(regex) || arr[1].matches(regex) || arr.length>2){
            throw new RuntimeException("Калькулятор проводит арифметические действия только с двумя положительными числами.");
        }
        if(isRoman(str)) {
            RomanNum num0 = RomanNum.valueOf(arr[0].trim());
            RomanNum num1 = RomanNum.valueOf(arr[1].trim());
            if(num0.getValue()>10 || num1.getValue()>10){
                throw new RuntimeException("Пожалуйста используйте римские числа от I до X включительно. Спасибо!");
            }
            int result = num0.getValue() * num1.getValue();
            RomanNum num = RomanNum.values()[result - 1];
            return num.toString();
        }else if(Integer.parseInt(arr[0].trim())>10 || Integer.parseInt(arr[1].trim())> 10) {
            throw new RuntimeException("Пожалуйста используйте числа от 1 до 10 включительно. Спасибо!");
        } else {
            int result = Integer.parseInt(arr[0].trim()) * Integer.parseInt(arr[1].trim());
            return Integer.toString(result);
        }
    }

    private static String division(String str){
        String[] arr = str.split("/");
        String regex = "(.*)[*|+|\\-|/](.*)";
        if(arr[0].matches(regex) || arr[1].matches(regex) || arr.length > 2){
            throw new RuntimeException("Калькулятор проводит арифметические действия только с двумя положительными числами.");
        }
        if(isRoman(str)) {
            RomanNum num0 = RomanNum.valueOf(arr[0].trim());
            RomanNum num1 = RomanNum.valueOf(arr[1].trim());
            if(num0.getValue()>10 || num1.getValue()>10){
                throw new RuntimeException("Пожалуйста используйте римские числа от I до X включительно. Спасибо!");
            }
            int result = num0.getValue() / num1.getValue();
            RomanNum num = RomanNum.values()[result - 1];
            return num.toString();
        }else if(Integer.parseInt(arr[0].trim())>10 || Integer.parseInt(arr[1].trim())> 10) {
            throw new RuntimeException("Пожалуйста используйте числа от 1 до 10 включительно. Спасибо!");
        } else {
            int result = Integer.parseInt(arr[0].trim()) / Integer.parseInt(arr[1].trim());
            return Integer.toString(result);
        }
    }

    private static boolean isRoman(String str){
        boolean hasDigits = false;
        for(int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                hasDigits = true;
            }
        }
        if(hasDigits) {
            String regex = "(.*)[V|I|X](.*)";
            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(str).matches()) {
                throw new RuntimeException("Пожалуйста не составляйте выражение из римских и арабских цифр одновременно. Спасибо!");
            } else
                return false;
        }
        return true;
    }
    public static String join(Collection s, String delimiter) { //повзаимствовано с интернета (http://codehelper.ru/questions/120/new/%D0%BC%D0%B5%D1%82%D0%BE%D0%B4-join-%D0%B4%D0%BB%D1%8F-%D1%81%D1%82%D1%80%D0%BE%D0%BA-%D0%B2-java)
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

}

//