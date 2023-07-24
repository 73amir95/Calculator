package model;

public class Operations {

    public Operations() {

    }

    public double sin(double num) {     // calculating sine
        return Math.sin(num);
    }
    public double cos(double num) {     // calculating cosine
        return Math.cos(num);
    }
    public double tan(double num) {     // calculating tangent
        return Math.tan(num);
    }
    public double square(double num) {  // calculating square of a number
        return Math.pow(num, 2);
    }
    public double sqrt(double num) {    // calculating square root of a number
        if (num < 0) {
            throw new IllegalArgumentException("Cannot take negative value for square root");
        } else {
            return Math.sqrt(num);
        }
    }
    public long factorial(int num) {    // calculating the factorial of an integer
        if (num < 0) {
            throw new IllegalArgumentException("Cannot take negative value for factorial");
        } else if (num == 0) {
            return 1;
        } else {
            long temp = 1;
            for (int i = 1; i <= num; i++) {
                temp = temp * i;
            }
            return temp;
        }
    }
    public double ln(double num) {      // calculating the natural logarithm of a number
        if (num <= 0) {
            throw new IllegalArgumentException("Cannot accept 0 or negative value for ln");
        } else {
            return Math.log(num);
        }
    }
    public double log(double arg, double base) {    // calculating logarithm of a number with the capability of choosing different bases
        if (base <= 0) {
            throw new IllegalArgumentException("base of log cannot be zero or negative");
        } else {
            return Math.log10(arg) / Math.log10(base);
        }
    }

}
