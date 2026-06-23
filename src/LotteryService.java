import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotteryService {

    private final Random random = new Random();


    public String draw
            (List<Participant> participants) {

        int totalTickets = participants
                .stream()
                .mapToInt(Participant::ticketCount)
                .sum();

        int target = random
                .nextInt(totalTickets) + 1;

        int cumulative = 0;

        for (Participant participant : participants) {

            cumulative += participant.ticketCount();

            if (target <= cumulative) {
                return participant.name();
            }

        }

        throw new IllegalStateException("추첨 실패");

    }

    public List<String> drawWinners
            (List<Participant> participants,
             int winnerCount) {

        List<Participant> remainingParticipants = new ArrayList<>(participants);
        List<String> winners = new ArrayList<>();

        for (int i = 0; i < winnerCount; i++) {

            String winner = draw(remainingParticipants);
            winners.add(winner);
            remainingParticipants.removeIf(
                    participant -> participant.name().equals(winner)
            );
        }
        return winners;
    }

    public record Participant(String name,
                              int ticketCount) {
    }
}
