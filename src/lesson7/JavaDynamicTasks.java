package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        // T = O(n*m)
        // R = O(n*m)
        // n - length of the first string, m - length of the second string;
        boolean match = false;

        StringBuilder sequence = new StringBuilder();
        int lengthFirst = first.length();
        int lengthSecond = second.length();
        int[][] matrix = new int[lengthFirst+1][lengthSecond+1];
        char[] column = first.toCharArray();
        char[] row = second.toCharArray();

        for (int i = lengthFirst-1; i >= 0 ; i--) {
            for (int j = lengthSecond-1; j >= 0; j--) {
                if (column[i] == row[j]) {
                    match = true;
                    matrix[i][j] = matrix[i+1][j+1] + 1;
                }
                else matrix[i][j] = Math.max(matrix[i+1][j], matrix[i][j+1]);
            }
        }
        if (!match) return "";

        int i = 0;
        int j = 0;
        while (i < lengthFirst && j < lengthSecond){
            if (column[i] == row[j]){
                sequence.append(column[i]);
                i++;
                j++;
            }
            else if (matrix[i+1][j] > matrix[i][j+1]) i++;
            else j++;
        }

        return sequence.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        // T = O(n^2)
        // R = O(n)
        // n - size of the list;

        int listSize = list.size();
        if (listSize <= 1) return list;

        List<Integer> result = new ArrayList<>();
        int[] index = new int[listSize];
        int maxLen = 0;
        int maxInd = 0;

        for (int i = 0; i < listSize; i++) {
            index[i] = 1;
            int current = list.get(i);
            for (int j = 0; j < i; j++) {
                int prev = list.get(j);
                if (prev < current) {
                    index[i] = Math.max(index[i], index[j] + 1);
                    if (maxLen < index[i]) {
                        maxLen = index[i];
                        maxInd = i;
                    }
                }
            }
        }

        if (maxInd == 0) {
            result.add(list.get(0));
            return result;
        }
        for (int i = 0; i < maxLen ; i++) result.add(0);

        int prev = list.get(maxInd) + 1;
        for (int i = maxInd; i >= 0 ; i--) {
            int current = list.get(i);
            if (index[i] == maxLen){
               if (current < prev) {
                   result.set(maxLen - 1, current);
                   if (i != 0 && index[i - 1] < maxLen){
                       prev = current;
                       maxLen--;
                   }
               }
               else {
                   prev = result.get(index[i]);
                   maxLen--;
               }
            }
        }
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
