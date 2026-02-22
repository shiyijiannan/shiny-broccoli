import java.io.*;
import java.util.*;

public class SearchTree implements Serializable {
    private TreeNode root;
    private static final String FILE = "bankdata.dat";

    public SearchTree(){
        root = null;
    }//初始化

    //插入,公共接口
    public void insert(Account account){
        root = insertRec(root, account);
    }

    private TreeNode insertRec(TreeNode root,Account account){
            if (root == null) {
                System.out.println("开户成功");
                return new TreeNode(account);
            }
            if (account.id.compareTo(root.account.id) < 0) {
                root.left = insertRec(root.left, account);
            } else if (account.id.compareTo(root.account.id) > 0) {
                root.right = insertRec(root.right, account);
            } else {
                System.out.println("账号已存在！");
            }
        return root;
    }

    //查找
    public Account search(String id){
        return searchRec(root,id);
    }

    private Account searchRec(TreeNode root, String id){
        if(root==null){
            System.out.println("暂无信息");
            return null;
        }
        if(id.equals(root.account.id)){
            return root.account;
        }
        return id.compareTo(root.account.id) < 0
                ? searchRec(root.left, id)
                : searchRec(root.right, id);
    }

    //保存
    public void saveData(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))){
            oos.writeObject(this);
            System.out.println("保存成功");
        }catch (Exception e){
            System.out.println("保存失败"+e.getMessage());
        }
    }

    //读取
    public static SearchTree loadFromFile() {
        File file = new File(FILE);
        if (!file.exists()) return new SearchTree();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (SearchTree) ois.readObject();
        } catch (Exception e) {
            return new SearchTree();
        }
    }

    public void inOrder() {
        System.out.println("====所有账户信息====");
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode root) {
        if (root != null) {
            inOrderRec(root.left);
            root.account.show();
            System.out.println("-----------------");
            inOrderRec(root.right);
        }
    }

    private void lineAccount(TreeNode root, List<Account> accountList){//将二叉树拷贝到列表
        if(root != null){
            lineAccount(root.left, accountList);
            accountList.add(root.account);
            lineAccount(root.right,accountList);
        }
    }

    public void showAccount() {
        List<Account> accountList = new ArrayList<>();
        lineAccount(root, accountList);

        if (accountList.isEmpty()) {
            System.out.println("暂无账户信息！");
            return;
        }

        // 按余额降序排序
        Collections.sort(accountList, new Comparator<Account>() {
            @Override
            public int compare(Account a1, Account a2) {
                // 降序排列：a2的余额 - a1的余额
                return Double.compare(a2.money, a1.money);
            }
        });

        // 展示排序后的账户
        System.out.println("====所有账户信息（按余额从高到低）====");
        for (int i = 0; i < accountList.size(); i++) {
            System.out.println("排名" + (i + 1) + "：");
            accountList.get(i).show();
            System.out.println("-----------------");
        }
    }
}
