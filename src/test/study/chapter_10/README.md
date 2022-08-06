# Chapter 10. 상태 패턴 (State Pattern)

![state_pattern](state_pattern.png)
* 객체의 내부 상태가 바뀜에 따라서 객체의 행동을 바꿀 수 있는 패턴이다.
* 상태를 별도의 클래스로 캡슐화하고 현재 상태를 나타내는 컨텍스트 객체가 행동을 위임하기 때문에 새로운 상태를 유연하게 확장할 수 있다.
* 전략 패턴과 같은 구조를 가지고 있다.
  * 하지만 상태 패턴과 전략 패턴은 용도가 다르다.
  * 상태 패턴은 상태 객체에 일련의 행동이 캡슐화된다.
  * 상태 패턴은 상황에 따라 Context 객체에서 여러 상태 객체 중 한 객체에게 모든 행동을 위임하고, Context의 상태가 바뀜에 따라 행동도 바뀌게 된다.
  * 전략 패턴은 일반적으로 클라이언트가 Context 객체에게 어떤 전략 객체를 사용할지 지정해 준다.
  * 전략 패턴은 실행 시에 전략 객체를 변경할 수 있는 유연성을 제공하는 용도로 쓰인다.
* 상태 패턴으로의 리팩토링하는 주된 목적은 상태 전이를 위한 조건 로직이 지나치게 복잡한 경우에 이를 해소하기 위함이다.
* 하나의 상태를 클래스 하나로 명확히 표현하는 것과, 상태 변수의 값으로 표현하는 방법 사이에서 트레이드 오프를 고민해보는 것이 좋다.
  * 클래스로 명확하게 표현하는 것이 더 낫다면 상태 패턴을 고려해보면 좋다.
  * 상태 패턴으로 리팩토링하여 결과물이 더 복잡해졌다면 굳이 도입하지 않아도 된다.
* 상태가 늘어남에 따라 클래스의 수가 늘어난다는 점을 주의해야 한다.
* 각 상태가 다음 상태를 알아야 하므로 각 상태가 다음 상태롤 모르는 것이 바람직한 것은 아닌지 고려해봐야 한다.
* 각 상태를 싱글톤으로 고려해볼 수도 있다.

### 간단한 티켓 자판기 예시
* 자판기는 두 개의 상태를 갖는다.
* 동전이 없는 상태
  * 동전이 투입되기를 기다리고 있는 상태
  * 티켓을 출력하려고 해도 출력되지 않는다.
  * 동전이 투입되면 동전이 투입된 상태가 된다.
* 동전이 투입된 상태
  * 티켓을 출력할 수 있다.
  * 동전을 더 넣어도 상태가 바뀌지는 않는다.
  * 티켓을 출력하면 동전이 없는 상태가 된다.

```java
public interface MachineState {
    void insertCoin();
    void printTicket();
}
```
```java
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
```
```java
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
```

### 상태 패턴의 예?
* TCP connection
