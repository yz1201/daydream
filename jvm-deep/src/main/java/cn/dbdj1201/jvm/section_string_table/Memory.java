package cn.dbdj1201.jvm.section_string_table;

/**
 * @author yz1201
 * @date 2020-08-02 15:07
 **/
public class Memory {
    public static void main(String[] args) {
        int i = 0;
        Object obj = new Object();
        Memory memory = new Memory();
        memory.foo(obj);
    }

    private void foo(Object obj) {
        String str = obj.toString();
        System.out.println(str);
    }
}
