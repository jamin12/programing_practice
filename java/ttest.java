public class ttest {
    public static void main(String[] args) {
        long sum = 0;
        for (int i = 1; i <= 500000990; i++) {
            sum += i;
        }
        System.out.println("The sum is: " + sum);
    }
}