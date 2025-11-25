package ua.opnu;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Consumer;

public class Main {
    public interface StudentConsumer {
        void accept(Student s);
    }
    public static Student[] filterStudents(Student[] students, Predicate<Student> condition) {
        return Arrays.stream(students)
                .filter(condition)
                .toArray(Student[]::new);
    }
    public static Student[] filterTwo(Student[] students,
                                      Predicate<Student> p1,
                                      Predicate<Student> p2) {
        return Arrays.stream(students)
                .filter(p1.and(p2))
                .toArray(Student[]::new);
    }
    public static void forEach(Student[] students, StudentConsumer action) {
        for (Student s : students) {
            action.accept(s);
        }
    }
    public static void processIf(int[] numbers,
                                 Predicate<Integer> cond,
                                 Consumer<Integer> action) {
        for (int n : numbers) {
            if (cond.test(n)) {
                action.accept(n);
            }
        }
    }
    public static String[] stringify(int[] numbers,
                                     Function<Integer, String> func) {
        return Arrays.stream(numbers)
                .mapToObj(func::apply)
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        System.out.println("Завдання 1");

        IntPredicate isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        };

        System.out.println(isPrime.test(2));
        System.out.println(isPrime.test(15));
        System.out.println(isPrime.test(17));

        System.out.println("\nЗавдання 2");

        Student[] students = {
                new Student("Віка", "Гудз", "УП-241", new int[]{90, 95, 100}),
                new Student("Микита", "Фірсов", "УП-241", new int[]{65, 80, 70}),
                new Student("Олексій", "Прокопчук", "УП-241", new int[]{40, 50, 58})
        };

        Predicate<Student> hasDebts = s -> {
            for (int m : s.getMarks()) if (m < 60) return true;
            return false;
        };

        Student[] debtors = filterStudents(students, hasDebts);

        System.out.println("Студенти з боргами:");
        for (Student s : debtors) {
            System.out.println(s.getName());
        }

        System.out.println("\nЗавдання 3");

        Predicate<Student> goodAverage = s -> {
            int sum = 0;
            for (int m : s.getMarks()) sum += m;
            return sum / s.getMarks().length >= 80;
        };
        Student[] passed = filterTwo(
                students,
                hasDebts.negate(),
                goodAverage
        );
        System.out.println("Студенти без боргів і з середнім >= 80:");
        for (Student s : passed) {
            System.out.println(s.getName());
        }

        System.out.println("\nЗавдання 4");

        StudentConsumer printFullName =
                s -> System.out.println(s.getLastName() + " " + s.getName());

        System.out.println("Повні імена студентів:");
        forEach(students, printFullName);

        System.out.println("\nЗавдання 5");

        int[] nums = {10, 15, 20, 25, 30, 35};

        Predicate<Integer> isMultipleOf10 = n -> n % 10 == 0;
        Consumer<Integer> printNum = n -> System.out.println("Число: " + n);

        processIf(nums, isMultipleOf10, printNum);

        System.out.println("\nЗавдання 6");

        int[] nums67 = {0,1,2,3,4,5,6,7,8,9};

        Function<Integer, Integer> pow2 = n -> (int) Math.pow(2, n);

        for (int n : nums67) {
            System.out.println("2^" + n + " = " + pow2.apply(n));
        }
        System.out.println("\nЗавдання 7");
        Function<Integer, String> numberToString = n -> switch (n) {
            case 0 -> "нуль";
            case 1 -> "один";
            case 2 -> "два";
            case 3 -> "три";
            case 4 -> "чотири";
            case 5 -> "п’ять";
            case 6 -> "шість";
            case 7 -> "сім";
            case 8 -> "вісім";
            case 9 -> "дев’ять";
            default -> "";
        };

        String[] strings = stringify(nums67, numberToString);

        System.out.println("Рядкове представлення:");
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
