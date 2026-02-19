import com.sun.source.tree.BinaryTree;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        SearchTree tree = SearchTree.loadFromFile();//读取文件
        int i=3;
        while(true){
            System.out.println("======银行账户管理系统======");
            System.out.println("1.开户   2.查询账户   3.存款");
            System.out.println("4.取款   5.展示账户   6.退出");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    String id = "";
                    boolean validId = false;
                    while (!validId) {
                        System.out.println("====开 户====");
                        System.out.println("===按1取消===");
                        System.out.print("账号：");
                        id = sc.nextLine().trim();
                        if ("1".equalsIgnoreCase(id)) {
                            System.out.println("已取消开户操作！");
                            validId = true; // 结束循环
                            continue;
                        }
                        // 检查账号是否为空
                        if (id.isEmpty()) {
                            System.out.println("账号不能为空，请重新输入！");
                            continue;
                        }
                        // 检查账号是否已存在
                        if (tree.search(id) != null) {
                            System.out.println("账号" + id + "已存在，请重新输入！");
                        } else {
                            validId = true;
                        }
                    }
                    if ("1".equalsIgnoreCase(id)) {
                        break;
                    }
                    System.out.print("密码：");
                    String password = sc.nextLine();
                    System.out.print("初始余额：");
                    double bal = sc.nextDouble();
                    tree.insert(new Account(id, password, bal));
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
                    System.out.println("账号：");
                    Account d = tree.search(sc.nextLine());
                    if(d == null){
                        System.out.println("账号不存在：");
                        break;
                    }
                    System.out.println("请输入密码：");
                    if(d.password.compareTo(sc.nextLine())==0){
                        System.out.println("取款：");

                    }else{
                        System.out.println("密码错误");
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    tree.saveData();
                    System.out.println("数据已保存，欢迎下次使用");
                    sc.close();
                    return;
                default:
                    if(i>1) {
                        i--;
                        System.out.println("请按要求输入");
                    }else {
                        System.out.println("系统强制退出");
                        i=3;
                        return;
                    }
            }
        }
    }
}