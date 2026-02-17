import com.sun.source.tree.BinaryTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        SearchTree tree = SearchTree.loadFromFile();//读取文件
        while(true){
            System.out.println("======银行账户管理系统======");
            System.out.println("1.开户   2.查询账户   3.存款");
            System.out.println("4.取款   5.展示账户   6.退出");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("账号：");
                    String id = sc.nextLine();
                    System.out.println("密码：");
                    String password = sc.nextLine();
                    System.out.println("初始金额：");
                    double bal= sc.nextDouble();
                    tree.insert(new Account(id,password,bal));
                    System.out.println("开户成功");
                    break;
                case 2:
                    System.out.println("请输入要查找的账号：");
                    Account a = tree.search(sc.nextLine());
                    if(a != null){
                        a.show();
                    }else{
                        System.out.println("账号不存在");
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
    }
}