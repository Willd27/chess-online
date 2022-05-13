package Game;

import javax.swing.*;

public class UI extends JFrame {
    private DrawingCanvas drawingCanvas;

    public UI(){
        this.drawingCanvas = new DrawingCanvas();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.add(drawingCanvas);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void updateUI() {
        drawingCanvas.repaint();
    }
}
