package team.vicilization.country;

import team.vicilization.gamemap.LandSquare;

import java.util.Vector;

public class TestCountry {
    public static void main(String[] args) {
        //Country testCountry = new Country(CountryName.AMERICA);
        Vector<LandSquare> test = new Vector<>();
        System.out.println(test.size());

        test.add(null);
        System.out.println(test.size());
    }
}
