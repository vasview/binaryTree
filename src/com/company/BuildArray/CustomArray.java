package com.company.BuildArray;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

//Класс для полученные данных от пользовтеля и их валицидации.
public class CustomArray {
    //получаем размер массива и валидируем значение размера
    public int getArraySize() {
        Scanner input = new Scanner(System.in);
        int size = 0;
        boolean isInputChecked = false;
        while (!isInputChecked) {
            System.out.print("Введите размер массива : ");
            size = input.nextInt();
            if (size >= 1 && size <= 100)
                isInputChecked = true;
        }
        return size;
    }

    //получаем массив данных от пользователя.
    public ArrayList<Integer> getArray(int size) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        Scanner input = new Scanner(System.in);
        boolean inputChecked = false;

        while (!inputChecked) {
            array.clear();
            System.out.print("Введите целые числа для построения массива : ");
            for (int i = 0; i < size; i++) {
                array.add(input.nextInt());
            }
            inputChecked = isCorrectInput(array);
            if (!inputChecked)
                System.out.println("Неверные данные.");
        }

        return array;
    }
    //метод для валидации введенных данных массива. Чтобы были числа и была цифра -1.
    private boolean isCorrectInput(ArrayList<Integer> array) {
        boolean correctDate = false;
        int count = 0;
        ListIterator<Integer> li = array.listIterator();
        while (li.hasNext()) {
            if (li.next().intValue() == -1)
                count ++;
        }
        if (count == 1)
            correctDate = true;

        return correctDate;
    }

    //метод для вывода на печать полученных данных
    public void printArray(ArrayList<Integer> array) {
        System.out.println("--------------------------");
        for(int i = 0; i < array.size(); i++) {
            System.out.print((int) array.get(i));
            System.out.print(' ');
        }
        System.out.println();
        System.out.println("--------------------------");
    }
}
