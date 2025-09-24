import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero();
        Scanner scan = new Scanner(System.in);;
        int from = 0;
        int to = from;

        System.out.println("Move strategy: " + hero1.moveStratName);
        while (true) {
            System.out.print("Change move strategy - c | Move - m | Quit - q --> ");
            switch (scan.nextLine().charAt(0)) {
                case 'c':
                    System.out.print("Walk - 1 | Horse ride - 2 | Fly - 3 --> ");
                    hero1.setMoveStrat(
                        switch (scan.hasNextInt() ? scan.nextInt() : 1) {
                            case 2 -> new HorseRideStrategy();
                            case 3 -> new FlyStrategy();
                            default -> new WalkStrategy();
                        }
                    );
                    System.out.println("Move strategy changed to " + hero1.moveStratName + "\n");
                    scan.nextLine();
                break;
                case 'm':
                    System.out.print("Move to (int) --> ");
                    to = scan.hasNextInt() ? scan.nextInt() : from;
                    hero1.move(from, to);
                    from = to;
                    System.out.println();
                    scan.nextLine();
                break;
                case 'q':
                    return;
            }
        }
    }

    interface MoveStrategy {
        String getName();
        void move(int from, int to);
    }

    static class WalkStrategy implements MoveStrategy {
        public String getName() {
            return "Walk";
        }

        public void move(int from, int to) {
            System.out.println("Hero walk from " + from + " to " + to);
        }
    }

    static class HorseRideStrategy implements MoveStrategy {
        public String getName() {
            return "Horse ride";
        }

        public void move(int from, int to) {
            System.out.println("Hero ride horse from " + from + " to " + to);
        }
    }

    static class FlyStrategy implements MoveStrategy {
        public String getName() {
            return "Fly";
        }

        public void move(int from, int to) {
            System.out.println("Hero fly from " + from + " to " + to);
        }
    }

    static class Hero {
        private MoveStrategy moveStrat_;
        public String moveStratName;

        public Hero() {
            setMoveStrat(new WalkStrategy());
        }

        public void setMoveStrat(MoveStrategy moveStrat) {
            moveStrat_ = moveStrat;
            moveStratName = moveStrat_.getName();
        }

        public void move(int from, int to) {
            moveStrat_.move(from, to);
        }
    }
}