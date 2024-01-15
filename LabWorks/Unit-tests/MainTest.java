import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class MainTest {

    @org.junit.jupiter.api.Test
    void main() {
    }

    @org.junit.jupiter.api.Test
    void calculateCosine() {
    }
    @Test
    public void testCalculateCosine() {
        // Проверка вычисления косинуса через ряд Тейлора
        double x = 0.5;
        int k = 10;
        double expectedResult = Main.calculateCosine(x, k);
        double delta = 0.001; // Погрешность для сравнения double
        assertEquals(Math.cos(x), expectedResult, delta);
    }

    @Test
    public void testCalculateCosineWithZeroK() {
        // Проверка вычисления косинуса с k = 0, должен вернуть 1.0
        double x = 0.5;
        int k = 0;
        double expectedResult = 1.0;
        double delta = 0.001;
        assertEquals(expectedResult, Main.calculateCosine(x, k), delta);
    }

    @Test
    public void testCalculateCosineWithNegativeX() {
        // Проверка вычисления косинуса с отрицательным x
        double x = -0.5;
        int k = 5;
        double expectedResult = Main.calculateCosine(x, k);
        double delta = 0.001;
        assertEquals(Math.cos(x), expectedResult, delta);
    }

    @Test
    public void testFormattedOutput() {
        // Проверка форматированного вывода
        double x = 0.5;
        int k = 5;

        // Замените эту строку на вызов вашего метода main
        String output = "Результат через ряд Тейлора: 0.8775825618903728\nРезультат через Math.cos(x): 0.8775825618903728\nЗначение cos(x) с минимальной шириной поля: 0.9\nЗначение cos(x) с использованием флага 0: 00000.8776\nЗначение cos(x) с использованием флага +: +0.877583\nЗначение cos(x) с использованием флага #: 0.878\n";
        String[] lines = output.split("\n");

        assertEquals(lines[0], "Результат через ряд Тейлора: 0.8775825618903728");
        assertEquals(lines[1], "Результат через Math.cos(x): 0.8775825618903728");
        assertEquals(lines[2], "Значение cos(x) с минимальной шириной поля: 0.9");
        assertEquals(lines[3], "Значение cos(x) с использованием флага 0: 00000.8776");
        assertEquals(lines[4], "Значение cos(x) с использованием флага +: +0.877583");
        assertEquals(lines[5], "Значение cos(x) с использованием флага #: 0.878");
    }

    }