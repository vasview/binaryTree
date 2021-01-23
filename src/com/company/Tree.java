package com.company;

import com.company.BinaryTree.BinaryTree;
import com.company.BinaryTree.TreeData;
import com.company.BuildArray.CustomArray;
import java.util.ArrayList;
import java.util.List;

public class Tree {
    //вспомогательный массив для хранения уже введенных узлов из исходных данных.
    private static List<Integer> builtNodeList = new ArrayList<>();

    public static void main(String[] args) {
        //объявляем само дерево.
        BinaryTree bTree = new BinaryTree();

        //Создаем экземпляр класса, ответственного для полученния данных от пользователя.
        CustomArray arrayObj = new CustomArray();
        int size = arrayObj.getArraySize();

        //объявляем массив для хранения полученных данных от пользователя и инциализируем его.
        ArrayList<Integer> inputArray = new ArrayList<Integer>();
        inputArray = arrayObj.getArray(size);

        //выводим полученный массив
        System.out.println("Исходные данные: ");
        System.out.println("Размер массива: " + size);
        arrayObj.printArray(inputArray);

        //объяляем экземлпяр для подготовки данных к заполнение в узлы дерева
        TreeData tData = new TreeData(inputArray);

        //находим значение корня дерева
        int treeRoot = tData.getRoot();
        System.out.println("Значение корня = " + treeRoot);

        //Вставляем корень в дерево
        bTree.add(treeRoot);
        builtNodeList.add(treeRoot);

        //заполняем дерево.
        buildNodeRecursive(tData, bTree, treeRoot,treeRoot);

        //отображаем структуры дерева.
        bTree.displayTree();
        //Находим глубину дерева и выводим на печать.
        System.out.println("Глубина дерева " + bTree.getDepth());
    }

    //метод для заполнения узлов дерева из полученного массива.
    static void buildNodeRecursive(TreeData tData, BinaryTree bTree, int currentNode, int parent){
        int left = tData.leftChild(currentNode);
        int right = tData.rightChild(currentNode);

        //корень поддерева
        if (currentNode != -2) {
            if (!builtNodeList.contains(currentNode)) {
                bTree.addChild(parent, currentNode, "left");
                builtNodeList.add(currentNode);
            }
        }
        //есть левый потомок
        if (left != -2) {
            bTree.addChild(currentNode, left, "left");
            builtNodeList.add(left);
        }
        //правый потомок
        if (right != -2) {
            bTree.addChild(currentNode, right, "right");
            builtNodeList.add(right);
        }

        //вызываем функцию рекурсивно
        if (currentNode != -2 && left !=  right) {
            buildNodeRecursive(tData, bTree, left, currentNode);
            buildNodeRecursive(tData, bTree, right, currentNode);
        }
    }
}
