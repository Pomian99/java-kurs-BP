package org.example;

public enum Operation implements MathOperation {
    ADD('+', (a, b) -> a + b),
    SUBTRACT('-', (a, b) -> a - b),
    MULTIPLY('*', (a, b) -> a * b),
    DIVIDE('/', Operation::division);

    private final char operator;
    private final MathOperation operation;

    Operation(char operator, MathOperation operation) {
        this.operator = operator;
        this.operation = operation;
    }

    public MathOperation getOperation() {
        return operation;
    }

    public static Operation fromOperator(char operator) {
        for (Operation temp : Operation.values()) {
            if (temp.operator == operator) {
                return temp;
            }
        }
        return null;
    }

    @Override
    public Integer calculate(Integer a, Integer b) {
        return operation.calculate(a, b);
    }

    public static Integer calculateByOperator(Integer a, Integer b, char operator) {
        for (Operation temp : Operation.values()) {
            if (temp.operator == operator) {
                return temp.operation.calculate(a, b);
            }
        }
        return null;
    }

    private static Integer division(Integer a, Integer b) {
        if (b.equals(0)) {
            throw new ArithmeticException("Can't divide by 0");
        }
        return Integer.divideUnsigned(a, b);
    }
}
