package chapter_10;

public class TicketMachine {
    private final MachineState hasNoCoinState;
    private final MachineState hasCoinState;
    private MachineState currentState;

    public TicketMachine(MachineState currentState) {
        this.hasNoCoinState = new HasNoCoinState(this);
        this.hasCoinState = new HasCoinState(this);
        this.currentState = currentState;
    }

    public void insertCoin() {
        currentState.insertCoin();
    }

    public void printTicket() {
        currentState.printTicket();
    }

    public void setMachineState(MachineState machineState) {
        this.currentState = machineState;
    }

    public MachineState getHasNoCoinState() {
        return hasNoCoinState;
    }

    public MachineState getHasCoinState() {
        return hasCoinState;
    }
}
