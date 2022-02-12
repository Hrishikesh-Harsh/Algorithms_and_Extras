import java.util.*;

public class All_Sort {

    static Scanner sc = new Scanner(System.in);

    public static int[] pigeonHole_sort(int x[])
    {
        int max=x[0];
        int min=x[0];
        
        for(int i=1;i<x.length;i++)
        {
            if(x[i]>max)
            max=x[i];

            if(x[i]<min)
            min=x[i];
        }

        int range=max-min+1;
        int newArray[]=new int[range];

        Arrays.fill(newArray,-1);

        for(int i=0;i<x.length;i++)
        {
            int index=x[i]-min;
            newArray[index]=x[i];
        }
        int index=0;
        for(int i=0;i<newArray.length;i++)
        {
            if(newArray[i]!=-1)
            x[index++]=newArray[i];
        }
        return x;
    }
    
    public static int[] shell_sort(int x[])
    {
        int length=x.length;
        int interval=length/2;          //Initial interval at which elements would be sorted
        while(interval>0)
        {
            //System.out.println(Arrays.toString(x));
            if(interval==1)             //If interval equals 1, then perform normal Insertion Sort
            x=insertion_sort(x);
            else
            {
                int end=interval;               //Keeps a track of the elements covered in the array in each pass
            //int current=x[interval];
            int index=end;                      //Goes back (interval) number of elements to check and compare
            int flag=0;                         //Flag to see if end has changed
            do{
                flag=0;
                if((index-interval)>=0)
                {
                    if(x[index]<x[index-interval])      //If the element present interval no. of elements back is larger, then exchange
                    {
                        int temp=x[index];
                        x[index]=x[index-interval];
                        x[index-interval]=temp;

                        index=index-interval;
                    }
                    else
                    {
                        end++;
                        flag=1;
                    }
                }
                else
                {
                    end++;
                    flag=1;
                }
                if(flag==1)
                    index=end;

                }while(end!=(length));
            }
            interval=interval/2;                    //Reduce interval after end pointer reaches end of array
        }
        return x;
    }
    
    public static int[] quick_sort(int x[]) {
        if (x.length <= 1)
            return x;

        int p = x[0]; // pivot element
        int l = 0;
        int m = 0;

        for (int i = 1; i < x.length; i++) {
            if (x[i] < p) {
                l += 1;
            } else if (x[i] >= p) {
                m += 1;
            }
        }
        int less[] = new int[l]; // elements less than pivot
        int li = 0;
        int more[] = new int[m]; // elements more than pivot
        int mi = 0;

        for (int i = 1; i < x.length; i++) {
            if (x[i] < p) {
                less[li++] = x[i];
            } else if (x[i] >= p) {
                more[mi++] = x[i];
            }
        }

        int lr[] = quick_sort(less); // recursively sorting the less_than sub-half
        int mr[] = quick_sort(more); // recursively sorting the more_than sub-half

        int n[] = new int[lr.length + mr.length + 1];
        int ni = 0;

        for (int i = 0; i < lr.length + mr.length + 1; i++) { // joining them all
            if (i < lr.length) {
                n[ni++] = lr[i];
            } else if (i > lr.length) {
                n[ni++] = mr[i - lr.length - 1];
            } else {
                n[ni++] = p;
            }
        }
        return n;
    }

    public static int[] merge_sort(int x[]) {
        if (x.length <= 1)
            return x;

        int mid = (x.length - 1) / 2;
        int l[] = new int[mid + 1];
        int li = 0;
        int r[] = new int[x.length - mid - 1];
        int ri = 0;

        for (int i = 0; i < x.length; i++) { // partitioning the array into two parts
            if (i < mid + 1) {
                l[li++] = x[i];
            } else if (i > mid) {
                r[ri++] = x[i];
            }
        }

        int lr[] = merge_sort(l); // recursively sorting the left sub-half
        int rr[] = merge_sort(r); // recursively sorting the right sub-half

        int mr[] = merge(lr, rr); // calling merge() to merge these 2 parts into a sorted array
        return mr;
    }

    public static int[] merge(int x[], int y[]) {
        int xi = 0;
        int yi = 0;
        int n[] = new int[x.length + y.length];
        int ni = 0;
        while (xi < x.length && yi < y.length) { // taking two sorted arrays and combining them into a single sorted
                                                 // array
            if (x[xi] < y[yi]) {
                n[ni++] = x[xi++];
            } else {
                n[ni++] = y[yi++];
            }
        }

        while (xi < x.length) {
            n[ni++] = x[xi++];
        }

        while (yi < y.length) {
            n[ni++] = y[yi++];
        }
        return n;
    }

    public static int[] radix_sort(int x[], int d) { // sorting by digit
        int p = 0;
        while (d > 0) {
            x = sort_digit(x, p);
            d = d - 1;
            p += 1;
        }
        return x;
    }

    public static int[] sort_digit(int y[], int p) {
        int d[] = new int[10];

        for (int j = 0; j < y.length; j++) {
            int ld = (y[j] / (int) (Math.pow(10, p))) % 10; // counting occurance of each digit
            d[ld] += 1;
        }

        for (int j = 1; j < 10; j++) {
            d[j] = d[j] + d[j - 1];
        }

        int ny[] = new int[y.length];

        for (int j = y.length - 1; j >= 0; j--) {
            int ld = (y[j] / (int) (Math.pow(10, p))) % 10; // sorting
            int place = --d[ld];
            ny[place] = y[j];
        }
        return ny;

    }

    public static int[] insertion_sort(int x[]) { // sorting the sub-array before current element as we go forward

        for (int i = 0; i < x.length; i++) {
            int p = i;
            for (int j = i; j >= 0; j--) {
                if (x[p] < x[j]) {
                    int t = x[p];
                    x[p] = x[j];
                    x[j] = t;
                    p--;
                }
            }
        }
        return x;
    }

    public static int[] bubble_sort(int x[]) { // swapping each element by its neighbour as we traverse the array

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length - i - 1; j++) {
                if (x[j] > x[j + 1]) {
                    int t = x[j];
                    x[j] = x[j + 1];
                    x[j + 1] = t;
                }
            }
        }
        return x;
    }

    public static int[] selection_sort(int x[]) { // picking out smallest element on each go and placing it at front

        for (int i = 0; i < x.length; i++) {
            int min = x[i];
            int pos = i;
            for (int j = i + 1; j < x.length; j++) {
                if (x[j] < min) {
                    min = x[j];
                    pos = j;
                }
            }
            int t = x[i];
            x[i] = min;
            x[pos] = t;
        }
        return x;
    }

    public static int[] counting_sort(int x[]) { // sorting the elements based on their range
        int max = x[0];
        int min = x[0];

        for (int i = 1; i < x.length; i++) {
            if (x[i] > max)
                max = x[i];

            if (x[i] < min)
                min = x[i];
        }

        int range = max - min;
        int count[] = new int[range + 1];

        for (int i = 0; i < x.length; i++) {
            count[x[i] - min] += 1;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        int y[] = new int[x.length];
        for (int i = x.length - 1; i >= 0; i--) {
            int pos = --count[x[i] - min];
            y[pos] = x[i];
        }
        return y;
    }

    public static int[] bucket_sort(int x[]) {
        int max = x[0];
        for (int i = 1; i < x.length; i++) {
            if (x[i] > max)
                max = x[i];
        }

        ArrayList<ArrayList<Integer>> bucket = new ArrayList<ArrayList<Integer>>();
        int slots = (int) (Math.ceil((max + 1) / 10.0));

        for (int i = 0; i < 10; i++) {
            bucket.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < x.length; i++) {
            int j = (int) Math.floor(x[i] / slots); // putting different elements in buckets based on their value
            bucket.get(j).add(x[i]);
        }

        for (int i = 0; i < 10; i++) {
            Collections.sort(bucket.get(i));
        }
        int y[] = new int[x.length];
        int yi = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < bucket.get(i).size(); j++) { // re-combining the elements from the buckets
                y[yi++] = bucket.get(i).get(j);
            }

        }
        return y;
    }

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Let's Sort..!");
        System.out.println("----------------------------------");
        System.out.println();
        System.out.println("Enter size of array to be sorted-");
        System.out.println("-----------------------------------");

        int n = sc.nextInt();
        System.out.println();

        System.out.println("Enter the elements\n"+
            "[Non negative for Radix sort, Counting sort, Bucket sort, Pigeonhole sort]\n"+
            "[Non Duplicate for Pigeohole Sort]");
        System.out.println("-----------------------------------");

        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Now Select the Sorting Technique to sort the elements:");
        System.out.println();
        System.out.println("1. Bubble Sort-> O(n^2)");
        System.out.println("2. Selection Sort-> O(n^2)");
        System.out.println("3. Insertion Sort-> O(n^2)");
        System.out.println("4. Quick Sort-> O(n*log(n))");
        System.out.println("5. Merge Sort-> O(n*log(n))");
        System.out.println("6. Radix Sort-> O(d*n)");
        System.out.println("7. Counting Sort-> O(n+k)");
        System.out.println("8. Bucket Sort-> O(n+k)");
        System.out.println("9. Shell Sort-> O(n^2)");
        System.out.println("10. Pigeonhole Sort-> O(N+n)");
        System.out.println();

        int ch = sc.nextInt();
        int b[] = new int[a.length];

        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max)
                max = a[i];
        }
        int digits = 0;
        while (max > 0) {
            digits += 1;
            max = max / 10;
        }

        long start = 0L;
        long finish = 0L;

        switch (ch) {
            case 1:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = bubble_sort(a);
                finish = System.nanoTime();
                System.out.println("Bubble Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 2:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = selection_sort(a);
                finish = System.nanoTime();
                System.out.println("Selection Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 3:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = insertion_sort(a);
                finish = System.nanoTime();
                System.out.println("Insertion Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 4:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = quick_sort(a);
                finish = System.nanoTime();
                System.out.println("Quick Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 5:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = merge_sort(a);
                finish = System.nanoTime();
                System.out.println("Merge Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 6:
                System.out.println();
                System.out.println("--------------------------------");

                start = System.nanoTime();
                b = radix_sort(a, digits);
                finish = System.nanoTime();
                System.out.println("Radix Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 7:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = counting_sort(a);
                finish = System.nanoTime();
                System.out.println("Counting Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 8:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = counting_sort(a);
                finish = System.nanoTime();
                System.out.println("Bucket Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 9:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = shell_sort(a);
                finish = System.nanoTime();
                System.out.println("Shell Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            case 10:
                System.out.println();
                System.out.println("--------------------------------");
                start = System.nanoTime();
                b = pigeonHole_sort(a);
                finish = System.nanoTime();
                System.out.println("Pigeonhole Sort applied...!");
                System.out.println();
                for (int i = 0; i < b.length; i++) {
                    System.out.println(b[i]);
                }
                break;

            default:
                System.out.println();
                System.out.println("None of the above choices made");
                System.out.println("---------------------------------");
                System.exit(0);
        }
        long timeElapsed = finish - start;
        System.out.println("Time taken (in nanoseconds)= " + timeElapsed);
        System.out.println("---------------------------------");
        System.out.println();

    }
}
