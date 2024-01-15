import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Formatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите x (вещественное число): ");
        double x = scanner.nextDouble();

        System.out.print("Введите k (натуральное число): ");
        int k = scanner.nextInt();

        BigDecimal bdX = BigDecimal.valueOf(x);
        BigDecimal result = calculateCosine(bdX, k);

        // Сравнение с результатом, вычисленным через стандартную функцию Math.cos(x)
        BigDecimal standardResult = BigDecimal.valueOf(Math.cos(x));

        System.out.println("Результат через ряд Тейлора: " + result);
        System.out.println("Результат через Math.cos(x): " + standardResult);

        // Форматированный вывод результатов
        Formatter formatter = new Formatter();
        //formatter.format("Результат с точностью %d знаков после запятой: %.6f\n", k + 1, result);
        formatter.format("Значение cos(x) с минимальной шириной поля: %.1f\n", result);
        formatter.format("Значение cos(x) с использованием флага 0: %010.4f\n", result);
        formatter.format("Значение cos(x) с использованием флага +: %+f\n", result);
        formatter.format("Значение cos(x) с использованием флага #: %.3f\n", result);
        System.out.println(formatter);
    }

    public static BigDecimal calculateCosine(BigDecimal x, int k) {
        BigDecimal result = BigDecimal.ONE;
        BigDecimal term = BigDecimal.ONE;
        BigDecimal xSquared = x.multiply(x);
        BigDecimal denominator = BigDecimal.ONE;
        int sign = -1;

        for (int n = 2; n <= 2 * k; n += 2) {
            term = term.multiply(xSquared).divide(BigDecimal.valueOf(n * (n - 1)), k + 2, BigDecimal.ROUND_HALF_EVEN);
            result = result.add(term.multiply(BigDecimal.valueOf(sign)));
            sign *= -1;
        }

        return result;
    }
}
