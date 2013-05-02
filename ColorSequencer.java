import java.awt.Color;

class ColorSequencer {

  private Color curColor = new Color(130,251,23);

  public Color current() { return curColor; }
  
  public Color next() {
    int r = curColor.getRed();
    if (r == 0) r = 1;
    int g = curColor.getGreen();
    if (g == 0) g = 1;
    int b = curColor.getBlue();
    if (b == 0) b = 1;
    int newr = (int)(Math.random() * 43 * b) % 256;
    int newg = r;
    int newb = g;
    if (newr < 100 && newg < 100 && newb < 100) {
      if (Math.random() < 0.5) newg += 100;
      else newb += 100;
    }
    return (curColor = new Color(newr,newg,newb));
  }
}
