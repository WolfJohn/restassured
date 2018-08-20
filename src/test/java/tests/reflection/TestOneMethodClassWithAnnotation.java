package tests.reflection;

import org.junit.Test;
import tests.generics.GenericClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestOneMethodClassWithAnnotation {

    @Test
    public void testGenerics(){
        GenericClass<String> str = new GenericClass<>("Value");
        System.out.println(str.getValue());
    }

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException {
        Class<?> clazz = Class.forName("tests.reflection.OneMethodClass");
        Method[] methods = OneMethodClass.class.getDeclaredMethods();

        OneMethodClass cl = (OneMethodClass) clazz.newInstance();

        PrintAnnotation annotation = cl.getClass().getAnnotation(PrintAnnotation.class);

        Method print = Arrays.asList(methods).stream().filter(m -> m.getName().equals("print")).findFirst().get();

        print.invoke(cl, "invoker");

        print.invoke(cl, annotation.value());

        Method staticPrint = cl.getClass().getDeclaredMethod("staticPrint");

        staticPrint.invoke(cl);

        List<?> list = Arrays.asList(methods);

        System.out.println(list);
    }
}
