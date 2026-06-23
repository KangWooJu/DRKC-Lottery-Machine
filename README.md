# 🎁 Lottery Draw Program

간단한 Java 기반 경품 추첨 프로그램입니다.

참가자 이름과 추첨권 개수를 입력하면, 전체 추첨권 개수를 기준으로 가중치 랜덤 추첨을 수행합니다.

---

## 📌 주요 기능

- 참가자별 추첨권 개수 등록
- 전체 추첨권 개수 기준 랜덤 추첨
- 추첨권 개수가 많을수록 당첨 확률 증가
- 1등, 2등, 3등 연속 추첨
- 이미 당첨된 사람은 다음 추첨에서 제외
- Spring Framework 없이 순수 Java(POJO) 기반 구현

---

## 🛠 사용 기술

- Java 17+
- Record
- List
- Random
- POJO (Plain Old Java Object)

---

## 📂 프로젝트 구조

```text
src
├── Main.java
└── LotteryService.java
```

### Main.java

- 참가자 데이터 등록
- 추첨 서비스 호출
- 결과 출력

### LotteryService.java

- 가중치 랜덤 추첨 로직 구현
- 당첨자 중복 방지
- 다수의 당첨자 추첨 지원

---

## 🚀 실행 방법

### 컴파일

```bash
javac Main.java LotteryService.java
```

### 실행

```bash
java Main
```

---

## 🧾 참가자 예시

```java
List<LotteryService.Participant> participants = List.of(
        new LotteryService.Participant("강지수", 6),
        new LotteryService.Participant("권태준", 6),
        new LotteryService.Participant("강우주", 2),
        new LotteryService.Participant("전시훈", 24),
        new LotteryService.Participant("박준형", 36)
);
```

---

## 🎲 추첨 방식

이 프로그램은 **가중치 랜덤(Weighted Random)** 방식을 사용합니다.

예를 들어 다음과 같은 참가자가 있다고 가정합니다.

| 이름 | 추첨권 |
|------|--------|
| 강우주 | 2 |
| 박준형 | 36 |

전체 추첨권은 38장입니다.

프로그램은 다음과 같이 구간을 생성합니다.

```text
1 ~ 2   → 강우주
3 ~ 38  → 박준형
```

이후 1 ~ 38 사이의 숫자를 랜덤으로 생성합니다.

```java
int randomNumber = random.nextInt(totalTickets) + 1;
```

생성된 숫자가 속한 구간의 참가자가 당첨됩니다.

따라서 당첨 확률은 다음과 같습니다.

```text
강우주 : 2 / 38  (약 5.3%)
박준형 : 36 / 38 (약 94.7%)
```

즉, 추첨권이 많을수록 당첨 확률이 높아집니다.

---

## 🏆 다중 당첨자 추첨

프로그램은 여러 명의 당첨자를 연속으로 추첨할 수 있습니다.

예시:

```java
List<String> winners = lotteryService.drawWinners(participants, 3);

System.out.println("1등: " + winners.get(0));
System.out.println("2등: " + winners.get(1));
System.out.println("3등: " + winners.get(2));
```

실행 결과:

```text
1등: 박준형
2등: 전시훈
3등: 오주영
```

---

## 🔄 중복 당첨 방지

당첨자가 선정되면 다음 추첨에서 자동으로 제외됩니다.

예를 들어:

```text
1등: 박준형
```

추첨 이후에는 내부 참가자 목록에서 박준형이 제거됩니다.

```java
remainingParticipants.removeIf(
        participant -> participant.name().equals(winner)
);
```

따라서 동일 인물이 여러 번 당첨되는 일이 발생하지 않습니다.

---

## 📄 핵심 코드

### Participant

참가자 정보를 저장하는 Record

```java
public record Participant(
        String name,
        int ticketCount
) {
}
```

### 추첨 로직

```java
public String draw(List<Participant> participants) {

    int totalTickets = participants.stream()
            .mapToInt(Participant::ticketCount)
            .sum();

    int target = random.nextInt(totalTickets) + 1;

    int cumulative = 0;

    for (Participant participant : participants) {

        cumulative += participant.ticketCount();

        if (target <= cumulative) {
            return participant.name();
        }
    }

    throw new IllegalStateException("추첨 실패");
}
```

---

## ⚡ 시간 복잡도

### 현재 방식

```text
시간복잡도 : O(N)
공간복잡도 : O(1)
```

N은 참가자 수입니다.

이 방식은 추첨권을 실제로 리스트에 저장하지 않기 때문에 메모리 사용량이 매우 적습니다.

예를 들어 추첨권이 100만 장이어도 참가자 수만큼만 순회하면 됩니다.

---

## ⚠️ 주의 사항

- 추첨권 개수는 0 이상이어야 합니다.
- 추첨권 총합이 0이면 추첨을 진행할 수 없습니다.
- 동일한 이름의 참가자가 중복 등록되지 않도록 관리하는 것을 권장합니다.

---
