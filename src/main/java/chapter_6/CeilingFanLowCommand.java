package chapter_6;

public class CeilingFanLowCommand implements Command {
    private CeilingFan ceilingFan;
    private Command prevSpeedCommand;

    public CeilingFanLowCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        CeilingFan.Speed prevSpeed = ceilingFan.getSpeed();
        prevSpeedCommand = prevSpeed.createCommand(ceilingFan);
        ceilingFan.low();
    }

    @Override
    public void undo() {
        prevSpeedCommand.execute();
    }
}
