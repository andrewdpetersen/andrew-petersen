package utils;

public class MyArrayList<E> implements MyArrayListInterface<E> {
    private Object[] arrayList;

    public Object[] getArrayList() {
        return arrayList;
    }

    public void setArrayList(Object[] arrayList) {
        this.arrayList = arrayList;
    }

    public MyArrayList() {
        this.arrayList = new Object[10];
    }


    @Override
    public int size() {
        return arrayList.length;
    }

    @Override
    public void add(E ele) {
        Object[] arr = getArrayList();
        Object[] newArray = new Object[(arr.length)+2];
        int i = 0;
        while(i<arr.length-1){
            newArray[i]=arr[i];
            i++;
        }
        newArray[i+1]=ele;
        setArrayList(newArray);
    }

    @Override
    public void add (E ele,int index){
//There are different scenarios here if the index is greater or less
//than the length of the original array. Look at the add method and extend from there.
        Object[] arrayList = getArrayList();
        if(index>=arrayList.length){
            Object[] newList = new Object[index+1];
            for(int i=0;i< arrayList.length-1;i++){
                newList[i] = arrayList[i];
            }
            newList[index] = ele;
        }
        else{
            Object[] newList = new Object[arrayList.length+2];
            for(int i=0;i<index;i++){
                newList[i]=arrayList[i];
            }
            newList[index] = ele;
            for(int i=index;i< newList.length-2;i++){
                newList[i+1]=arrayList[i];
            }
        }
    }

    //put in something to check if index given is out of bounds
    @Override
    public E get (int index){
        Object[] arr = getArrayList();
        return (E) arr[index];
    }

    //put in something to check if index given is out of bounds
    @Override
    public void remove (int index){
        Object[] arr = getArrayList();
        Object[] newArray = new Object[arrayList.length];
        int i = 0;
        while(i<index){
            newArray[i]=arr[i];
            i++;
        }
        i+=2;
        while(i>index){
            newArray[i-1]=arr[i];
            i++;
        }
    }

    @Override
    public void clear () {
        Object[] arr = getArrayList();
        for(int i=0;i<arr.length-1;i++){
            remove(i);
        }
    }

    @Override
    public int find (E ele){
        Object[] arr = getArrayList();
        int i =0;
        while(i<arr.length-1){
            if(ele.equals(arr[i])){
                return i;
            }else{
                i++;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E ele){
        if(find(ele)<0){
            return false;
        }else{
            return true;
        }
    }
//        Object[] arr = getArrayList();
//        int i =0;
//        while(i<arr.length){
//            if(arr[i].equals(ele)){
//                return true;
//            }else{
//                i++;
//            }
//        }
//        return false;
//    }
}