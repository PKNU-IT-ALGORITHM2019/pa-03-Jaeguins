
public class MainClass {
    public static java.util.Random random=new java.util.Random();
    public static int[] random1K,reverse1K,random10K,reverse10K,random100K,reverse100K;
    public static void main(String args[]){
        System.out.print("Initializing...");
        random1K=new int[1000];
        random10K=new int[10000];
        random100K=new int[100000];
        reverse1K=new int[1000];
        reverse10K=new int[10000];
        reverse100K=new int[100000];
        int N=1000;
        for(int i=0;i<N;i++){
            random1K[i]=random.nextInt(N);
            reverse1K[i]=N-i;
        }
        N*=10;
        for(int i=0;i<N;i++){
            random10K[i]=random.nextInt(N);
            reverse10K[i]=N-i;
        }
        N*=10;
        for(int i=0;i<N;i++){
            random100K[i]=random.nextInt(N);
            reverse100K[i]=N-i;
        }
        System.out.print("\r");
        System.out.println("\t\tRandom1K\tReverse1K\tRandom10K\tReverse10K\tRandom100K\tReverse100K");
        printSort("Bubble\t",ISort.bubbleSorting);
        printSort("Selection",ISort.selectionSorting);
        printSort("Insertion",ISort.insertionSorting);
        printSort("Merge\t",ISort.mergeSorting);
        printSort("Quick1\t",ISort.QuickSort,0);
        printSort("Quick2\t",ISort.QuickSort,1);
        printSort("Quick3\t",ISort.QuickSort,2);
        printSort("Heap\t",ISort.heapSorting);
        printSort("Library\t",ISort.STLSorting);
    }
    
    static String tab="\t\t";

    static void printSort(String sortName, Sorting sorting){
        System.out.print(sortName+"\t");
        System.out.print(ISort.printTime(sorting,random1K.clone())+tab);
        System.out.print(ISort.printTime(sorting,reverse1K.clone())+tab);
        System.out.print(ISort.printTime(sorting,random10K.clone())+tab);
        System.out.print(ISort.printTime(sorting,reverse10K.clone())+tab);
        System.out.print(ISort.printTime(sorting,random100K.clone())+tab);
        System.out.println(ISort.printTime(sorting,reverse100K.clone())+tab);
    }

    static void printSort(String sortName,Quick sort,int mod){
        System.out.print(sortName+"\t");
        System.out.print(ISort.printTime(sort,mod,random1K.clone())+tab);
        System.out.print(ISort.printTime(sort,mod,reverse1K.clone())+tab);
        System.out.print(ISort.printTime(sort,mod,random10K.clone())+tab);
        System.out.print(ISort.printTime(sort,mod,reverse10K.clone())+tab);
        System.out.print(ISort.printTime(sort,mod,random100K.clone())+tab);
        System.out.println(ISort.printTime(sort,mod,reverse100K.clone())+tab);
    }
}
