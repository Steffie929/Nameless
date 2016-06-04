package rsi.nameless;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Move {
    private Map map;
    private CanvasMap sc;
    private float clX;
    private float clY;
    private boolean succes;

    public Move(Map map, CanvasMap sc, float clickX, float clickY){
        this.map = map;
        this.sc = sc;
        clX = clickX;
        clY = clickY;
        succes = false;
    }

    public Move(Map map, CanvasMap sc){
        this.map = map;
        this.sc = sc;
        succes = false;
    }

    public boolean tryMove(){
        int currentPoint = map.getCurrentPoint();

        switch(currentPoint){
            case 0:
                map.setCurrentPoint(8);
                break;
            case 1:
            case 2:
                tryPoint(0);
                break;
            case 4:
                tryPoint(2);
            case 3:
                tryPoint(1);
                break;
            case 5:
                tryPoint(2);
                break;
            case 6:
                tryPoint(3);
                tryPoint(4);
                break;
            case 7:
                tryPoint(4);
                tryPoint(5);
                break;
            case 8:
                tryPoint(6);
                tryPoint(7);
                break;
        }
        return succes;
    }

    public void tryPoint(int nextP){
        if(Math.abs(clX-sc.getPathX()[nextP]) <= 150 && Math.abs(clY-sc.getPathY()[nextP]) <= 150) {
            map.setCurrentPoint(nextP);
            succes = true;
        }
    }

    public boolean swipedLeft(int currentPoint){
        switch(currentPoint){
            case 0:
                break;
            case 1:
                break;
            case 2:
                map.setCurrentPoint(0);
                succes = true;
                break;
            case 3:
                break;
            case 4:
                map.setCurrentPoint(1);
                succes = true;
                break;
            case 5:
                map.setCurrentPoint(2);
                succes = true;
                break;
            case 6:
                map.setCurrentPoint(3);
                succes = true;
                break;
            case 7:
                map.setCurrentPoint(4);
                succes = true;
                break;
            case 8:
                map.setCurrentPoint(6);
                succes = true;
                break;

        }

        return succes;
    }

    public boolean swipedRight(int currentPoint){
        switch(currentPoint){
            case 0:
                break;
            case 1:
                map.setCurrentPoint(0);
                succes = true;
                break;
            case 2:
                break;
            case 3:
                map.setCurrentPoint(1);
                succes = true;
                break;
            case 4:
                map.setCurrentPoint(2);
                succes = true;
                break;
            case 5:
                break;
            case 6:
                map.setCurrentPoint(4);
                succes = true;
                break;
            case 7:
                map.setCurrentPoint(5);
                succes = true;
                break;
            case 8:
                map.setCurrentPoint(7);
                succes = true;
                break;
        }
        return succes;

    }
}
