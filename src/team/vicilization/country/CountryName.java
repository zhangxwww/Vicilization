package team.vicilization.country;

import org.jetbrains.annotations.NotNull;

public enum CountryName {
    INDIA,
    MACEDONIA;

    @NotNull
    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
