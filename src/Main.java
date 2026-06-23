import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<LotteryService.Participant> participants = List.of(

                new LotteryService.Participant("강지수", 6),
                new LotteryService.Participant("권태준", 6),
                new LotteryService.Participant("김진호", 1),
                new LotteryService.Participant("나예원", 1),
                new LotteryService.Participant("박규민", 9),
                new LotteryService.Participant("서연희", 7),
                new LotteryService.Participant("윤지원", 10),
                new LotteryService.Participant("이동훈", 11),
                new LotteryService.Participant("이승엽", 4),
                new LotteryService.Participant("이시하", 7),
                new LotteryService.Participant("이한세", 1),
                new LotteryService.Participant("정가연", 4),
                new LotteryService.Participant("정찬빈", 6),
                new LotteryService.Participant("채강욱", 1),
                new LotteryService.Participant("강신호", 10),
                new LotteryService.Participant("김시진", 8),
                new LotteryService.Participant("백승우", 2),
                new LotteryService.Participant("오주영", 12),
                new LotteryService.Participant("오형훈", 16),
                new LotteryService.Participant("이수연", 8),
                new LotteryService.Participant("임수종", 2),
                new LotteryService.Participant("박준서", 3),
                new LotteryService.Participant("황성진", 13),
                new LotteryService.Participant("강우주", 2),
                new LotteryService.Participant("이준희", 6),
                new LotteryService.Participant("전시훈", 24),
                new LotteryService.Participant("김동현", 7),
                new LotteryService.Participant("문성현", 15),
                new LotteryService.Participant("박준형", 36)
        );

        LotteryService lotteryService = new LotteryService();

        List<String> winners = lotteryService.drawWinners(participants, 3);

        System.out.println("1등: " + winners.get(0));
        System.out.println("2등: " + winners.get(1));
        System.out.println("3등: " + winners.get(2));

    }
}