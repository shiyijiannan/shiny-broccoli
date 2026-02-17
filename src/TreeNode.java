import java.io.Serializable;

public class TreeNode implements Serializable {
    Account account;
    TreeNode left,right;

    public TreeNode(Account account){
        this.account = account;
        left = right = null;
    }//初始化
}
