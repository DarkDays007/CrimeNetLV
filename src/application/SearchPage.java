package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPage extends JFrame {
    private Registry registry;

    public SearchPage(Registry registry) {   
        this.registry = registry; 
        setTitle("Find Suspect");   
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
         setLayout(null);
  
        //πεδιο αναζητησης 
        JLabel searchLabel = new JLabel("Please enter suspect's name:");
         searchLabel.setBounds(10, 10, 200, 25);
         
        add(searchLabel);  
          
          JTextField searchField = new JTextField();
        searchField.setBounds(10, 40, 200, 25);
        
        add(searchField);
           
        
        JButton findButton = new JButton("Find");
        
        findButton.setBounds(220, 40, 60, 25);
        add(findButton);
        
        
        
        

        //σε registry ψαχνει suspect 
          findButton.addActionListener(new ActionListener() {
        	  
            @Override
             public void actionPerformed(ActionEvent e) {
                 String name = searchField.getText();
                  Suspect suspect = registry.getSuspects().stream()
                         .filter(s -> s.getName().equalsIgnoreCase(name))
                        .findFirst().orElse(null);

                 if (suspect != null) {
                        new SuspectPage(suspect, registry).setVisible(true);
                  } else {
                    JOptionPane.showMessageDialog(SearchPage.this, "Suspect not found.");
                }
            }
        });

        //το γραφημα
           JButton visualizeButton = new JButton("Visualize Network");
         visualizeButton.setBounds(10, 80, 200, 25);
        add(visualizeButton);

        visualizeButton.addActionListener(e -> {
             GraphVisualizer visualizer = new GraphVisualizer(registry.createSuspectGraph());
            visualizer.setVisible(true);
        });
    }
}
