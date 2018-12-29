package team.vicilization.mechanics.leader;

public enum LeaderName {
    // 伟人名称枚举类
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
