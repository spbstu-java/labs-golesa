public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero();
        hero1.move(1,2);

        hero1.setMoveStrat(new HorseRideStrategy());
        hero1.move(2,3);

        (new Hero(new FlyStrategy())).move(4,5);
    }
    
    interface MoveStrategy {
        void move(int from, int to);
    }

    static class WalkStrategy implements MoveStrategy {
        public void move(int from, int to) {
            System.out.println("Walk from " + from + " to " + to);
        }
    }

    static class HorseRideStrategy implements MoveStrategy {
        public void move(int from, int to) {
            System.out.println("Ride horse from " + from + " to " + to);
        }
    }

    static class FlyStrategy implements MoveStrategy {
        public void move(int from, int to) {
            System.out.println("Fly from " + from + " to " + to);
        }
    }

    static class Hero {
        private static byte n = 0;
        private final String name_ = "Hero " + ++n;

        private MoveStrategy moveStrat_;

        public Hero() {
            moveStrat_ = new WalkStrategy();
        }

        public Hero(MoveStrategy moveStrat) {
            moveStrat_ = moveStrat;
        }

        public void setMoveStrat(MoveStrategy moveStrat) {
            moveStrat_ = moveStrat;
        }

        public void move(int from, int to) {
            System.out.print(name_ + " -> ");
            moveStrat_.move(from, to);
        }
    }

}