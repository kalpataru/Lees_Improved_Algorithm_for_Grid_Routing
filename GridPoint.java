import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GridPoint {

  private int posx;
  private int posy;
  private int posz;
  private static final int ROUTED = -2;
  private static final int OBSTACLE = -1;
  private static final int UNROUTED = 10000;
  
  public GridPoint(int x, int y, int z) { // Constructor Originally called by Grid class
    posx = x;      // Posx, posy,posz initialized   
    posy = y;
    posz = z;
  }
  
  public GridPoint(int x, int y) {
    this(x, y, 0);
  }

  
  public GridPoint northNeighbor() {
    return myGrid.gridPointAt(posx, posy-1, posz);
  }

  public GridPoint southNeighbor() {
    return myGrid.gridPointAt(posx, posy+1, posz);
  }

  public GridPoint eastNeighbor() {
    return myGrid.gridPointAt(posx+1, posy, posz);
  }

  public GridPoint westNeighbor() {
    return myGrid.gridPointAt(posx-1, posy, posz);
  }

  public GridPoint upNeighbor() {
    return myGrid.gridPointAt(posx, posy, posz-1);
  }

  public GridPoint downNeighbor() {
    return myGrid.gridPointAt(posx, posy, posz+1);
  }

  public int expand() throws InterruptedException 
  {
    GridPoint xp;//..............................................Object of GridPoint class
  
    if ( (xp = westNeighbor()) != null && xp.val == UNROUTED ) {
      xp.val = val+1;
      if (xp.isTarget()) return xp.val;
      else myGrid.enqueueGridPoint(xp);
    }
	
    if ( (xp = eastNeighbor()) != null && xp.val == UNROUTED ) {
      xp.val = val+1;
      if (xp.isTarget()) return xp.val;
      else myGrid.enqueueGridPoint(xp);
    }
	
    if ( (xp = southNeighbor()) != null && xp.val == UNROUTED ) {
      xp.val = val+1;
      if (xp.isTarget()) return xp.val;
      else myGrid.enqueueGridPoint(xp);
    }
	
    if ( (xp = northNeighbor()) != null && xp.val == UNROUTED ) {
      xp.val = val+1;
      if (xp.isTarget()) return xp.val;
      else myGrid.enqueueGridPoint(xp);
    }
    if ( (xp = upNeighbor()) != null && xp.val == UNROUTED ) {
      xp.val = val+1;
      if (xp.isTarget()) return xp.val;
      else myGrid.enqueueGridPoint(xp);
    }
    if ( (xp = downNeighbor()) != null && xp.val == UNROUTED ) {
      xp.val = val+1;
      if (xp.isTarget()) return xp.val;
      else myGrid.enqueueGridPoint(xp);
    }
    return -1;
  }

  public boolean isTarget() {
    return ( this == myGrid.getTarget() );
  }

  public boolean isSource() {
    return ( this == myGrid.getSource() );
  }

  public boolean isEmpty() {
    return val == UNROUTED;
  }

  public void paintGridPoint(Graphics g) {
    g.setColor(Color.black);
    g.drawRect(myGrid.gridPanelX(posx,posy,posz),myGrid.gridPanelY(posx,posy,posz),
        myGrid.GRIDSIZE,myGrid.GRIDSIZE);
    if (isRouted()) {
      g.setColor(routedColor);
      fillGridPoint(g);
    } else if (isObstacle()) {
      g.setColor(Color.red);
      fillGridPoint(g);
    }
    else if (isSource()) {
      if (highlighted) g.setColor(Color.yellow);
      else g.setColor(Color.red);
      fillGridPoint(g);
      g.setColor(Color.black);
      labelGridPoint(g, "S");
    } else if (isTarget()) {
      if (highlighted) {
        g.setColor(Color.green);
        fillGridPoint(g);
        g.setColor(Color.black);
		 if(val==0)
			labelGridPoint(g, Integer.toString(0));
		else
			labelGridPoint(g, Integer.toString(((val-1) % 3)+1));
      } else {
        g.setColor(Color.red);
        fillGridPoint(g);
        g.setColor(Color.black);
        labelGridPoint(g, "T");
      }
    } else if (val < UNROUTED) {
      if (isEnqueued()) g.setColor(Color.pink);
      else g.setColor(Color.green);
      fillGridPoint(g);
      g.setColor(Color.black);
	 if(val==0)
		labelGridPoint(g, Integer.toString(0));
	 else
		labelGridPoint(g, Integer.toString(((val-1) % 3)+1));
    }
  }

  private void fillGridPoint(Graphics g) {
    g.fillRect(myGrid.gridPanelX(posx,posy,posz)+2,myGrid.gridPanelY(posx,posy,posz)+2,
        myGrid.GRIDSIZE-3,myGrid.GRIDSIZE-3);
  }
  private void labelGridPoint(Graphics g, String s) {
    g.drawString(s, myGrid.gridPanelX(posx,posy,posz)+myGrid.CHARXOFFSET,
                    myGrid.gridPanelY(posx,posy,posz)+myGrid.CHARYOFFSET);
  }

  public String toString() {
    return "GridPoint(" + posx + "," + posy  + "," + posz + ")[" + val + "]";
  }

  public void reset() { val = UNROUTED; }

  public void initExpand() { val = 0; }

  public void setRouted() {
    val = ROUTED;
    routedColor = cseq.current();
  }

  public boolean isRouted() { return (val == ROUTED); }

  public void setObstacle() { val = OBSTACLE; }

  public boolean isObstacle() { return (val == ROUTED || val == OBSTACLE); }

  public boolean lessThan(GridPoint p2) { return val != 0 && val < p2.val; }
  
  public int manhattanDistance(GridPoint p2) {
    return ( Math.abs(p2.posx - posx) + Math.abs(p2.posy - posy) +
      Math.abs(p2.posz - posz) );
  }
  
  private boolean highlighted = false;
  private boolean enqueued = false;
  boolean isEnqueued()  { return enqueued; }
  void setEnqueued(boolean e) { enqueued = e; }

  private int val = UNROUTED;

  private Color routedColor = null;

  public int getVal() { return val; }

  public void highlight(boolean h) { highlighted = h; }

  static Grid myGrid;

  private static ColorSequencer cseq = new ColorSequencer();

  public static void nextRouteColor() { cseq.next(); }
}
