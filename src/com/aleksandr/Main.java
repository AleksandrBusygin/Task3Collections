package com.aleksandr;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    //методы для средней и медианной длинны слова
    public static double arithmeticMean (
            int[] arrayForFindingTheArithmeticMean)  //метод для нахождения среднего арифметического, параметром является массив
    {
        int valueArithmeticMean = 0;
        for (int i = 0; i < arrayForFindingTheArithmeticMean.length; i++)
        {
            valueArithmeticMean += arrayForFindingTheArithmeticMean[i];
        }

        return valueArithmeticMean / arrayForFindingTheArithmeticMean.length;
    }

    public static double getMedianOfNumber (
            int[] arrayForFindingTheMedianOfNumber)  //метод для нахождения медианы чисел, параметром является массив
    {
        Arrays.sort(arrayForFindingTheMedianOfNumber);
        if (arrayForFindingTheMedianOfNumber.length % 2 == 0)
        {
            return (int) ((arrayForFindingTheMedianOfNumber[arrayForFindingTheMedianOfNumber.length / 2] + arrayForFindingTheMedianOfNumber[arrayForFindingTheMedianOfNumber.length / 2 - 1]) / 2f);
        }
        //если количество элементов нечётное, то возвращаем средний элемент массива
        return arrayForFindingTheMedianOfNumber[arrayForFindingTheMedianOfNumber.length / 2];
    }

    public static void main(String[] args) throws IOException {
        String[] array = {"я", "он", "она", "торт", "видео", "футбол", "новости", "жидкость", "аудитория", "бакенбарды"};

        // записываем 10000 слов в рандомном порядке в фаил FirstFile
        File file = new File("FirstFile");
        PrintWriter pw = new PrintWriter(file);
        int a = 0;
        int b = 9;
        for (int i = 0; i < 10000; i++) {
            pw.print(array[(a + (int) (Math.random() * b))] + " ");
        }
        pw.close();

        //сортировка слов из файла по алфавиту
        Scanner sc = new Scanner(file);
        List<String> list = new ArrayList();
        while (sc.hasNext()) {
            String word = sc.useDelimiter("\\s+").next();
            list.add(word + " ");
        }
        Collections.sort(list);
        for (String str : list) {
            System.out.println(str);
        }

        //статистика количества слов + слово с максимальным количеством повторений
        Scanner scanner = new Scanner(file);
        Map<String, Integer> statistics = new HashMap<>();
        while (scanner.hasNext()) {
            String word = scanner.useDelimiter("\\s+").next();
            Integer count = statistics.get(word);
            if (count == null) {
                count = 0;
            }
            statistics.put(word, ++count);
        }
        String key = null;
        int value = 0;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (value > maxCount) maxCount = value;
            System.out.println(String.format("Слово '%s' встречается %d раз(а)", key, value));
        }

        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (value == maxCount)
                System.out.println(String.format("\nСлово '%s' встречается в файле масимальное количество раз - %d", key, value));
        }

        //находим среднюю и медианную длину слов
        String[] arrayOfStrings = null;
        arrayOfStrings = list.toArray(new String[list.size()]);
        int[] lengthOfString = new int[list.size()];

        for (int i = 0; i < arrayOfStrings.length; i++) {
            lengthOfString[i] = arrayOfStrings[i].length();
        }
        System.out.println("Средняя длина слов: " + arithmeticMean(lengthOfString));
        System.out.println("Средняя медианная длина слов: " + getMedianOfNumber(lengthOfString));

        //сортируем слова по длине и записываем в фаил SecondFile
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        File file2 = new File("SecondFile");
        PrintWriter pw2 = new PrintWriter(file2);
        for (String s : list){
            pw2.print(s + " ");
        }
        pw2.close();
    }
}
