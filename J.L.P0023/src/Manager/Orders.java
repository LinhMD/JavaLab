package Manager;

import DTO.Fruit;

import java.util.ArrayList;

public class Orders {
    ArrayList<Fruit> Orders;

    public Orders(){
        this.Orders = new ArrayList<>();
    }

    public void viewOrders(){
        int total = 0;
        System.out.printf("%20s | %20s | %19s$ | %19s$\n", "product", "quantity", "price", "amount");
        for (int i = 0; i < this.Orders.size(); i++) {
            Fruit order = this.Orders.get(i);
            double amount = order.getPrice() * order.getQuantity();
            System.out.printf("%3d.%16s | %20d | %20f | %20.2f\n", (i+1), order.getName(), order.getQuantity(), order.getPrice(), amount);
            total += amount;
        }
        System.out.println("total: " + total +"$");
    }

    public void addOrder(Fruit order){
        if(order.getQuantity() == 0){
            System.out.println("Out of Stock");
            return;
        }
        if(Orders.contains(order)){// case customer already order the fruit we just need to add the quantity
            int index = this.findOrderIndex(order);
            int newQuantity = this.Orders.get(index).getQuantity() + order.getQuantity();
            this.Orders.get(index).setQuantity(newQuantity);
        }else // just add the order to arraylist
            this.Orders.add(order);
    }

    private int findOrderIndex(Fruit order){
        for (int i = 0; i < this.Orders.size(); i++) {
            if(this.Orders.get(i).equals(order))
                return i;
        }
        return -1;
    }
}
