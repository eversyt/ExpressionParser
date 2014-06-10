package parser;

public class TreeNode
{
    private ExpressionElement nodeValue;
    private TreeNode parent;
    private TreeNode leftChild;
    private TreeNode rightChild;
    private int priority;

    public TreeNode(ExpressionElement nodeValue, TreeNode parent, Integer priority)
    {
        if (priority == null)
        {
            priority = nodeValue.getPriority();
        }
        this.priority = priority;
        this.nodeValue = nodeValue;
        this.parent = parent;
    }

    public ExpressionElement getNodeValue()
    {
        return nodeValue;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public TreeNode getParent()
    {
        return parent;
    }

    public void setParent(TreeNode parent)
    {
        this.parent = parent;
    }

    public TreeNode getLeftChild()
    {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild)
    {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild()
    {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild)
    {
        this.rightChild = rightChild;
    }
}
