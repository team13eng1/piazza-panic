package Tools;

import Sprites.Chef;
import com.team13.piazzapanic.idleScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class chefAI {


    //TODO make a ai to make burgers and salad

    private idleScreen game;
    private Chef mychef;
    private int targetx = 4;
    private int targetY = 1;

    private int[][] targetsPoints = {{4,1},{4,8},{9,8},{9,1}};
    private int[][] myIntArray =
                    {{19, 18, 18, 18, 18, 18, 18, 18, 18, 20,},
                    {16, 21, 21, 21, 21, 21, 21, 21, 21, 17,},
                    {16, 21, 21, 21, 21, 21, 21, 21, 21, 17,},
                    {1, 2, 2, 2, 1, 1, 1, 1, 1, 1,},
                    {1, 10, 10, 10, 10, 10, 10, 10, 10, 1,},
                    {1, 10, 10, 10, 10, 10, 10, 10, 10, 1,},
                    {1, 10, 10, 4, 6, 5, 1, 10, 10, 15,},
                    {1, 10, 10, 1, 1, 8, 7, 10, 10, 15,},
                    {14, 10, 10, 10, 10, 10, 10, 10, 10, 1,},
                    {1, 10, 10, 10, 10, 10, 11, 12, 12, 1,},
                    {1, 1, 13, 1, 1, 1, 3, 9, 9, 1}};
    private int targetVal = 0;

    public chefAI(idleScreen game) {
        this.game = game;




    }


    public String pickMovementDirection(){
        int[] myData = convertPosToXY(game.chef1.getNotificationX(), game.chef1.getNotificationY());
        if (myData[0] == targetx && myData[1] == targetY || myData[0] == 0){
            System.out.println("i arrived at my target node");
            targetx = targetsPoints[targetVal][1];
            targetY = targetsPoints[targetVal][0];
            targetVal ++;
            if (targetVal >= 4){targetVal = 0;}
            return "";
        }

        List<String> targets = defineShortestPath(myData[1], myData[0], targetx, targetY);
        int tarX = Integer.parseInt( targets.get(targets.size() - 1).split(",")[1]);
        int tarY = Integer.parseInt( targets.get(targets.size() - 1).split(",")[0]);
        System.out.println(String.format("my target was %d %d :::: %d %d ", tarX, tarY, myData[0], myData[1]));
        if (tarX < myData[0]){return "a";}
        if (tarX > myData[0]){return "d";}
        if (tarY > myData[1]){return "s";}
        if (tarY < myData[1]){return  "w";}

        return "w";
    }

    public String returnKeyboardInput() {
        //System.out.println(String.format("chef x: %f chef y: %f ",game.chef1.getX(), game.chef1.getX() )); ;
        System.out.println(String.format("chef x: %f chef y: %f ",game.chef1.getNotificationX(), game.chef1.getNotificationY() ));
        convertPosToXY(game.chef1.getNotificationX(), game.chef1.getNotificationY());
        //System.out.println("\n\n" + game.map.getLayers().get(0).getObjects(). + "\n\n");
        return pickMovementDirection();
    }

    public List<String> defineShortestPath(int cols, int rows, int endNode, int endCol) {
        HashMap<String, String> nodes = new HashMap<String, String>();
        HashMap<String, String> visited = new HashMap<String, String>();
        List<String> fringe = new ArrayList<>();
        defineInit(nodes, cols, rows);


        visitNextNodes(cols, rows, fringe, nodes, visited);
        visited.put("2,2", "visited");
        while (fringe.size() > 0) {
            String fringe0 = fringe.get(0);

            String[] args = fringe0.split(",");
            fringe.remove(0);
            visitNextNodes(Integer.parseInt(args[0]), Integer.parseInt(args[1]), fringe, nodes, visited);
            visited.put(fringe0, "visited");
        }

        List<String> targets = printShortestPath(endCol,endNode,nodes);

        return targets;


    }


    private List<String> printShortestPath(int startCol, int startRow, HashMap<String, String> nodes){

        List<String> pathToFollow = new ArrayList<>();
        String currentNode = Integer.toString(startCol) + "," + Integer.toString(startRow);
        String endNode = "INIT";
        while (currentNode.equals(endNode) == false){
            System.out.println(currentNode + "apples");
            pathToFollow.add(currentNode);
            String temp = nodes.get(currentNode);
            currentNode = temp.split(",")[2];
            currentNode = currentNode.replace(":", ",");


        }
        pathToFollow.remove(pathToFollow.size() - 1);
        return pathToFollow;

    }


    private void defineInit(HashMap<String, String> nodes, int cols, int rows) {
        nodes.put(Integer.toString(cols) + "," + Integer.toString(rows), "0,C,INIT");
    }

    private void visitNextNodes(int nodeCol, int nodeRow, List<String> fringe, HashMap<String, String> nodes, HashMap<String, String> visited) {


        if (visited.containsKey(Integer.toString(nodeCol) + "," + Integer.toString(nodeRow))) {
            return;
        } else {
            visited.put(Integer.toString(nodeCol) + "," + Integer.toString(nodeRow), "visited");
        }

        int temprow;
        int tempCol;
        String tempStr;
        // 0-10
        //0-9

        //Left
        if (nodeRow > 0) {
            temprow = nodeRow - 1;
            tempCol = nodeCol;
            process(fringe, tempCol, temprow,nodes,visited, nodeCol, nodeRow);


        }
        //Right
        if (nodeRow < 9) {
            temprow = nodeRow + 1;
            tempCol = nodeCol;
            process(fringe,tempCol, temprow, nodes,visited, nodeCol, nodeRow);

        }

        //up
        if (nodeCol > 0) {
            temprow = nodeRow;
            tempCol = nodeCol - 1;
            process(fringe,tempCol, temprow, nodes,visited, nodeCol, nodeRow);


        }

        //down
        if (nodeCol < 10) {
            temprow = nodeRow;
            tempCol = nodeCol + 1;
            process(fringe,tempCol, temprow, nodes,visited, nodeCol, nodeRow);

        }

    }



    private int[] convertPosToXY(double x, double y){ //This function is assuming x and y and tiles the player can move to
        //bottomY = 0.208184     topY = 1.056333
        //xStart = 0.198333      //xMidle = 0.441956
        double tileWidth = (0.441956 - 0.198333) / 2;
        double[] bottomLeftTileCorner = {0.198333, 0.208184 };

        int tileNumX = (int) ((x - bottomLeftTileCorner[0]) / tileWidth);
        int tileNumY = (int) ((y - bottomLeftTileCorner[1]) / tileWidth);
        System.out.println("x tile is:" + getXconv(tileNumX));
        System.out.println("y tle is " + getYconv(tileNumY));
        return new int[]{getXconv(tileNumX), getYconv(tileNumY)};



    }

    private int getYconv(int y){
        if (y < 4) { return 9 - y;}
        return 10 - y;
    }
    private int getXconv(int x){
        if (x > 4) { return x - 1;}
       return  x + 1;

    }


    private void process(List<String> fringe, int tempCol, int temprow, HashMap<String, String> nodes, HashMap<String, String> visited, int nodeCol, int nodeRow) {
        if (myIntArray[tempCol][temprow] != 10 &&myIntArray[tempCol][temprow] != 11 && myIntArray[tempCol][temprow] != 12){
            return;
        }

        String tempStr;
        tempStr = Integer.toString(tempCol) + "," + Integer.toString(temprow);
        if (fringe.contains(tempStr) && visited.containsKey(tempStr) == false) {

        } else {
            //System.out.println("i added this" + tempStr);
            fringe.add(tempStr);
        }


        String[] myParts = nodes.get(Integer.toString(nodeCol) + "," + Integer.toString(nodeRow)).split(",");
        if (nodes.containsKey(tempStr)) {
            String[] nextParts = nodes.get(tempStr).split(",");


            if (Integer.parseInt(myParts[0]) < Integer.parseInt(nextParts[0])) {
                String newStr = Integer.toString(Integer.parseInt(myParts[0]) + 1) + "," + "U," + Integer.toString(nodeCol) + ":" + Integer.toString(nodeRow);
            }

        } else {
            //System.out.println("running with " + tempStr);

            nodes.put(tempStr, Integer.toString(Integer.parseInt(myParts[0]) + 1) + ",U," + Integer.toString(nodeCol) + ":" + Integer.toString(nodeRow));
            fringe.add(tempStr);
        }
    }
}