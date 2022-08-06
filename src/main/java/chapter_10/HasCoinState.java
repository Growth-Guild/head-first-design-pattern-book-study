package chapter_10;

public class HasCoinState implements MachineState {
    private final TicketMachine ticketMachine;

    public HasCoinState(TicketMachine ticketMachine) {
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("이미 동전이 들어있습니다. 티켓을 출력해주세요.");
    }

    @Override
    public void printTicket() {
        ticketMachine.printTicket();
        ticketMachine.setMachineState(ticketMachine.getHasNoCoinState());
    }
}
