import java.util.ArrayList;

public class Creature {

    //private Environment environment;
    private final Environment environment;

    private ArrayList<Movement> movements;


    public Creature(Environment environment, ArrayList<Movement> movements) {
        this.environment = environment;
        this.movements = movements;
        System.out.println(movements.size());
    }


    public Creature mutate(double mutationRate) {
        for (int i = 0; i < movements.size(); i++) {
            Movement movement;
            if (Math.random() <= mutationRate) {
                movement = Movement.values()[(int) (1 + Math.random() * 7)];
                movements.set(i, movement);
            }
        }
        return this;
    }


    public Creature crossover(Creature b, Environment environment, double crossoverRate) {
        ArrayList<Movement> newmovements = new ArrayList<>();
        for (int i = 0; i < movements.size(); i++) {
            if (Math.random() <= crossoverRate) {
                newmovements.add(movements.get(i));
            } else {
                newmovements.add(b.movements.get(i));
            }
        }
        System.out.println("Tailles mouvements");
        System.out.println(movements.size());
        System.out.println(newmovements.size());
        return new Creature(new Environment(environment), newmovements);
    }

    @Override
    public String toString() {
        return "Creature{" +
                "environment=" + environment +
                ", movements=" + movements +
                ", score=" + getScore() +
                '}';
    }

    public double getScore() {
        double X = environment.getCreaturepositon().getX() - environment.getEnd().getX();
        double Y = environment.getCreaturepositon().getY() - environment.getEnd().getY();
        X *= X;
        Y *= Y;
        var availableMouvementsCount = movements.size() - movements.stream().filter(movement -> movement == Movement.IDLE).count();
        double sqrt = Math.sqrt(X + Y);
        return (sqrt != 0 ? 1.0 / (sqrt) : 0) + availableMouvementsCount;
    }


    public ArrayList<Movement> getMovements() {
        return movements;
    }

    public void setMovements(ArrayList<Movement> movements) {
        this.movements = movements;
    }

    public int nextMove(int indice, int maxTic, boolean print) {
        if (environment.getCreaturepositon().equals(environment.getEnd())) {
            System.err.println("Fin ");
            return 0;
        }
        if (indice < getMovements().size()) {
            maxTic = environment.movecreature(movements.get(indice), maxTic);
            if(maxTic == 0)
            {
                for(int i = indice+1; i < movements.size(); i++)
                    movements.set(i, Movement.IDLE);
            }
        } else {
            maxTic = 0;
            for(int i = indice+1; i < movements.size(); i++)
                movements.set(i, Movement.IDLE);
        }
        if (print) {
            System.out.println("tic restant: " + maxTic);
            environment.printGrid();
        }
        return maxTic;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
