import static java.lang.Math.*;

class Main {
    public static void main(String[] args) {
        int[] g = new int[7];
        for (int i = 0; i < 7; i++) g[i] = 6 + 2 * i;

        double[] x = new double[19];
        for (int i = 0; i < 19; i++) x[i] = -12 + random() * (11 + 12);

        double[][] b = new double[7][19];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 19; j++) {
                switch (g[i]) {
                    case (12):
                        double num = (pow(tan(x[j]), 2) + 1) / (2 * PI);
                        double exponent = pow(atan((x[j] - 0.5) / 23) / 2, exp(x[j]));
                        b[i][j] = log(pow(num, exponent));
                        break;
                    case (8):
                    case (16):
                    case (18):
                        b[i][j] = sin(cos(log(abs(x[j]))));
                        break;
                    default:
                        b[i][j] = cbrt(cbrt(tan(atan((x[j] - 0.5) / 23))));
                        break;
                }
            }
        }
        for (double[] line : b) {
            for (double element : line) System.out.printf("%5.2f ", element);
            System.out.println();
        }
    }
}