public class Account {
    String id;
    String password;
    double money;

    public Account(String id, String password, double balance) {
        this.id = id;
        this.password = password;
        this.money = balance;
    }//初始化

    //展示
    public  void show(){
        System.out.println("账号："+id+ "\n" +"余额："+money);
    }
}
