package team.vicilization.gameitem;


import team.vicilization.util.Position;

public abstract class Folk extends Unit {

    public Folk() {
        super();
        setType(UnitType.FOLK);
    }

    public Folk(Position position) {
        super(position);
        setType(UnitType.FOLK);
    }
}
