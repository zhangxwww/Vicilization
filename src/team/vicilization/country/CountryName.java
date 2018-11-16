package team.vicilization.country;

public enum CountryName {
    INDIA,
    MACEDONIA;

    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
