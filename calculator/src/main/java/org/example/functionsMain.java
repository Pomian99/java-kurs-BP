package org.example;

public class functionsMain {
    public static void main(String[] args) {
        System.out.println(Operation.calculateByOperator(1, 2, '+'));
        System.out.println(Operation.calculateByOperator(1, 2, '-'));
        System.out.println(Operation.calculateByOperator(1, 2, '*'));
        System.out.println(Operation.calculateByOperator(1, 2, '/'));

        System.out.println(functionsMain.CalculateByOperation(10, 5, Operation.fromOperator('/').getOperation()));
    }

    public static Integer CalculateByOperation(Integer a, Integer b, MathOperation operation) {
        return operation.calculate(a, b);
    }
}
