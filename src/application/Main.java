//Author:Fourkan Mechmet

/*Το σύστημα είναι μια εφαρμογή διαχείρισης υπόπτων και επικοινωνιών. Δημιουργεί και 
 * αποθηκεύει δεδομένα για υπόπτους, συνεργάτες, κλήσεις και μηνύματα. Παρέχει γραφικό περιβάλλον
 *  για αναζήτηση υπόπτων και οπτικοποίηση του δικτύου συνεργασιών τους. Χρησιμοποιείται για ανάλυση 
 *  και παρακολούθηση σχέσεων σε δίκτυα.*/

package application;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
    	
       // Δημιουργία υπόπτων
        Suspect s1 = new Suspect("John Dow", "Sleepy Dog", "Barcelona");
        
         s1.addNumber("00496955444444");        
         s1.addNumber("00496955333333");       

        Suspect s2 = new Suspect("Danny Rust", "Rusty Knife", "London");     
        s2.addNumber("00446999888888");

        Suspect s3 = new Suspect("Bob Robson", "Frozen Bear", "Oslo");
        s3.addNumber("00478484777777");   
        s3.addNumber("00478484666666");   
        
        s3.addNumber("00478484222222");

        
        
        Suspect s4 = new Suspect("Nick McGee", "Big Tuna", "San Francisco");
        
        s4.addNumber("00496955222222");

        Suspect s5 = new Suspect("Luigi Marecchio", "Joe Bananas", "Naples");
        
        s5.addNumber("00496955111111");

        
        
        // Δημιουργία επικοινωνιών
         Communication[] comms = new Communication[16];

         comms[0] = new PhoneCall("00496955444444", "00478484777777", LocalDate.of(2021, 10, 15), 127); 
         comms[1] = new PhoneCall("00496955444444", "00478484777777", LocalDate.of(2021, 10, 16), 240);  
        comms[2] = new PhoneCall("00446999888888", "00496955333333", LocalDate.of(2021, 10, 17), 52);      
        comms[3] = new PhoneCall("00446999888888", "00478484777777", LocalDate.of(2021, 10, 18), 180); 
        
         comms[4] = new PhoneCall("00478484666666", "00496955333333", LocalDate.of(2021, 10, 19), 305);  
        comms[5] = new PhoneCall("00496955444444", "00478484222222", LocalDate.of(2021, 10, 20), 247);
          comms[6] = new PhoneCall("00478484222222", "00496955333333", LocalDate.of(2021, 10, 21), 32);

          //SMS WITH NUM1 TO NUM2 
        comms[7] = new Sms("00496955444444", "00478484777777", LocalDate.of(2021, 10, 10), "Fancy a drink tonight?");
         comms[8] = new Sms("00496955333333", "00446999888888", LocalDate.of(2021, 10, 11), "Nitro Bomb prepared");
         comms[9] = new Sms("00446999888888", "00496955444444", LocalDate.of(2021, 10, 12), "Flying to Berlin tomorrow");
         comms[10] = new Sms("00478484777777", "00446999888888", LocalDate.of(2021, 10, 13), "No internet connection today");
        comms[11] = new Sms("00478484777777", "00446999888888", LocalDate.of(2021, 10, 14), "Gun received from Rusty Knife");
         comms[12] = new Sms("00478484777777", "00446999888888", LocalDate.of(2021, 10, 15), "Metro attack ready");
         comms[13] = new Sms("00478484666666", "00446999888888", LocalDate.of(2021, 10, 16), "Explosives downtown placed");

        // Δημιουργία Registry
        Registry registry = new Registry();
        
        registry.addSuspect(s1);   
        registry.addSuspect(s2);   
        registry.addSuspect(s3);   
        registry.addSuspect(s4);    
        registry.addSuspect(s5);   

        for (Communication comm : comms) { 
            if (comm != null) {  
                registry.addCommunication(comm);
                
            }
        }

        // Προσθήκη συνεργατών για τις επιπλέον συνδέσεις  
          s3.addPartner(s4); // Frozen Bear -> Big Tuna
         s4.addPartner(s3); // Big Tuna -> Frozen Bear   

         s4.addPartner(s5); // Big Tuna -> Joe Bananas
        s5.addPartner(s4); // Joe Bananas -> Big Tuna

        
        
     // Δημιουργία και εμφάνιση του SearchPage , απο εκει μετα αναλογος η τρεχετ Graphvisualizer η λιστα με επαφες συνεργατες κτλ 
        SearchPage searchPage = new SearchPage(registry);
        
         searchPage.setVisible(true);
    
    
        // Εκκίνηση οπτικοποίησης δικτύου
     //   GraphVisualizer visualizer = new GraphVisualizer(registry.createSuspectGraph());
       // visualizer.setVisible(true);
    }
}
