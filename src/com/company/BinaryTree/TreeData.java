package com.company.BinaryTree;

import java.util.ArrayList;

//Класс для подготовки данных из массива для заполнения дерева.
public class TreeData {
    private ArrayList<Integer> alist;

    public TreeData(ArrayList<Integer> inputArray) {
        this.alist = inputArray;
    }

    //Получаем корень дерева.
    public int getRoot() {
        return alist.indexOf(-1);
    }

    //Находим левый потомок. Если его нет возвращаем -2
    public int leftChild(int value) {
        int firstValue = alist.indexOf(value);
        int secondValue = alist.lastIndexOf(value);
        if (firstValue == -1 && secondValue == -1)
            return -2;
        if (firstValue < secondValue) {
            return firstValue;
        } else  {
            return secondValue;
        }
    }
    //Находим правый потомок. Если его нет возвращаем -2
    public int rightChild(int value) {
        int firstValue = alist.indexOf(value);
        int secondValue = alist.lastIndexOf(value);
        //Возращаем -2 если нет потомков
        if (firstValue == -1 && secondValue == -1)
            return -2;
        //Возвращаем -2 если всего один потомок, считая его левым.
        if (firstValue == secondValue)
            return -2;
        if (firstValue > secondValue) {
            return firstValue;
        } else  {
            return secondValue;
        }
    }
}
