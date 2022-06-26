package chapter_6;

public class CeilingFanHighCommand implements Command {
    private CeilingFan ceilingFan;
    private Command prevSpeedCommand;

    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        CeilingFan.Speed prevSpeed = ceilingFan.getSpeed();
        prevSpeedCommand = prevSpeed.createCommand(ceilingFan);
        ceilingFan.high();
    }

    @Override
    public void undo() {
        prevSpeedCommand.execute();
    }
}
