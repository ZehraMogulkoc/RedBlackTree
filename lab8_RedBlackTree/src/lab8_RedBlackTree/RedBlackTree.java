package lab8_RedBlackTree;

import java.util.Scanner;
import java.util.Stack;


class Node

{    
	Node left, right;

    int key;

    int color;
    
    public Node(int key)
    {
    	this( key, null, null );

    } 
    public Node(int Key, Node lt, Node rt)
    {
             left = lt;

             right = rt;

             key = Key;

             color = 1;

         }    

     }
class RBT

{

    private Node current;

    private Node parent;

    private Node grand;

    private Node great;

    private Node header;    

    private static Node nullNode;


    static 

    {

        nullNode = new Node(0);

        nullNode.left = nullNode;

        nullNode.right = nullNode;

    }


    static final int BLACK = 1;    

    static final int RED   = 0;




    public RBT(int data)

    {

        header = new Node(data);

        header.left = nullNode;

        header.right = nullNode;

    }
      public boolean isEmpty() {
    	   
    	  if(header==null) {
    		  return true;
    		  
    	  }
    	  return header.right == nullNode;
      }


    public void clearTree() {
    	//header=null;
    	//header.left=null;
    	header.right=null;
    }


    public void insert(int item )

    {

        current = parent = grand = header;

        nullNode.key = item;

        while (current.key != item)

        {            

            great = grand; 

            grand = parent; 

            parent = current;

            current = item < current.key ? current.left : current.right;
 

            if (current.left.color == RED && current.right.color == RED)

                fixReorient( item );

        }


        if (current != nullNode)

            return;

        current = new Node(item, nullNode, nullNode);


        if (item < parent.key)

            parent.left = current;

        else

            parent.right = current;        

        fixReorient( item );

    }

    private void fixReorient(int item)

    {
        current.color = RED;

        current.left.color = BLACK;

        current.right.color = BLACK;



        if (parent.color == RED)   

        {

            grand.color = RED;

            if (item < grand.key != item < parent.key)

                parent = rotate( item, grand );

            current = rotate(item, great );

            current.color = BLACK;

        }


        header.right.color = BLACK; 

    }      

    private Node rotate(int item, Node parent)

    {

        if(item < parent.key)

            return parent.left = item < parent.left.key ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;  

        else

            return parent.right = item < parent.right.key ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);  

    }


    private Node rotateWithLeftChild(Node n2)

    {

        Node n1 = n2.left;

        n2.left = n1.right;

        n1.right = n2;

        return n1;

    }

    private Node rotateWithRightChild(Node n1)

    {

        Node n2 = n1.right;

        n1.right = n2.left;

        n2.left = n1;

        return n2;

    }

    public int countNodes()

    {

        return countNodes(header.right);

    }

    private int countNodes(Node r)

    {

        if (r == nullNode)

            return 0;

        else

        {

            int l = 1;

            l += countNodes(r.left);

            l += countNodes(r.right);

            return l;

        }

    }

  

   public boolean search(int val) {
	   return search(header.right, val);
   }
   private boolean search(Node r, int number)

   {

       boolean isExist = false;

       while ((r != nullNode) && !isExist)

       {  int rval = r.key;

           if (number < rval)

               r = r.left;

           else if (number > rval)

               r = r.right;

           else{

               isExist = true;

               break;

           }

           isExist = search(r, number);

       }

       return isExist;

   }




    public void inorder()

    {

        inorder(header.right);

    }

    private void inorder(Node node)

    {

        if (node != nullNode)

        {

            inorder(node.left);

            char color = 'B';

            if (node.color == 0)

                color = 'R';

            System.out.print(node.key +""+color+" ");

            inorder(node.right);

        }

    }


  public void preorder(Node node) {
	  
	  if (node != nullNode){

          char color = 'B';

          if (node.color == 0)

              color = 'R';

          System.out.print(node.key +""+color+" ");

          preorder(node.left);             

          preorder(node.right);
}
  }
  public void preorder() {

      preorder(header.right); }

 


  public void  postorder(Node node) {

      if (node != nullNode) {
  postorder(node.left);             
postorder(node.right);

          char color = 'B';

          if (node.color == 0)

              color = 'R';

          System.out.print(node.key +""+color+" ");
      }
  }
  public void postorder()

  {

      postorder(header.right);

  }

}

public class RedBlackTree {
	public static void main(String[] args)

    {            

       Scanner scan = new Scanner(System.in);


       RBT rbt = new RBT(Integer.MIN_VALUE);      

       char ch;


       do    

       {

           System.out.println("\nPlease choose a Red-Black Tree operation\n");

           System.out.println("1. insert ");

           System.out.println("2. search");

           System.out.println("3. count nodes");

           System.out.println("4. check empty");

           System.out.println("5. clear tree");



           int choice = scan.nextInt();            

           switch (choice)

           {

           case 1 : 

               System.out.println("Enter an integer element to insert");

               rbt.insert( scan.nextInt() );                     

               break;                          

           case 2 : 

            System.out.println("Enter an integer element to search");

               System.out.println("Search result : "+ rbt.search( scan.nextInt() ));

               break;                                          

           case 3 : 

              System.out.println("Number of nodes = "+ rbt.countNodes());

               break;     

           case 4 : 

             System.out.println("Empty status = "+ rbt.isEmpty());

               break;     

           case 5 : 

              System.out.println("\nClear!");

               rbt.clearTree();

               break;         

           default : 

               System.out.println("Incorrect entry \n ");

               break;    

           }

     

           System.out.print("\nPost order : ");

           
        rbt.postorder();

           System.out.print("\nPre order : ");

        rbt.preorder();

           System.out.print("\nIn order : ");

           rbt.inorder(); 



           System.out.println("\nProceed (y or n)? \n");

           ch = scan.next().charAt(0);                        

       } while (ch == 'Y'|| ch == 'y');               

    }
}