package team.vicilization.mechanics;

import org.jetbrains.annotations.NotNull;

public enum LeaderName {
    GANDHI,
    ALEXANDER;

    @NotNull
    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
