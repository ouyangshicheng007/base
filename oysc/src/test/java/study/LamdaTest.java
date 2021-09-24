package study;

import org.assertj.core.util.Lists;

import java.io.File;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/8/27 17:16
 **/
public class LamdaTest {


    public static void main(String[] args) {



    }


    public static void test44() {

        BiFunction<String, String, String> biFunction = (x, y) -> "x: " + x + " y:" + y;

        System.out.println(biFunction.apply("3","5"));

    }



    public static void test33() {
//        List<Double> collect = Stream.iterate(1.0, p -> p * 2).peek(e -> System.out.println("Fetching " + e)).limit(20).collect(Collectors.toList());


        List<String> list = Lists.newArrayList("hello","worlds","You","is","I", "love");

        list.stream().sorted(Comparator.comparingInt(String::length).reversed()).limit(3).forEach(System.out::println);


        System.out.println(System.nanoTime());

    }


    public static void testGrep() {
        String test = "ext={\"proposal\":\"TDEJ21110190000000000055,TDFA21110190000000000050,TDFA21110190000000000050\",\"companyId\":\"CCIC\",\"sign\":\"6c85fc5ddf0cf7b9c7615ad1860ce5db\"}  orderAmount=0.01  paymentNo=01210005435492";

        String pre = "\"proposal\":";
        Matcher matcher = Pattern.compile(pre + "\".*?\"").matcher(test);
        if (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
            System.out.println(group.replaceAll(pre, ""));
        }
    }

    public static void test111() {


        List<List<String>> lists = Lists.newArrayList(Lists.newArrayList("H", "E", "L", "L", "E"), Lists.newArrayList("W", "O", "R", "L", "D"));


        List<String> collect = lists.stream().flatMap(Collection::stream).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    public static void test11() {

        List<String> list = Lists.newArrayList("An","Boss","Car");

        Stream<Character> stream1 = list.stream().map(x -> x.charAt(0));
        Stream<Character> stream2 = list.stream().map(x -> x.charAt(1));



        List<String> collect = Arrays.stream(new String[]{}).collect(Collectors.toList());


        List<Stream<Character>> collect3 = list.stream().map(data -> {
            List<Character> cs = Lists.newArrayList();
            for (Character c : data.toCharArray()) {
                cs.add(c);
            }
            return cs.stream();
        }).collect(Collectors.toList());
        collect3.forEach(System.out::println);
        System.out.println("====================");
/**
 *      {
 *          [a,n],
 *          [b,o,s,s],
 *          [c,a,r]
 *      }
 *
 *      {
 *          a,n,b,o,s,s,c,a,r
 *      }
**/

        List<char[]> collect1 = list.stream().map(String::toCharArray).distinct().collect(Collectors.toList());

        Stream<char[]> stream3 = list.stream().flatMap(data -> {
            for (Character c : data.toCharArray()) {

            }
            return Lists.list(data.toCharArray()).stream();
        });


        List<Character> collect2 = list.stream().flatMap(data -> {
            List<Character> cs = Lists.newArrayList();
            for (Character c : data.toCharArray()) {
                cs.add(c);
            }
            return cs.stream();
        }).collect(Collectors.toList());

        collect2.forEach(System.out::println);

    }

    public static void study2() {
        File file = new File("E:\\");
        File[] files = file.listFiles();
        for (File f: files) {
            if (f.isDirectory()) {
                System.out.println(f);
            }
        }
    }

    public static void study22() {
        File file = new File("E:\\");
        File[] files = file.listFiles(File::isFile);
//        assert files != null;
//        Arrays.stream(files).forEach(System.out::println);
    }

    public static void study3() {
        File parentFile = new File("E:\\");
        File[] files = parentFile.listFiles(f -> f.getName().endsWith(".txt"));
        assert files != null;
        Arrays.stream(files).forEach(System.out::println);
    }

    public static void test4() {
        File parentFile = new File("E:\\");
        File[] files = parentFile.listFiles(File::isFile);

//        Arrays.stream(files).sorted((x,y) -> x.getName())

    }

    public static void testCopy() {
        List<String> list = Collections.nCopies(5, "abc");
        list.forEach(System.out::println);
    }

    public static void testCallAble() throws Exception {
        Callable<Void> aa = () -> {
            System.out.println("haha");
            Thread.sleep(1000);
            return null;
        };

        aa.call();


        String[] strings = new String[]{};

        Arrays.sort(strings, String::compareTo);
        Arrays.sort(strings, (a,b)-> a.compareTo(b));

        List<String> list = Lists.newArrayList("A", "B", "C");

        String[] strings2 = list.toArray(new String[0]);


        String[] strings1 = Stream.of("A", "B").toArray(String[]::new);
    }


    public static void message(int count, String msg) {

        Runnable runnable = () -> {
          for (int i=0; i<count; i++) {
              System.out.println(msg);
          }
        };

    }
}
