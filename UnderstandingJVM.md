## Код для исследования
```java

public class JvmComprehension {

    public static void main(String[] args) {
        int i = 1;                      // 1
        Object o = new Object();        // 2
        Integer ii = 2;                 // 3
        printAll(o, i, ii);             // 4
        System.out.println("finished"); // 7
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        System.out.println(o.toString() + i + ii);  // 6
    }
}
```
## Описание работы
1. Загружается в Metaspace класс JvmComprehension с метаданными.
2. В Stack выделяется память для метода main.
3. В эту память (Stack) вносится int i = 1.
4. В Heap создается объект Object(), данные об этом классе вносятся в Metaspace.
5. В Stack вносится ссылка "o" на Object().
6. Класс Integer помещается в Metaspace.
7. В области памяти Stack-main помещается переменная Integer ii = 2.
8. При первом вызове printAll в Stack выделяется память для данного метода.
9. В нее передается Integer uselessVar = 700.
10. В Stack выделяется память для метода System.out.println.
11. В нее передается результат вычислений o.toString() + i + ii.
12. А вот здесь уже сложно сказать, значение "finished" будет передаваться в память существующего метода System.out.println, или, поскольку его вызывает другой метод (не printAll, а main), будет выделена новая область памяти в Stack.
13. По окончании работы программы все связи перестанут быть актуальными, и сборщик мусора GC почистит всю память.
14. На этом JVM расслабится немного.
