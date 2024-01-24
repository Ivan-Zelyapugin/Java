import java.util.Scanner;

public class Main{
    public static void main(String[] args)
    {
        System.out.println("Лабораторная работа 1 \nВыполнили студенты группы 22ВП2 Зеляпугин и Сафронов \nЗадание найти" +
                " в массиве чисел палиндромы и вывести их");

        System.out.println("Если необходимо закончить напиши q, иначе любой символ");
        Scanner start = new Scanner(System.in);
        String st = start.next();
        while(true){
            if(!st.equals("q")){
                System.out.println("Введи кол-во чисел");
                Scanner inLen = new Scanner(System.in);
                String len = inLen.next();
                int ilen=0;
                if(len.matches("^\\d+$")) {
                    ilen = Integer.parseInt(len);
                    System.out.println("Вводи числа");
                    Scanner in = new Scanner(System.in);

                    int[] nums = new int[ilen];

                    boolean fl=true;
                    for (int i = 0; i < nums.length; i++) {
                        String num = in.next();

                        if (num.matches("^\\d+$"))
                            nums[i] = Integer.parseInt(num);
                        else {
                            System.out.println("Вводить необходимо только положительные числа!ДО 10 знаков ");
                            fl=false;
                            break;
                        }
                    }
                    if(fl) {
                        System.out.println("Числа-полиндромы:");
                        for (int i = 0; i < nums.length; i++) {
                            String num = Integer.toString(nums[i]);
                            boolean flag = true;
                            for (int j = 0; j < (num.length()) / 2; j++) {
                                if (num.charAt(j) != num.charAt(num.length() - 1 - j)) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                System.out.println(num);
                        }
                        System.out.println("Если необходимо закончить напиши q, иначе любой символ для продолжения");
                        start = new Scanner(System.in);
                        st = start.next();
                    }
                }
                else
                    System.out.println("Вводить необходимо только положительные числа! НЕ БОЛЬШЕ 10 ЗНАКОВ!!");
            }
            else
                break;
        }

    }

}