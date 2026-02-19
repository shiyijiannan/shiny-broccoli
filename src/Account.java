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

    //取钱
    public void withdrew(double withdrewMoney){
        if(withdrewMoney<0){
            System.out.println("取款金额要大于零");
        }else if(withdrewMoney>money){
            System.out.println("余额不足");
        }else{
            money -= withdrewMoney;
            System.out.println("取款成功，余额："+money);
        }
    }

    //存钱
    public void keep(double keepMoney){

    }
}
