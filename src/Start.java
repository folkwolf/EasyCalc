import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        System.out.println("EasyCalc 1.0. \nВведите выражение:");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        try {
            if (input.matches("([1-9]?|10)(\\+|-|\\*|/)([1-9]?|10)")) {
                Calc inCalc = new Calc (input);
                if (inCalc.check(false)) {
                    System.out.println("Ответ: "+inCalc.resultat());
                }
                else {
                    throw new Exception("Выражение недопустимо.");
                }
            }
            else if (input.matches("[IVX]+(\\+|-|\\*|/)[IVX]+")) {
                Rim convert = new Rim(input);
                Calc inCalc = new Calc (convert.resultat[0],convert.resultat[1],convert.znak);
                System.out.println("В арабском виде: "+convert.resultat[0]+convert.znak+convert.resultat[1]);
                if (inCalc.check(true)) {
                  Rim convert2 = new Rim (inCalc.resultat());
                  System.out.print("Ответ: ");
                  convert2.getResultat();
                }
                else {
                    throw new Exception("Выражение недопустимо.");
                }



            }
            else throw new Exception("Выражение недопустимо.");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class Calc {
    int a;
    int b;
    char c;
    int res;
    Calc(String input) {
        String[] nums = input.split("(\\+|-|\\*|/)");
        this.c = input.charAt(nums[0].length());
        this.a = Integer.parseInt(nums[0]);
        this.b = Integer.parseInt(nums[1]);
    }

    Calc(int a,int b,char c) {
        this.a=a;
        this.b=b;
        this.c=c;
    }

    public boolean check(boolean rim) {
        if (a > 10 || b > 10) {
           return false;
        }
        else if (a < 0 || b < 0) {
            return false;
        }
        else if (b==0 && c=='/') {
            return false;
        }
        else if (rim && a<=b && c=='-') {
            return false;
        }
        else {
            return true;
        }
    }

    public int resultat() {
        switch (c) {
            case '+':   res= a+b;
                        break;
            case '-':   res= a-b;
                        break;
            case '*':   res= a*b;
                        break;
            case '/':   res= a/b;
                        break;
        }
        return res;
    }
}

class Rim {
    char [] rimb = {'I','V','X','L','C'};
    int [] rimn = {1,5,10,50,100};
    int [] resultat = {0,0};
    char znak;
    String nums;
    Rim (String input)
    {
        String [] rimnum = input.split("(\\+|-|\\*|/)");
        for (int b=0;b<2;b++) {
           char [] currim = rimnum[b].toCharArray();
           int prev=0;
            for (int i=0; i< currim.length;i++) {
                int s=0;
                while (currim[(currim.length)-i-1]!=rimb[s]) {
                    s++;
                }
                if (prev<rimn[s]) {this.resultat[b]+=rimn[s]; prev=rimn[s]; }
                else if (prev==0) {this.resultat[b]=rimn[s]; prev=rimn[s]; continue; }
                else if (prev==rimn[s])  {this.resultat[b]+=rimn[s]; prev=rimn[s]; }
               else if (prev>rimn[s]) {this.resultat[b]-=rimn[s]; prev=rimn[s];  }


                }

            }
        znak = input.charAt(rimnum[0].length());

        }

        Rim (int input) {
            nums = Integer.toString(input);
        }

    public void getResultat () {
        for (int d=0; d<nums.length();d++) {
            char one = rimb[(nums.length()-1-d)*2];
            char five = rimb[(nums.length()*(2-d))-1];
            char nextone = rimb[(nums.length()-1-d)*2+2];
            switch (nums.charAt(d)) {
                case '1': this.print(one); break;
                case '2': this.print(one);
                    this.print(one);
                    break;
                case '3': this.print(one); this.print(one); this.print(one);
                    break;
                case '4': this.print(one); this.print(five); break;
                case '5': this.print(five); break;
                case '6': this.print(five); this.print(one); break;
                case '7': this.print(five); this.print(one); this.print(one); break;
                case '8': this.print(five); this.print(one); this.print(one); this.print(one); break;
                case '9': this.print(one); this.print(nextone);
                case '0': break;

            }
        }

    }
    void print(char a) {
        System.out.print(a);
    }

    }
