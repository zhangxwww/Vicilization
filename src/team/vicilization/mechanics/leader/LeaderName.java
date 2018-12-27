package team.vicilization.mechanics.leader;

public enum LeaderName {
    GANDHI,
    ROOSEVELT;

    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }
}
