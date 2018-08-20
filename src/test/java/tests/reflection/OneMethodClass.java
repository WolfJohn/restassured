package tests.reflection;

@PrintAnnotation(value = "let's rock")
public class OneMethodClass {

    public void print(String whatToPrint){
        System.out.println(whatToPrint);
    }

    public static void staticPrint(){
        System.out.println("Static print");
    }
}
