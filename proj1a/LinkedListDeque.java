import sun.jvm.hotspot.debugger.windbg.DLL;

public class LinkedListDeque<DLListType>{
    private  class DLList{
        public DLList pre;
        public DLListType item;
        public DLList next;

        public  DLList(DLList p,DLListType x,DLList n){
            pre=p;
            item=x;
            next=n;
        }

        public DLListType get(int i){
            DLListType tem;
            i--;
            if(i==0){
                return item;
            }
            tem=next.get(i);
            return tem;
        }

    }

    private DLList sentinel;
    private int size;

    public LinkedListDeque(){
        size=0;
        sentinel=new DLList(null,null,null);
    }

    public LinkedListDeque(LinkedListDeque other){
        DLList ptr;
        sentinel=new DLList(null,null,null);
        for(size=0,ptr=other.sentinel.next;size<other.size;size++){
            addFirst(ptr.item);
            ptr=ptr.next;
        }

    }

    public void addFirst(DLListType x){
        DLList tem;
        size +=1;
        if(size==1){
            sentinel.next= new DLList(sentinel,x,sentinel);
            sentinel.pre=sentinel.next;
        }else{
            tem=new DLList(sentinel,x,sentinel.next);
            sentinel.next=tem;
        }
    }

    public void addLast(DLListType x){
        DLList tem;
        size+=1;
        if(size==1){
            sentinel.pre = new DLList(sentinel,x,sentinel);
            sentinel.next=sentinel.pre;
        }else{
            tem = new DLList(sentinel.pre,x,sentinel);
            sentinel.pre=tem;
        }
    }

    public boolean isEmpty(){
        if(size>0) return false;
        return true;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        DLList ptr;
        ptr=sentinel.next;
        while(ptr!=sentinel){
            System.out.print(ptr.item);
            System.out.print(" ");
            ptr=ptr.next;
        }
        System.out.println();
    }

    public DLListType removeFirst(){
        size--;
        DLList tem;
        tem=sentinel.next;
        sentinel.next=tem.next;
        tem.next.pre=sentinel;
        return tem.item;
    }

    public DLListType removeLast(){
        size--;
        DLList tem;
        tem=sentinel.pre;
        sentinel.pre=tem.pre;
        tem.pre.next=sentinel;
        return tem.item;
    }

    public DLListType get(int index){
        int i;
        DLList ptr;
        if(index>size%2){
            for(i=1,ptr=sentinel.pre;i<(size-index+1);i++){
                ptr=ptr.pre;
            }
            return ptr.item;
        }else{
            for(i=1,ptr=sentinel.next;i<index;i++){
                ptr=ptr.next;
            }
            return ptr.item;
        }
    }

    public DLListType getRecursive(int index){
        return sentinel.next.get(index);
    }

}
