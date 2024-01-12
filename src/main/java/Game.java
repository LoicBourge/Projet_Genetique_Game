import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Game {
    int maxTic;
    int nbrCreatures = 10;
    double mutationRate = 0.02;
    double crossoverRate = 0.5;
    double pourcentkeepparents = 0.2;
    int keepNumber = 2;
    int nbrGeneration = 10;

    ArrayList<Creature> creatures = new ArrayList<>();
    //Creature creature;
    Environment environment;

    public Game(int maxTic, Environment environment) {
        this.maxTic = maxTic;
        this.environment = environment;
        for (int i = 0; i < nbrCreatures; i++) {
            creatures.add(new Creature(new Environment(environment), Movement.generate(15)));
        }
    }

    private Creature playGame(boolean print) {

        for (int i = 0; i < nbrGeneration; i++) {
            for (Creature creature : creatures) {
                System.out.println("Creature debut : " + creature.getEnvironment().getCreaturepositon());

                int temp = maxTic;
                for (int j = 0; j < temp; j++) {
                    temp = creature.nextMove(j, temp, print);
                }
                System.out.println("Score: " + creature.getScore());
                System.out.println("Creature fin");
            }
            newGeneration();
            System.out.println("End generation " + i);
        }
        return creatures.stream().min(Comparator.comparingDouble(Creature::getScore)).orElse(null);
    }


    private Creature bestfitselection() {
        int nbrkeepparents = (int) (creatures.size() * pourcentkeepparents);
        return creatures.stream().sorted(Comparator.comparingDouble(value -> Math.random()))
                .limit(nbrkeepparents)
                .min(Comparator.comparingDouble(Creature::getScore)).orElse(null);
    }

    private void newGeneration() {
        List<Creature> sortedCreatures = creatures.stream().sorted(Comparator.comparingDouble(Creature::getScore))
                .limit(keepNumber).collect(Collectors.toList());
        for (int i = keepNumber; i < nbrCreatures; i++) {
            Creature parent1 = bestfitselection();
            Creature parent2 = bestfitselection();
            Creature child = parent1.crossover(parent2, new Environment(environment), crossoverRate).mutate(mutationRate);
            sortedCreatures.add(child);
        }
        creatures = new ArrayList<>(sortedCreatures);
    }

    public static void main(String[] args) {
        Environment environment = new Environment("src/main/resources/test.csv");
        Game game = new Game(50, environment);
        Creature bestcreature = game.playGame(true);
        System.out.println("Best creature " + bestcreature);
    }
}
