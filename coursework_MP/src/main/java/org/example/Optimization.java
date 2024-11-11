package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Optimization {

    private static int[][] a;  // Матрица выпуска продукта для каждого способа реконструкции
    private static int[] c;    // Затраты на каждый способ реконструкции
    private static int[] b;    // Минимальные требования по каждому продукту
    private static int n, p, m;

    private static int[][] x;  // Матрица для хранения текущего состояния выбранных способов реконструкции
    private static int currentCost;
    private static int[][] bestX;

    public static void main(String[] args) {
        // Чтение данных из файла
        try {
            readDataFromFile("input.txt"); // Укажите путь к вашему файлу
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
            return;
        }

        x = new int[p][n];
        bestX = new int[p][n];
        currentCost = Integer.MAX_VALUE;

        branchAndBound(0, 0, new int[m]);

        System.out.println("Оптимальные затраты: " + currentCost);
        System.out.println("Решение:");
        printMatrix(bestX);
    }

    private static void readDataFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        // Чтение n, p, m
        n = scanner.nextInt();
        p = scanner.nextInt();
        m = scanner.nextInt();

        // Инициализация массивов c, b и a
        c = new int[n];
        b = new int[m];
        a = new int[m][n];

        // Чтение затрат c
        for (int i = 0; i < n; i++) {
            c[i] = scanner.nextInt();
        }

        // Чтение минимальных требований b
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        // Чтение матрицы выпуска a
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scanner.nextInt();
            }
        }

        scanner.close();
    }

    private static void branchAndBound(int enterprise, int currentCostSum, int[] currentProduction) {
        System.out.println("Предприятие " + (enterprise + 1) + ": текущие затраты = " + currentCostSum);
        System.out.println("Текущий выбор:");
        printMatrix(x);

        if (enterprise == p) {
            if (isValid(currentProduction)) {
                System.out.println("Все продукты удовлетворяют минимальным требованиям.");
                if (currentCostSum < currentCost) {
                    currentCost = currentCostSum;
                    for (int i = 0; i < p; i++) {
                        for (int j = 0; j < n; j++) {
                            bestX[i][j] = x[i][j];
                        }
                    }
                    System.out.println("Найдено лучшее решение с затратами " + currentCost);
                }
            }
            return;
        }

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {
                currentProduction[k] += a[k][j];
            }

            int newCost = currentCostSum + c[j];
            if (newCost >= currentCost) {
                for (int k = 0; k < m; k++) {
                    currentProduction[k] -= a[k][j];
                }
                System.out.println("Пропускаем реконструкцию " + (j + 1) + " для предприятия " + (enterprise + 1));
                continue;
            }

            x[enterprise][j] = 1;
            System.out.println("Предприятие " + (enterprise + 1) + " выбирает реконструкцию " + (j + 1));

            branchAndBound(enterprise + 1, newCost, currentProduction);

            x[enterprise][j] = 0;
            for (int k = 0; k < m; k++) {
                currentProduction[k] -= a[k][j];
            }
        }
    }

    private static boolean isValid(int[] currentProduction) {
        for (int i = 0; i < m; i++) {
            if (currentProduction[i] < b[i]) {
                return false;
            }
        }
        return true;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
