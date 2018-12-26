package team.vicilization.mechanics;

public enum GiantName {
    ALBERT_EINSTEIN,
    ERWIN_SCHRODINGER,
    ALAN_TURING,
    ADAM_SMITH,
    DAVID_RICARDO,
    JOHN_KEYNES,
    LEONARDO_DA_VINCI,
    GUSTAVE_EIFFEL,
    NICOLA_TESLA,
    JAMES_WATT;

    @Override
    public String toString() {
        String name = super.toString().toLowerCase();
        name = name.replace('_', ' ');
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        for (int i = 1; i < name.length(); i++) {
            if (cs[i - 1] == ' ') {
                cs[i] -= 32;
            }
        }
        return String.valueOf(cs);
    }
}
