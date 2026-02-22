import com.sun.source.tree.BinaryTree;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        SearchTree tree = SearchTree.loadFromFile();//读取文件
        int i=3;
        int if_log=0;
        while(true){
            System.out.println("======银行账户管理系统======");
            System.out.println("1.开户   2.查询账户    3.取款");
            System.out.println("4.存款   5.管理员登录   6.退出");
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
                        d.withdrew(sc.nextInt());
                    }else{
                        System.out.println("密码错误");
                    }
                    break;
                case 4:
                    System.out.println("账号：");
                    Account b = tree.search(sc.nextLine());
                    if(b == null){
                        System.out.println("账号不存在");
                        break;
                    }
                    System.out.println("密码：");
                    if(b.password.compareTo(sc.nextLine())==0){
                        System.out.println("存款金额：");
                        b.keep(sc.nextInt());
                    }else {
                        System.out.println("密码错误：");
                    }
                    break;
                case 5:
                    manage M = new manage();
                    System.out.println("===管理员登录===");
                    System.out.println("请输入管理员姓名：");
                    if(M.manageName.compareTo(sc.nextLine())==0){
                        System.out.println("请输入登录密码：");
                        if(M.managePassword.compareTo(sc.nextLine())==0){
                            if_log=1;
                            while(if_log==1) {
                                System.out.println("管理员菜单");
                                System.out.println("1.展示账户   2.删除   3.修改");
                                System.out.println("4.操作日志   5.退出");
                                System.out.println("请选择：");
                                switch (sc.nextInt()) {
                                    case 1:
                                        tree.showAccount();
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        tree.inOrder();
                                        System.out.println("请选择要修改密码的账号：");
                                        Account temp = tree.search(sc.nextLine());
                                        if(temp==null){
                                            System.out.println("暂无该账号");
                                        }else{

                                        }
                                        break;
                                    case 4:
                                        break;
                                    case 5:
                                        if_log=0;
                                        break;
                                }
                            }
                        }else{
                            System.out.println("密码错误");
                        }
                    }else{
                        System.out.println("管理员姓名错误");
                    }
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