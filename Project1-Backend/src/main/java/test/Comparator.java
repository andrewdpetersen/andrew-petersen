package test;

public class Comparator {

    public static boolean compare(int a, int b){
        return a==b;
    }

    public static boolean compare(String a, String b){
        return a.equals(b);
    }

    public static boolean compare(int[] a, int[] b){
        boolean flag = false;
        if(a.length==b.length){
            flag = true;
            for(int i=0;i<a.length;i++){
                if(a[i] != b[i]){
                    flag = false;
                    break;
                }
            }
        }return flag;

    }
}
//        1) Override comparator class... inta, int b... String a, string b... int[] a, int [] b...
//        if(same length){
//        for(i<a.length){a[i]=b[i]}
//        print Same or Different...