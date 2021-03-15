package Manager;

import DTO.*;

import java.util.*;

public class CandidateManager {
    //key is type of candidate(obj.getClass().toString()), value is list of candidate
    private final HashMap<String, ArrayList<Candidate>> candidatesMap;
    //for checking purpose:
    private final TreeSet<String> ids;

    public CandidateManager(){
        this.candidatesMap = new HashMap<>();
        this.ids = new TreeSet<>();
    }

    private boolean addCandidate(Candidate candidate){
        ArrayList<Candidate> candidates;
        String type = candidate.getClass().toString().toLowerCase();
        type = type.substring(type.lastIndexOf(".") + 1);
        if (!candidatesMap.containsKey(type))
            candidates = new ArrayList<>();
        else
            candidates = candidatesMap.get(type);
        if(ids.add(candidate.getCandidateID())){
            candidates.add(candidate);
            candidatesMap.put(type, candidates);
            return true;
        }else return false;
    }

    private boolean searchCandidate(){
        if(this.ids.isEmpty()){
            System.out.println("List of candidate is empty");
            return false;
        }
        String type = "";
        int typeInt;
        String name = InputValidation.getString("Input candidate name: ", "Name can not empty");
        typeInt = InputValidation.getAnInteger("Input type: ", "Type must be 0/1/2", 0,2);
        switch (typeInt){
            case 0: type = "experience"; break;
            case 1: type = "fresher"; break;
            case 2: type = "intern"; break;
        }
        boolean check = false;
        ArrayList<Candidate> candidates = candidatesMap.get(type);
        for (Candidate candidate: candidates)
            if (candidate.getLastName().contains(name) ||
                    candidate.getFirstName().contains(name)){
                System.out.println(candidate.toString());
                check = true;
            }
        return check;
    }

    private void printAll(){
        Collection<String> types = this.candidatesMap.keySet();
        for (String type : types){
            System.out.println("===========" + type.toUpperCase() + " CANDIDATE============");
            ArrayList<Candidate> candidates = this.candidatesMap.get(type);
            for (Candidate candidate: candidates)
                System.out.println(candidate.getFirstName() + " " + candidate.getLastName());
        }
    }

    public CandidateManager menu(){
        this.printAll();
        boolean check = true;
        System.out.println("1.Experience");
        System.out.println("2.Fresher");
        System.out.println("3.Internship");
        System.out.println("4.Searching");
        System.out.println("5.Exit");
        switch (InputValidation.getAnInteger("Input your option: ", "Option invalid", 1, 5)){
            case 1: check = this.addCandidate(new Experience().input(this.ids)); break;
            case 2: check = this.addCandidate(new Fresher().input(this.ids)); break;
            case 3: check = this.addCandidate(new Intern().input(this.ids)); break;
            case 4: check = this.searchCandidate(); break;
            case 5: return this;
            default:
                System.out.println("how did you get here?");
        }
        if(check)
            System.out.println("Action done successful");
        else
            System.out.println("Action failed");
        return this.menu();
    }
    public static void main(String[] args) {
        new CandidateManager().menu();
    }
}
