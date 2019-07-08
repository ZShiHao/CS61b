public class ArrayDeque<type>{
    private type[] item;
    private int nextFirst;
    private int nextLast;
    private int size;

    public  ArrayDeque(){
        item=(type []) new Object[8];
        nextFirst=4;
        nextLast=5;
        size=0;
    }

    public ArrayDeque(ArrayDeque other){
        item=(type[]) new Object[other.item.length];
        System.arraycopy(other.item,0,item,0,item.length);
        nextFirst=other.nextFirst;
        nextLast=other.nextLast;
        size=other.size;
    }

    private void resize(int capacity){
        type[] tem=(type []) new Object[capacity];
        int first=nextFirst+1;
        int last=nextLast-1;
        System.arraycopy(item,first,tem,0,size-first);
        System.arraycopy(item,0,tem,size-first,last+1);
        nextFirst=tem.length-1;
        nextLast=size;
        item=tem;
    }

    private int indexFirst(){
        int first;
        if(nextFirst==item.length-1){
            first=0;
        }else{
            first=nextFirst+1;
        }
        return first;
    }

    private int indexLast(){
        int last;
        if(nextLast==0){
            last=item.length-1;
        }else{
            last=nextLast-1;
        }
        return last;
    }

    public void addFirst(type x){
        if(size==item.length){
            resize(2*size);
        }
        item[nextFirst]=x;
        size++;
        if(nextFirst==0){
            nextFirst=item.length-1;
        }else{
            nextFirst--;
        }
    }

    public  void addLast(type x){
        if(size== item.length){
            resize(2*size);
        }
        size++;
        item[nextLast]=x;
        if(nextLast==item.length-1){
            nextLast=0;
        }else{
            nextLast++;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if(size==0) return true;
        return false;
    }

    public void printDeque(){
        int i=0;
        int first=indexFirst();
        int index;
        index=first;
        while (i!=size){
            System.out.print(item[index]);
            System.out.print(" ");
            if(index==item.length-1){
                index=0;
            }else{
                index++;
            }
            i++;
        }
        System.out.println();
    }

    public type removeFirst(){
        int first=indexFirst();
        type x=item[first];
        item[first]=null;
        nextFirst=first;
        size--;
        return x;
    }

    public type removeLast(){
        int last=indexLast();
        type x=item[last];
        item[last]=null;
        nextLast=last;
        size--;
        return x;
    }

    public type get(int index){
        int first=indexFirst();
        if(first+index<=item.length-1){
            return item[first+index];
        }else{
            return item[index-item.length+first];
        }
    }

}