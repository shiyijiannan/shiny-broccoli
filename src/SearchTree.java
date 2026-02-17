import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class SearchTree implements Serializable {
    private TreeNode root;
    private static final String FILE = "bankdata.dat";

    public SearchTree(){
        root = null;
    }//初始化

    //插入
    public void insert(Account account){
        root = insertRec(root, account);
    }

    private TreeNode insertRec(TreeNode root,Account account){
        if (root == null) {
            return new TreeNode(account);
        }
        if (account.id.compareTo(root.account.id) < 0) {
            root.left = insertRec(root.left, account);
        }
        else if (account.id.compareTo(root.account.id) > 0) {
            root.right = insertRec(root.right, account);
        }
        else {
            System.out.println("账号已存在！");
        }
        return root;
    }
    //读取文件
    public static SearchTree loadFromFile() {
        File file = new File(FILE);
        if (!file.exists()) {
            return new SearchTree();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (SearchTree) ois.readObject();
        } catch (Exception e) {
            return new SearchTree();
        }
    }

    //查找
    public Account search(String id){
        return searchRec(root,id);
    }

    private Account searchRec(TreeNode tree, String id){
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
}
