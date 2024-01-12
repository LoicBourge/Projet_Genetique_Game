import java.io.FileReader;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class Environment {
    private int m;
    private int n;
    private Coordinates begin;
    private Coordinates end;
    private Coordinates creaturepositon; //TODO mettre la position dans la creature<

    private EnvironmentTile[][] grid;

    public Environment(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public Environment(Environment e) {
        this.m = e.m;
        this.n = e.n;
        this.begin = e.begin;
        this.end = e.end;
        this.creaturepositon = e.creaturepositon;
        this.grid = e.grid.clone();
        for(int i=0;i<e.grid.length;i++){
            this.grid[i]=e.grid[i].clone();
        }
    }


    public Environment(String pathname) {
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(pathname));
            String[] nextLine = reader.readNext();
            m = Integer.parseInt(nextLine[0]);
            n = Integer.parseInt(nextLine[1]);
            grid = new EnvironmentTile[m][n];
            int i = 0, j = 0;
            while ((nextLine = reader.readNext()) != null) {
                for (String token : nextLine) {
                    grid[i][j] = EnvironmentTile.values()[Integer.parseInt(token)];
                    if (grid[i][j] == EnvironmentTile.START) {
                        begin = new Coordinates(j, i);
                        creaturepositon = begin;
                    }
                    if (grid[i][j] == EnvironmentTile.END) {
                        end = new Coordinates(j, i);
                    }
                    //System.out.print(token);
                    j++;
                }
                i++;
                j = 0;
                //System.out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.deepToString(grid));

    }

    private void setCreaturepositon(Coordinates newcreaturepositon) {
        //System.out.println("Creature position " + creaturepositon + "begin " + begin);
        if (creaturepositon.equals(begin)) {
            grid[creaturepositon.getY()][creaturepositon.getX()] = EnvironmentTile.START;
        } else {
            grid[creaturepositon.getY()][creaturepositon.getX()] = EnvironmentTile.VIDE;
        }
        creaturepositon = newcreaturepositon;
        grid[creaturepositon.getY()][creaturepositon.getX()] = EnvironmentTile.CREATURE;
        //System.out.println("Creature position end : " + creaturepositon);
    }

    public int movecreature(Movement movement, int maxtic) {
        System.out.println("Mouv " + movement + " X " + movement.getX() + " Y " + movement.getY());
        Coordinates newposition = new Coordinates(creaturepositon.getX() + movement.getX(), creaturepositon.getY() + movement.getY());
        //System.out.println("Creature position " + creaturepositon + " newposition " + newposition + " |" + grid[newposition.getX()][newposition.getY()] + "|");
        if (grid[newposition.getY()][newposition.getX()] != EnvironmentTile.TILE) {
            setCreaturepositon(newposition);
            //printGrid();
            maxtic--;
            System.err.println("POSITIONS:");
            System.err.println(creaturepositon);
            System.err.println(end);
            if (creaturepositon.equals(end)) {
                System.out.println("Stop");
                return 0;
            }
            maxtic = movegraviti(movement, maxtic);
        } else {
            maxtic--;
        }
        return maxtic;
    }


    public int movegraviti(Movement movement, int maxtic) {
        if (movement == Movement.UP || movement == Movement.RIGHT || movement == Movement.LEFT || movement == Movement.DOWN_LEFT || movement == Movement.DOWN_RIGHT) {
            Coordinates newposition = new Coordinates(creaturepositon.getX(), creaturepositon.getY() + 1);
            while (grid[newposition.getY()][newposition.getX()] != EnvironmentTile.TILE && maxtic != 0) {
                setCreaturepositon(newposition);
                newposition = new Coordinates(creaturepositon.getX(), creaturepositon.getY() + 1);
                maxtic--;
            }
        } else {
            if (movement == Movement.UP_LEFT) {
                Coordinates under = new Coordinates(creaturepositon.getX(), creaturepositon.getY() + 1);
                Coordinates left = new Coordinates(creaturepositon.getX()-1, creaturepositon.getY());
                Coordinates under_left = new Coordinates(creaturepositon.getX() - 1, creaturepositon.getY() + 1);
                //System.out.println("Grid under :"+grid[newposition.getY()][newposition.getX()]);
                while (grid[under.getY()][under.getX()] != EnvironmentTile.TILE && maxtic != 0) {
                    if(grid[left.getY()][left.getX()] != EnvironmentTile.TILE){
                        if(grid[under_left.getY()][under_left.getX()] != EnvironmentTile.TILE) {
                            setCreaturepositon(under_left);
                        } else {
                            setCreaturepositon(under);
                        }
                    } else {
                        setCreaturepositon(under);
                    }
                    under = new Coordinates(creaturepositon.getX(), creaturepositon.getY() + 1);
                    left = new Coordinates(creaturepositon.getX()-1, creaturepositon.getY());
                    under_left = new Coordinates(creaturepositon.getX() - 1, creaturepositon.getY() + 1);
                    maxtic--;
                }

            } else {
                if (movement == Movement.UP_RIGHT) {
                    Coordinates under = new Coordinates(creaturepositon.getX(), creaturepositon.getY() + 1);
                    Coordinates right = new Coordinates(creaturepositon.getX()+1, creaturepositon.getY());
                    Coordinates under_right = new Coordinates(creaturepositon.getX() + 1, creaturepositon.getY() + 1);
                    //System.out.println("Grid under :"+grid[newposition.getY()][newposition.getX()]);
                    while (grid[under.getY()][under.getX()] != EnvironmentTile.TILE && maxtic != 0) {
                        if(grid[right.getY()][right.getX()] != EnvironmentTile.TILE){
                            if(grid[under_right.getY()][under_right.getX()] != EnvironmentTile.TILE) {
                                setCreaturepositon(under_right);
                            } else {
                                setCreaturepositon(under);
                            }
                        } else {
                            setCreaturepositon(under);
                        }
                        under = new Coordinates(creaturepositon.getX(), creaturepositon.getY() + 1);
                        right = new Coordinates(creaturepositon.getX()+1, creaturepositon.getY());
                        under_right = new Coordinates(creaturepositon.getX() + 1, creaturepositon.getY() + 1);
                        maxtic--;
                    }
                }
            }
        }
        return maxtic;
    }

    @Override
    public String toString() {
        return "environment{" +
                "m=" + m +
                ", n=" + n +
                ", begin=" + begin +
                ", end=" + end +
                ", grid=\n" + Arrays.deepToString(grid) +
                '}';
    }

    public void printGrid() {
        for (EnvironmentTile[] environmentTiles : grid) {
            for (EnvironmentTile environmentTile : environmentTiles) {
                System.out.print(environmentTile + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public Coordinates getEnd() {
        return end;
    }

    public void setEnd(Coordinates end) {
        this.end = end;
    }

    public Coordinates getCreaturepositon() {
        return creaturepositon;
    }

}
