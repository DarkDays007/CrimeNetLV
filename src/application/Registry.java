package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registry {
    private List<Suspect> suspects;
    private List<Communication> communications;

    public Registry() {
        this.suspects = new ArrayList<>();    
         this.communications = new ArrayList<>();
    }

    // Προσθήκη ύποπτου
    public void addSuspect(Suspect suspect) {
        if (!suspects.contains(suspect)) {
        	
            suspects.add(suspect);
        }
    }

    
    // Προσθήκη επικοινωνίας   
     public void addCommunication(Communication communication) {
    	
         communications.add(communication);

        Suspect suspect1 = findSuspectByPhoneNumber(communication.getNumber1());
         Suspect suspect2 = findSuspectByPhoneNumber(communication.getNumber2());
        

        //ελεγχος για να μην ειναι ιδιοι και αν ειναι null
        if (suspect1 != null && suspect2 != null && !suspect1.equals(suspect2)) {
            suspect1.addPartner(suspect2);
            suspect2.addPartner(suspect1);
        }
        
    }

    // Εύρεση ύποπτου βάσει τηλεφώνου,διασχειζει το suspect και αναζηταει με βαση κινητο
     private Suspect findSuspectByPhoneNumber(String phoneNumber) {
        for (Suspect suspect : suspects) {
            if (suspect.getPhoneNumbers().contains(phoneNumber)) {
                return suspect;
            }
        }
        return null;    
        
        
    }
    //αναζητηση μυνηματων συμμεταξη num1 num2 
     public List<Sms> getMessagesBetween(List<String> suspectNumbers, String phoneNumber) {
        List<Sms> result = new ArrayList<>();
        for (Communication comm : communications) {
        	
             if (comm instanceof Sms) {    
                Sms sms = (Sms) comm;        
                // Έλεγχος αν το SMS ανταλλάχθηκε μεταξύ του ύποπτου αριθμού και του συγκεκριμένου αριθμού
                 if ((suspectNumbers.contains(sms.getNumber1()) && sms.getNumber2().equals(phoneNumber)) ||
                       (suspectNumbers.contains(sms.getNumber2()) && sms.getNumber1().equals(phoneNumber))) {
                     result.add(sms);
                }
            }
        }
        return result;
    }  
    
     
    
    // Δημιουργία γράφου υπόπτων
       public Map<Suspect, List<Suspect>> createSuspectGraph() {
         Map<Suspect, List<Suspect>> graph = new HashMap<>();
           for (Suspect suspect : suspects) {
              graph.put(suspect, suspect.getPartners());
           }
           return graph;
    }

    // Επιστροφή της λίστας των υπόπτων
       public List<Suspect> getSuspects() {
            return suspects;
    }
}

