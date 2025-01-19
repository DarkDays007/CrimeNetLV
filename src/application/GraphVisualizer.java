package application;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphVisualizer extends JFrame {
	
    private Map<Suspect, List<Suspect>> graph;

    public GraphVisualizer(Map<Suspect, List<Suspect>> graph) {
    	
        this.graph = graph;   
        setTitle("Suspect Network Visualization");
        setSize(800, 600);    
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        add(new GraphPanel());   
    }  

    private class GraphPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {   
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g;    
   
            int centerX = getWidth() / 2;  
            int centerY = getHeight() / 2;   
            int radius = 200;
   
            int numNodes = graph.size();    
            Point[] points = new Point[numNodes];  
            int index = 0;   

            for (Suspect suspect : graph.keySet()) {   
                // Υπολογισμός θέσης κόμβων σε κυκλική διάταξη  
                double angle = 2 * Math.PI * index / numNodes;
                int x = centerX + (int) (radius * Math.cos(angle));  
                int y = centerY + (int) (radius * Math.sin(angle));     
                points[index] = new Point(x, y);    

                // Σχεδίαση κόμβου  
                g2d.fillOval(x - 10, y - 10, 20, 20);    
                g2d.drawString(suspect.getCodeName(), x + 15, y);   
                index++;  
                
                 
            }

            // Σχεδίαση ακμών
            
            index = 0;
            
            for (Suspect suspect : graph.keySet()) {
                 for (Suspect partner : graph.get(suspect)) {   
                    int partnerIndex = new ArrayList<>(graph.keySet()).indexOf(partner);  
                    if (partnerIndex > index) {    
                        g2d.drawLine(points[index].x, points[index].y, points[partnerIndex].x, points[partnerIndex].y);
                    }
                } 
                index++;     
            }   
        }
    }
}

