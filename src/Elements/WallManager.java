package Elements;

import Panels.GamePanel;

import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class WallManager {
    private int n ;
    private boolean[] toBeOrNotTobe ;
    private  ArrayList<ArrayList<Wall>> wallsList ;
    private Random random ;

    public WallManager(int n){
        this.n = n ;
        toBeOrNotTobe = new boolean[n];
        wallsList = new ArrayList<>();
        random = new Random();
        spawnWalls(1);
    }

    public void setN(int n ){
        this.n = n ;
        toBeOrNotTobe = new boolean[n];
    }



    public void spawnWalls(int number ){
        ArrayList<Wall> walls = new ArrayList<>();
        toBeOrNotTobe = new boolean[n];
        if(number%3==0){
            int i = random.nextInt(0 , 3);
            toBeOrNotTobe[i%n] = true;
            toBeOrNotTobe[(i+2)%n] = true ;
            toBeOrNotTobe[(i+4)%n] = true;
            System.out.println(toBeOrNotTobe.toString());
        } else if (number%3==1) {
            for(int i = 0 ; i < n ; i++){
                toBeOrNotTobe[i] = true;
            }
            toBeOrNotTobe[number%n] = false;
        } else generateRandom();

        for(int i = 0 ; i < n ; i++){
            if(toBeOrNotTobe[i]){
                walls.add(new Wall(n , 800 , 450 , 350 , i , GamePanel.color));

            }
        }
        wallsList.add(walls);
    }
    public void checkInteredCenter(){
       if(!wallsList.isEmpty()){
           ArrayList<Wall> walls = wallsList.get(0);
           if(!walls.isEmpty()){
               if(   walls.get(0).getR() < GamePanel.getR()) wallsList.removeFirst();
           } else wallsList.removeFirst();
       }
    }
    public ArrayList<ArrayList<Wall>> getWalls(){
        return wallsList;
    }



    private void generateRandom(){
        int counter = 0 ;
        for(int i = 0 ; i < n ; i++){
            toBeOrNotTobe[i] = random.nextBoolean();
            if(toBeOrNotTobe[i]) counter++;
        }
        if (counter==n) toBeOrNotTobe[random.nextInt(0 , 6)] = false;

    }


}
