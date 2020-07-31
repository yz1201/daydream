package cn.dbdj1201.jvm.sectionobject;

/**
 * @author yz1201
 * @date 2020-07-30 18:46
 **/
public class Customer {

    int id =1001;
    String name;
    Account account;
    {
        name="匿名客户";
    }

    public Customer(){
        account = new Account();
    }
}

class Account{

}