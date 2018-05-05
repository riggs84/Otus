package Pages.Utils;

import com.github.javafaker.Faker;

public class DataGenerator {
    public static String generateFirstName(){
        return new Faker().name().firstName();
    }

    public static String generateLastName(){
        return new Faker().name().lastName();
    }

    public static String generateAddress(){
        return new Faker().address().streetAddress();
    }

    public static String generateCity(){
        return new Faker().address().city();
    }

    public static String generateState(){
        return new Faker().address().state();
    }

    public static String generateZipCode(){
        return new Faker().address().zipCode();
    }

    public static String generateCreditCardNumber(){
       return new Faker().business().creditCardNumber();
    }

    public static String generateMonth(){
        return String.valueOf(new Faker().number().numberBetween(1, 12));
    }

    public static String generateYear(){
        return String.valueOf(new Faker().number().numberBetween(1970, 2070));
    }
}
