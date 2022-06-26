package chapter_6;

public class CeilingFan {
    private String location;
    private Speed speed;

    public CeilingFan(String location) {
        this.location = location;
        this.speed = Speed.OFF;
    }

    public void high() {
        speed = Speed.HIGH;
    }

    public void medium() {
        speed = Speed.MEDIUM;
    }

    public void low() {
        speed = Speed.LOW;
    }

    public void off() {
        speed = Speed.OFF;
    }

    public Speed getSpeed() {
        return speed;
    }

    public enum Speed {
        HIGH(3) {
            @Override
            Command createCommand(CeilingFan ceilingFan) {
                return new CeilingFanHighCommand(ceilingFan);
            }
        },
        MEDIUM(2) {
            @Override
            Command createCommand(CeilingFan ceilingFan) {
                return new CeilingFanMediumCommand(ceilingFan);
            }
        },
        LOW(1) {
            @Override
            Command createCommand(CeilingFan ceilingFan) {
                return new CeilingFanLowCommand(ceilingFan);
            }
        },
        OFF(0) {
            @Override
            Command createCommand(CeilingFan ceilingFan) {
                return new CeilingFanOffCommand(ceilingFan);
            }
        };

        private int val;

        Speed(int val) {
            this.val = val;
        }

        abstract Command createCommand(CeilingFan ceilingFan);
    }
}
