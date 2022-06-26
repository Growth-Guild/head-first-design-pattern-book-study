package chapter_6;

public class CeilingFanMediumCommand implements Command {
    private CeilingFan ceilingFan;
    private Command prevSpeedCommand;

    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        CeilingFan.Speed prevSpeed = ceilingFan.getSpeed();
        prevSpeedCommand = prevSpeed.createCommand(ceilingFan);
        ceilingFan.medium();
    }

    @Override
    public void undo() {
        prevSpeedCommand.execute();
    }
}
