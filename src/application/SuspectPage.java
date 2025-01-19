package application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SuspectPage extends JFrame {
	
    public SuspectPage(Suspect suspect, Registry registry) {
    	
    	//sizes etc
        setTitle("Suspect Page");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        
        
        // Πάνω τμήμα: Όνομα και κωδικό όνομα
        
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JLabel nameLabel = new JLabel("Name: " + suspect.getName());  
        JLabel codeNameLabel = new JLabel("Code Name: " + suspect.getCodeName());  
        topPanel.add(nameLabel);   
        topPanel.add(codeNameLabel); 

        // Τμήμα τηλεφωνικών αριθμών
        JPanel phonePanel = new JPanel(new BorderLayout());  
        JTextArea phoneNumbersArea = new JTextArea();   
        
        phoneNumbersArea.setEditable(false);   
        
        
        for (String number : suspect.getPhoneNumbers()) {
            phoneNumbersArea.append(number + "\n");    
        }
        
        
        phonePanel.add(new JLabel("Phone numbers:"), BorderLayout.NORTH);
        phonePanel.add(new JScrollPane(phoneNumbersArea), BorderLayout.CENTER);

        
        
        // Αναζήτηση SMS
        
        JPanel centerPanel = new JPanel(new BorderLayout());  
        JPanel smsSearchPanel = new JPanel(new FlowLayout());    
        JTextField phoneNumberField = new JTextField(15);   
        JButton findSmsButton = new JButton("Find SMS");
           
         smsSearchPanel.add(new JLabel("Enter Phone Number:"));   
          smsSearchPanel.add(phoneNumberField);
           smsSearchPanel.add(findSmsButton);   

           
           JTextArea smsDisplayArea = new JTextArea();  
        smsDisplayArea.setEditable(false);   
        centerPanel.add(smsSearchPanel, BorderLayout.NORTH);   
        centerPanel.add(new JScrollPane(smsDisplayArea), BorderLayout.CENTER);
   
        //Αναζητηση για sms        
        findSmsButton.addActionListener(new ActionListener() {   
        	
            @Override
            public void actionPerformed(ActionEvent e) {   
                String phoneNumber = phoneNumberField.getText();   
                
                List<Sms> messages = registry.getMessagesBetween(suspect.getPhoneNumbers(), phoneNumber);        
                smsDisplayArea.setText("");    
                   
                if (!messages.isEmpty()) {
                    for (Sms sms : messages) {  
                        smsDisplayArea.append(sms.getMessage() + "\n");        
                    }             
                } else {   
                    smsDisplayArea.setText("No messages found for this phone number.");   
                }
            }  
        });
        
        

        // Τμήμα συνεργατών   
        
        JPanel partnersPanel = new JPanel(new BorderLayout());
        JTextArea partnersArea = new JTextArea();
        partnersArea.setEditable(false);
        //φερνει απο λιστα partner name k codename
        for (Suspect partner : suspect.getPartners()) {
            partnersArea.append(partner.getName() + ", " + partner.getCodeName() + "\n");
        }
        
        partnersPanel.add(new JLabel("Partners:"), BorderLayout.NORTH);
        partnersPanel.add(new JScrollPane(partnersArea), BorderLayout.CENTER);

        // Προτεινόμενοι συνεργάτες
        JPanel suggestedPanel = new JPanel(new BorderLayout());
        JTextArea suggestedArea = new JTextArea();
        suggestedArea.setEditable(false);
        
          for (Suspect suggested : suspect.getSuggestedSuspects()) {
             suggestedArea.append(suggested.getName() + ", " + suggested.getCodeName() + "\n");
         }
        suggestedPanel.add(new JLabel("Suggested Partners:"), BorderLayout.NORTH);
        
        suggestedPanel.add(new JScrollPane(suggestedArea), BorderLayout.CENTER);
        

        // Κουμπί επιστροφής
        
        JPanel bottomPanel = new JPanel();            
        JButton backButton = new JButton("Back to Search Screen");                  
         backButton.addActionListener(e -> dispose());
        bottomPanel.add(backButton);   

        // Προσθήκη τμημάτων στο παράθυρο
        
        add(topPanel, BorderLayout.NORTH); 
        
          add(phonePanel, BorderLayout.WEST);
         add(centerPanel, BorderLayout.CENTER);   
         add(partnersPanel, BorderLayout.EAST);
           add(suggestedPanel, BorderLayout.SOUTH); 
         add(bottomPanel, BorderLayout.PAGE_END);
    }
}
