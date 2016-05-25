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
            case 1:
                map.setCurrentPoint(9);
                break;
            case 2:
            case 3:
                tryPoint(1);
                break;
            case 5:
                tryPoint(3);
            case 4:
                tryPoint(2);
                break;
            case 6:
                tryPoint(3);
                break;
            case 7:
                tryPoint(4);
                tryPoint(5);
                break;
            case 8:
                tryPoint(5);
                tryPoint(6);
                break;
            case 9:
                tryPoint(7);
                tryPoint(8);
                break;
        }
    }

    public void tryPoint(int nextP){
        if(Math.abs(clX-sc.getPathX()[nextP-1]) <= 150 && Math.abs(clY-sc.getPathY()[nextP-1]) <= 150) {
            map.setCurrentPoint(nextP);
        }
    }
}
