package team.vicilization.gameitem;

import team.vicilization.util.Position;

public class Explorer extends Folk{

    public Explorer() {
        super();
        setSubType(UnitSubType.EXPLORER);
    }

    public Explorer(Position position) {
        super(position);
        setSubType(UnitSubType.EXPLORER);
    }
}
