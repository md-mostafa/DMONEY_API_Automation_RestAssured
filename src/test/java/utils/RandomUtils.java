package utils;

import com.github.javafaker.Faker;

public class RandomUtils {
    private Faker faker = new Faker();

    public String getFullName(){
        return faker.name().fullName();
    }

    public String getEmail(){
        return faker.internet().emailAddress();
    }

    public String generatePhoneNumber(){
        String prefix ="01712";
        int min = 100000;
        int max = 999999;
        return prefix + (int) Math.round(Math.random()*(max-min)+min);

    }

}
