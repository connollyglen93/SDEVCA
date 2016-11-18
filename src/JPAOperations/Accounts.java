/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAOperations;

/**
 *
 * @author x00131787
 */
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@SuppressWarnings("SerializableClass")
abstract class Accounts {
    @Id
    protected String id;
    protected double balance;
    protected int pin;
    protected int noOfLodgements = 0;
    protected int noOfWithdrawals = 0;
    
    @ManyToMany(mappedBy = "accList")   //many to many relationship with customer
    private List<Customer> clist = new ArrayList<>();
    
    @OneToMany(mappedBy = "acc", orphanRemoval=true) //one to many relationship with lodgement
    private List<Lodgements> lodgelist = new ArrayList<>();
        
    @OneToMany(mappedBy = "acc", orphanRemoval=true) //one to many relationship with withdraw
    private List<Withdrawals> withlist = new ArrayList<>();
    
    public Accounts(){
        this.id = "";
        this.balance = 0;
        this.pin = 0;      
    }
    
    public Accounts(String id, double balance, int pin){
        this.id = id;
        this.balance = balance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setClist(List<Customer> custs){
        clist = custs;
    }
    
    public List<Customer> getClist(){
        return clist;
    }
    
    public void addCustomer(Customer c) {
        clist.add(c);
        c.getAccList().add(this);
    }
    
    abstract Withdrawals makeWithdrawal(double amount, String type);
    
    abstract Lodgements makeLodgement(double amount, String type);
    
    public void addLodgement(Lodgements l){
        lodgelist.add(l);
        l.setAcc(this);
    }
    
    public void addWithdrawal(Withdrawals w){
        withlist.add(w);
        w.setAcc(this);
    }    
    
    @Override
    public String toString(){
        return "Account ID: " + id + " Balance: " + balance;
    }

}
