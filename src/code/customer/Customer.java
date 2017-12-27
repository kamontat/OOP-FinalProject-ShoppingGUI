package code.customer;

import code.constant.MemberClass;
import code.store.Order;
import code.store.OrderElement;

import java.util.*;

public class Customer extends Person {
    private String customerID;
    private MemberClass memberClass;
    private ArrayList<OrderElement> basketList;
    private ArrayList<Order> historyList;
    private static int numCustomers;
    
    /**
     * for create guest member
     */
    public Customer() {
        super();
        memberClass = MemberClass.NONE;
        basketList = new ArrayList<>();
        historyList = new ArrayList<>();
        customerID = "GUEST";
    }
    
    public Customer(String ID, String name, String lastName, String gender, int age, MemberClass memberClass) {
        super(ID, name, lastName, gender, age);
        numCustomers++;
        this.memberClass = memberClass;
        basketList = new ArrayList<>();
        historyList = new ArrayList<>();
        customerID = "Member" + numCustomers;
    }
    
    public Customer(String ID, String name, String lastName, String gender, String age, String memberClass) {
        super(ID, name, lastName, gender, Integer.parseInt(age));
        numCustomers++;
        this.memberClass = MemberClass.checkMember(memberClass);
        basketList = new ArrayList<>();
        historyList = new ArrayList<>();
        customerID = "Member" + numCustomers;
    }
    
    public String getCustomerID() {
        return customerID;
    }
    
    public MemberClass getMemberClass() {
        return memberClass;
    }
    
    public ArrayList<OrderElement> getBasketList() {
        return basketList;
    }
    
    public ArrayList<Order> getHistoryList() {
        return historyList;
    }
    
    public double getDiscount() {
        return getPrice() * memberClass.getDiscount();
    }
    
    /**
     * get original price without minus any discount
     *
     * @return price
     */
    public double getPrice() {
        double price = 0;
        for (OrderElement basket : basketList) {
            price += basket.getProduct().getPrice() * basket.getNum();
        }
        return price;
    }
    
    /**
     * price that calculate discount already
     *
     * @return price
     */
    public double getTotalPrice() {
        return getPrice() - getDiscount();
    }
    
    /**
     * get only product count by type
     *
     * @return num product
     */
    public int getNumProduct() {
        return basketList.size();
    }
    
    /**
     * get total product include num in orderElement
     *
     * @return num product
     */
    public int getTotalProduct() {
        int num = 0;
        for (OrderElement ele : basketList) {
            num += ele.getNum();
        }
        return num;
    }
    
    public double getWeight() {
        double weight = 0;
        for (OrderElement basket : basketList) {
            weight += basket.getProduct().getWeight() * basket.getNum();
        }
        return weight;
    }
    
    public static int getNumCustomers() {
        return numCustomers;
    }
    
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    
    public void setMemberClass(MemberClass memberClass) {
        this.memberClass = memberClass;
    }
    
    public void setBasketList(ArrayList<OrderElement> basketList) {
        this.basketList = basketList;
    }
    
    public void setHistoryList(ArrayList<Order> historyList) {
        this.historyList = historyList;
    }
    
    public static void setNumCustomers(int numCustomers) {
        Customer.numCustomers = numCustomers;
    }
    
    public void addToBasket(OrderElement element) {
        // 0 is mean don't order
        if (element.getNum() != 0) {
            basketList.add(element);
        }
    }
    
    public boolean removeFromBasket(OrderElement element) {
        for (int i = 0; i < basketList.size(); i++) {
            if (basketList.get(i).equals(element)) {
                basketList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public Object[][] getBasketToArray() {
        // don't have basket yet.
        if (basketList.size() == 0) {
            return null;
        }
        
        // first [] is row of all orderElement, second [] is get info of pruduct and add by 1 (Because number in OrderElement)
        Object[][] temp = new Object[basketList.size()][basketList.get(0).getProductInfoExtra().length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = basketList.get(i).getProductInfoExtra();
        }
        return temp;
    }
    
    public void clearBasket() {
        basketList.clear();
    }
    
    public void addToHistoryList(Order order) {
        if (order.getBuyList().size() != 0) {
            historyList.add(order.clone());
        }
    }
    
    /**
     * array in form (ID, Name, LastName, Gender, Age, Class)
     * return null if input element more than it have
     *
     * @param element
     *         element of array
     * @return array with element element
     */
    public Object[] getCustomerInfo(int element) {
        Object[] all = new Object[]{
                getID(), getName(), getLastName(), getGender(), getAge(), getMemberClass().getName()
        };
        if (element <= all.length) {
            Object[] temp = new Object[element];
            // copy all into temp with element
            System.arraycopy(all, 0, temp, 0, element);
            return temp;
        }
        return null;
    }
    
    public Object[] getHistoryListArray() {
        Object[] temp = new Object[historyList.size()];
        int i = 0;
        for (Order history : historyList) {
            String format = "OrderNumber: %s %s";
            temp[i++] = String.format(format, history.getOrderID(), history.toString());
        }
        return temp;
    }
    
    /**
     * <b>This method use in ExpendProduct/history only</b>
     * the will print information of buyList in <code>index</code> in form of {"Order ID", "Name", "Type", "Size", "Weight", "Num", "Price"} <br>
     * and return array 2D of information, if don't have <code>index</code> id return null.
     *
     * @param index
     *         index of historyList
     * @return object array
     */
    public Object[][] getHistoryListInform(int index) {
        Object[][] temp = new Object[historyList.get(index).getBuyList().size() + 1][7];
        
        for (int j = 0; j < temp.length - 1; j++) {
            if (j == 0) temp[j][0] = historyList.get(index).getOrderID(); // order id
            else temp[j][0] = "";
            
            temp[j][1] = historyList.get(index).getBuyList().get(j).getProduct().getName(); // name
            temp[j][2] = historyList.get(index).getBuyList().get(j).getProduct().getTypeToString(); // type
            temp[j][3] = historyList.get(index).getBuyList().get(j).getProduct().getSize(); // size
            temp[j][4] = historyList.get(index).getBuyList().get(j).getProduct().getWeight(); // weight
            temp[j][5] = historyList.get(index).getBuyList().get(j).getNum(); // number of product
            temp[j][6] = historyList.get(index).getBuyList().get(j).getPriceToString(); // price
        }
        temp[temp.length - 1] = historyList.get(index).informationArray();
        return temp;
    }
    
    /**
     * <b>This method use in ExpendProduct/history only</b>
     * the will print information of all in buyList in form of {"Order ID", "Name", "Type", "Size", "Weight", "Num", "Price"} <br>
     * and return array 2D of information, if don't have <code>id</code> id return null.
     *
     * @return object array
     */
    public Object[][] getHistoryListInform() {
        int size = 0;
        for (Order history : historyList) {
            size += history.getBuyList().size();
        }
        
        ArrayList<Object[]> temp = new ArrayList<>();
        
        for (int i = 0; i < historyList.size(); i++) {
            Object[][] temp2 = getHistoryListInform(i);
            Collections.addAll(temp, temp2);
        }
        return temp.toArray(new Object[][]{});
    }
    
    public Customer clone() {
        numCustomers--;
        Customer temp = new Customer(getID(), getName(), getLastName(), getGender(), getAge(), getMemberClass());
        temp.setHistoryList(historyList);
        temp.setBasketList(getBasketList());
        
        return temp;
    }
    
    public boolean equals(String personID, String name, String lastname) {
        return super.getID().equals(personID) && super.getName().equals(name) && super.getLastName().equals(lastname);
    }
    
    public String toString() {
        String format = "%s, %s, %s %s, %s, %d";
        if (getCustomerID().equals("GUEST")) {
            return "GUEST member";
        }
        return String.format(format, this.customerID, super.getID(), super.getName(), super.getLastName(), super.getGender(), super.getAge());
    }
}