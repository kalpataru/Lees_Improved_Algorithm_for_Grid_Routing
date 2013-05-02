import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class LeeApplet extends Applet implements Runnable, ActionListener
{
  private Grid myGrid = null; // Grid class instantion

  private Thread myThread = null;
  
  private static final int WAITFORSRC = 0;
  private static final int WAITFORTGT = 1;

  private GridPoint clicked = null;
  
  Button startBtn, stopBtn, pauseBtn, resumeBtn, clearBtn, nextBtn;

  public void init() {
  
    int nlayers=1;
      int ncols = Grid.calculateCols(getSize().width);
      int nrows = Grid.calculateRows(getSize().height, nlayers);
	  
      myGrid = new Grid(ncols,nrows,nlayers); // Grid class instance initialization
     
      setLayout(new BorderLayout());
      add(myGrid, "Center");
	  
      startBtn = new Button("Start");
	  pauseBtn = new Button("Pause");
	  resumeBtn = new Button("Resume");
	  clearBtn = new Button("Clear");
	  nextBtn = new Button("Next");
	  
      Panel btnPanel = new Panel();
      btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      btnPanel.add(startBtn);
	  btnPanel.add(pauseBtn);
	  btnPanel.add(resumeBtn);
	  btnPanel.add(clearBtn);
      add(btnPanel, "South");
	  startBtn.addActionListener(this);
	  pauseBtn.addActionListener(this);
	  resumeBtn.addActionListener(this);
      clearBtn.addActionListener(this);
	  
      show();
      repaint();
	 
   }

 	public void actionPerformed(ActionEvent ae){
		if((ae.getSource()==startBtn)&&(myThread==null)){
			myThread=new Thread(this);
			myThread.start();
		}
		else if((ae.getSource()==pauseBtn)&&(myThread!=null)){
			myThread.suspend();
		}
		else if((ae.getSource()==resumeBtn)&&(myThread!=null)){
			myThread.resume();
		}
		else if((ae.getSource()==clearBtn)&&(myThread!=null)){
			myGrid.requestClear();
		}
	}

   public void run() {
     try {
       myGrid.run();
     } catch (InterruptedException e) {}
   }
}
