# Problem 1
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1,1);
        int c = calc.divide.apply(a, b);
/*  Ошибка заключается в отсутствии обработки исключения деления на 0.
    Способы решения:
    1. Запретить деление на 0.
    2. Использовать try-catch.

*/
        calc.println.accept(c);
    }
}

class Calculator {
    static Supplier<Calculator> instance = Calculator::new;

    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> divide = (x, y) -> {
        int a = Integer.MAX_VALUE;
        try {
            a = x / y;
        } catch (ArithmeticException e) {
            System.out.println("Попытка деления на 0, поэтому выводится максимальное целое значение, как замена бесконечности:");
        }
        return a;
    };

    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;

    Predicate<Integer> isPositive = x -> x > 0;

    Consumer<Integer> println = System.out::println;
}

# Problem 2
public class Main {
    public static void main(String[] args) {
        Worker.OnTaskDoneListener listener = System.out::println;
        Worker worker = new Worker(listener);
        worker.start();
    }
}

public class Worker {
    @FunctionalInterface
    public interface OnTaskDoneListener {
        void onDone(String result);
    }

    private OnTaskDoneListener callback;

    public Worker(OnTaskDoneListener callback) {
        this.callback = callback;
    }

    public void start() {
        for (int i = 1; i <= 10; i++) {
            callback.onDone("Task " + i + " is done");
        }
    }
}
