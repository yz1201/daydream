package cn.dbdj1201.jvm.sectionobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yz1201
 * @date 2020-07-30 18:46
 **/
//@Data
@AllArgsConstructor
public class Customer implements Cloneable {

    int id = 1001;
    String name;
    Account account;

    {
        name = "匿名客户";
    }

    public Customer() {
        account = new Account();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Customer clone = (Customer) super.clone();
        clone.setAccount((Account) this.account.clone());
        return clone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}

class Account implements Cloneable {
    private int id;
    private Integer serialId;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}