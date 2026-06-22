package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /*
        Filtrowanie listy za pomocą Predicate
        Masz listę liczb całkowitych: List<Integer> numbers = Arrays.asList(1, 2, 3, 10, 15, 20);
        Napisz metodę, która przyjmuje listę i Predicate<Integer> i zwraca nową listę zawierającą tylko te elementy, które spełniają warunek predykatu.

        Utwórz lambdę sprawdzającą, czy liczba jest parzysta i wywołaj metodę, aby odfiltrować tylko parzyste liczby.
        Wypisz wynik na konsolę.
        */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 10, 15, 20);
        Predicate<Integer> isEven = a -> (a & 1) == 0;
        System.out.println(filterNumbers(numbers, isEven));
        /*
        Zdefiniuj dwie funkcje Function<String, String>:
        Jedna usuwa białe spacje z początku i końca napisu (trim),
        Druga zamienia wszystkie litery na wielkie (toUpperCase).
        Użyj metod andThen lub compose aby połączyć te funkcje w jedną całość i
        zastosuj ją do ciągu znaków z niechcianymi spacjami i małymi literami (np. " hello world ").
         */
        Function<String, String> myTrim = String::trim;
        Function<String, String> toUpper = String::toUpperCase;
        String text = "  hello world ";
        Function<String, String> combined = myTrim.andThen(toUpper);
        Function<String, String> composed = toUpper.compose(myTrim);
        System.out.println(combined.apply(text));
        System.out.println(composed.apply(text));
        /*
        zadanie 8
        Filtrowanie, Mapowanie i Grupowanie

        Masz klasę Employee z polami name, age i department.
        Utwórz listę Employee.
        Odfiltrowuj pracowników, którzy mają więcej niż 25 lat.
        Zmapuj imiona (name) odfiltrowanych pracowników do listy.
        Posortuj tych imion alfabetycznie.
        Pogrupuj pracowników według działu (department) i wypisz wyniki.
         */
        List<Employee> employees = generateEmployees();
        employees.stream()
                .filter(e -> e.age() > 25)
                .sorted(Comparator.comparing(Employee::name))
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name, Collectors.toList())
                ))
                .forEach((dept, names) -> System.out.println(dept + ": " + names));

        /*
        zadanie 9
        Przetwarzanie tekstu i unikalne wartości

        Masz listę zdań:
        List<String> sentences = Arrays.asList("hello world", "java streams", "world of code");.
        Rozdziel wszystkie zdania na słowa (np. "hello world" → "hello", "world").
        Wyeliminuj duplikaty słów.
        Posortuj unikalne słowa alfabetycznie.
        Polącz je w jeden string, oddzielając słowa przecinkami (np. "code,hello,java,of,streams,world").
         */
        List<String> sentences = Arrays.asList("hello world", "java streams", "world of code");
        System.out.println(
                sentences.stream()
                        .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                        .distinct()
                        .sorted()
                        .collect(Collectors.joining(","))
        );

        /*
        Pogrupuj produkty na dwie kategorie: "Tanie" (cena < 50) i "Drogie" (cena ≥ 50).
        Dla każdej grupy zbuduj jeden napis: nazwy produktów wielkimi literami, połączone " | ", całość objęta nawiasami kwadratowymi.
        Oczekiwany wynik:
        Tanie  -> [MLEKO | CHLEB | DLUGOPIS]
        Drogie -> [TELEWIZOR | SLUCHAWKI | BIURKO]
         */
        record Product(String name, String category, double price) {
        }

        List<Product> products = List.of(
                new Product("Mleko", "Spozywcze", 4.50),
                new Product("Telewizor", "Elektronika", 2300),
                new Product("Chleb", "Spozywcze", 6.00),
                new Product("Sluchawki", "Elektronika", 199),
                new Product("Dlugopis", "Biuro", 3.20),
                new Product("Biurko", "Biuro", 650)
        );
        products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.price() < 50 ? "Tanie" : "Drogie",
                        Collectors.mapping(
                                product -> product.name.toUpperCase(),
                                Collectors.joining(" | ", "[", "]")
                        )
                ))
                .forEach((key, value) -> System.out.printf("%s -> %s%n", key, value));
    }

    public static List<Integer> filterNumbers(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private static List<Employee> generateEmployees() {
        return List.of(
                new Employee("Zoe", 31, Department.IT),
                new Employee("Adam", 22, Department.MARKETING),
                new Employee("Marta", 28, Department.ACCOUNTING),
                new Employee("Krzysztof", 24, Department.IT),
                new Employee("Olivia", 35, Department.MANAGEMENT),
                new Employee("Bartek", 21, Department.IT),
                new Employee("Natalia", 27, Department.MARKETING),
                new Employee("Piotr", 23, Department.ACCOUNTING),
                new Employee("Ewa", 29, Department.IT),
                new Employee("Tomasz", 41, Department.MANAGEMENT),
                new Employee("Julia", 20, Department.IT),
                new Employee("Sebastian", 33, Department.ACCOUNTING),
                new Employee("Anna", 26, Department.IT),
                new Employee("Michał", 19, Department.MARKETING),
                new Employee("Karolina", 30, Department.MANAGEMENT)
        );
    }
}