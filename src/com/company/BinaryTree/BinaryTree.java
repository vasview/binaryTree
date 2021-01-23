package com.company.BinaryTree;

import javax.swing.tree.TreeNode;
import java.util.*;

public class BinaryTree {

    Node root;//узел дерева
    private List<Node> listForPrint = new ArrayList<>();

     //метод добавления узла бинарного дерева.
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (current.right == null && current.left == null) {
            current.left =  addRecursive(current.left, value);
            return current;
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        }
        else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }
        else {
            //value already exists
            return current;
        }

        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
        listForPrint.add(root);
    }

    //добавление потомков узла. Перед этим находим корень по его значению.
    public void addChild(int rootNodeValue, int value, String position) {
        Node currentNode = searchNode(rootNodeValue);
        if (currentNode == null)
            return;
        //размещаем в
        if (position == "left") {
            currentNode.left = new Node(value);
            listForPrint.add(currentNode.left);
        }
        if (position == "right") {
            currentNode.right = new Node(value);
            listForPrint.add(currentNode.right);
        }
    }

    //BFS метод для поиска существующего узла в дереве.
    private Node searchFullTree(Node node, int value) {
        Queue<Node> currentLevelNodes = new LinkedList<>();
        Queue<Node> foundNodes = new LinkedList<>();

        currentLevelNodes.add(node);

        while(!currentLevelNodes.isEmpty()) {
            Node currentNode = currentLevelNodes.poll();
            if (value == currentNode.value) {
                foundNodes.add(currentNode);
                break;
            }
            if (currentNode.left != null)
                currentLevelNodes.add(currentNode.left);
            if (currentNode.right != null)
                currentLevelNodes.add(currentNode.right);
        }

        return foundNodes.poll();
    }

    public Node searchNode(int value) {
        return searchFullTree(root, value);
    }

    //нахождение максимальной глубины дерева
    private int findMaxDepth(Node tmpRoot) {
        if (tmpRoot == null){
            return 0;
        }
        int leftChildDepth = findMaxDepth(tmpRoot.left);
        int rightChildDepth = findMaxDepth(tmpRoot.right);

        return leftChildDepth > rightChildDepth
                ? leftChildDepth + 1
                : rightChildDepth + 1;
    }

    public int getDepth() {
        return findMaxDepth(root);
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(Node tmpRoot) {

        Queue<Node> currentLevel = new LinkedList<Node>();
        Queue<Node> nextLevel = new LinkedList<Node>();

        currentLevel.add(tmpRoot);

        while (!currentLevel.isEmpty()) {
            Iterator<Node> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                Node currentNode = iter.next();
                if (currentNode.left != null) {
                    nextLevel.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nextLevel.add(currentNode.right);
                }
                System.out.print(currentNode.value + " ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<Node>();
        }
    }

    public void displayTree()
    {//отображение элементов  дерева в виде иерархии
        Stack<Node> globalStack = new Stack();//желательно заменить Stack на другой класс
        globalStack.push(root);
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(isRowEmpty==false)
        {
            Stack<Node> localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false)
            {
                Node temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.value);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if(temp.left != null ||temp.right != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<emptyLeaf*2-2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            emptyLeaf /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
        System.out.println("****......................................................****");
    }
    private boolean containsNodeRecursive(Node current, int value) {
        //проверяет наличие узла в дереве
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private Node findNodeRecursive(Node current, int value) {
        if (value == current.value) {
            return current;
        }
        return value < current.value
                ? findNodeRecursive(current.left, value)
                : findNodeRecursive(current.right, value);

    }

    public Node findNode(int value) {
        return findNodeRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            //если деревое пустое завершить метод
            return null;
        }
        if (value == current.value) {
            //Node to delete found
            //... code to delete the node will go here
            // случай, когда узел является листовым узлом
            if (current.left == null && current.right == null) {
                return null;
            } else
                //случай, когда у узла есть один дочерний элемент
                if (current.right == null) {
                    return current.left;
                } else if (current.left == null) {
                    return current.right;
                }

                //случай, когда узел имеет двух дочерних элементов.
                else {
                    int smallestValue = findSmallestValue(current.left);
                    current.value = smallestValue;
                    current.left = deleteRecursive(current.left, smallestValue);
                    return current;
                }
        }
        else if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        else current.right = deleteRecursive(current.right, value);
        return current;
    }
    private int findSmallestValue(Node root) {
        //возвращает потомка с наименьшим значением
        return root.right == null ? root.value : findSmallestValue(root.right);
    }
    public void delete(int value) {
        //открытый метод, который запускает удаление из root
        root = deleteRecursive(root, value);
    }
}