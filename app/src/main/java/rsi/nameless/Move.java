package rsi.nameless;

/**
 * Created by Stéphanie on 20-5-2016.
 */
public class Move {
    private Map map;
    private CanvasMap sc;
    private float clX;
    private float clY;

    public Move(Map map, CanvasMap sc, float clickX, float clickY){
        this.map = map;
        this.sc = sc;
        clX = clickX;
        clY = clickY;
        tryMove();
    }

    public void tryMove(){
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
    }

    public void tryPoint(int nextP){
        if(Math.abs(clX-sc.getPathX()[nextP]) <= 150 && Math.abs(clY-sc.getPathY()[nextP]) <= 150) {
            map.setCurrentPoint(nextP);
        }
    }
}
