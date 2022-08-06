package chapter_10;

public class HasNoCoinState implements MachineState {
    private final TicketMachine ticketMachine;

    public HasNoCoinState(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void insertCoin() {
        ticketMachine.setMachineState(ticketMachine.getHasCoinState());
    }

    @Override
    public void printTicket() {
        System.out.println("동전이 없습니다. 동전을 투입해주세요.");
    }
}
