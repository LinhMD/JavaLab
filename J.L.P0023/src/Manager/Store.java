package Manager;

import DTO.Fruit;

import java.util.ArrayList;

public class Store {
    private final ArrayList<Fruit> store;

    public Store(){
        this.store = new ArrayList<>();
    }

    public boolean addFruit(){
        Fruit e = this.inputFruit();
        if(this.store.contains(e))
            return false;
        return this.store.add(e);
    }

    private Fruit inputFruit(){
        String id;
        boolean check;
        do{
            id = InputValidation.getID("Input fruit id: ","ID invalid. Try format: AB123", "\\w{2}\\d{3}");
            if(check = isIDDuplicate(id))
                System.out.println("Id is duplicate!");
        }while(check);
        String name = InputValidation.getString("Input name: ", "name must not empty");
        String origin = InputValidation.getString("Input origin: ", "Origin must not empty");
        double price = InputValidation.getADouble("Input Price: ", "price invalid", 0, Double.MAX_VALUE);
        int quantity = InputValidation.getAnInteger("Input quantity: ", "Quantity invalid", 1, Integer.MAX_VALUE);
        return new Fruit(id, name, price, quantity, origin);
    }

    private boolean isIDDuplicate(String id){
        ArrayList<String> ids = new ArrayList<>();
        for (Fruit fruit: this.store) ids.add(fruit.getId());
        return ids.contains(id);
    }

    public Fruit buyFruit(){
        int i = 1;
        System.out.printf("|%8s|%20s|%20s|%20s|%10s|\n","Item", "Fruit name", "Origin", "Quantity","Price");
        for (Fruit fruit: this.store)
            System.out.printf("|%8d|%20s|%20s|%20d|%9.2f$|\n", i++, fruit.getName(), fruit.getOrigin(), fruit.getQuantity(), fruit.getPrice());
        int index = InputValidation.getAnInteger("Input Item index: ", "Index must be integer" , 1, this.store.size());
        int quantity = InputValidation.getAnInteger("Input quantity: ", "Quantity invalid", 1, this.store.get(--index).getQuantity());
        if(this.store.get(index).getQuantity() == 0){
            return this.store.get(index);
        }else{
            Fruit temp = this.store.get(index);
            Fruit order = new Fruit(temp.getId(), temp.getName(),temp.getPrice(), quantity, temp.getOrigin());
            int newQuantity = this.store.get(index).getQuantity() - quantity;
            this.store.get(index).setQuantity(newQuantity);
            return order;
        }
    }
}
