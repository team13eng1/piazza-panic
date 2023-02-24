package Tools;

import com.team13.piazzapanic.idleScreen;

public class chefAI {





    //TODO make a ai to make burgers and salad

    private idleScreen game;

    public chefAI(idleScreen game){
        this.game = game;

        int[][] myIntArray = {{19,18,18,18,18,18,18,18,18,20,},
                {16,21,21,21,21,21,21,21,21,17,},
                {16,21,21,21,21,21,21,21,21,17,},
                {1,2,2,2,1,1,1,1,1,1,},
                {1,10,10,10,10,10,10,10,10,1,}, {1,10,10,10,10,10,10,10,10,1,},
                {1,10,10,4,6,5,1,10,10,15,},
                {1,10,10,1,1,8,7,10,10,15,}, {14,10,10,10,10,10,10,10,10,1,},
                {1,10,10,10,10,10,11,12,12,1,},
                {1,1,13,1,1,1,3,9,9,1}};













    }
    public String returnKeyboardInput(){
        //System.out.println(String.format("chef x: %f chef y: %f ",game.chef1.getX(), game.chef1.getX() )); ;
        //System.out.println(String.format("chef x: %f chef y: %f ",game.chef1.getNotificationX(), game.chef1.getNotificationY() ));

        //System.out.println("\n\n" + game.map.getLayers().get(0).getObjects(). + "\n\n");
        return "w";
    }
}