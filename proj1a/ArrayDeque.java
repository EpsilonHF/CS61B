/*
 * define array deque
 */

public class ArrayDeque<Type> {
    public int size = 8;
    private int length;
    private int start;
    private int end;
    private Type[] array;
    
    // create an empty array
    public ArrayDeque() {
        array = (Type[])new Object[size];
        start = 0;   // position of first element
        end = 0;     // position after last element
        length = 0;  // number of elements
    }

    // add item in the front of array
    public void addFirst(Type item) {
        length++;
        // just add item
        if (length == 1) {
            array[0] = item;
            end = 1;
        }
        else if (start != end) {
            if (start > 0)
                array[--start] = item;
            else {
                start = size - 1;
                array[start] = item;
            }
        }
        // resize the array and add item
        else {
            resize(true);
            start = size -1;
            array[start] = item;
        }
    }

    // add item in the back of array
    public void addLast(Type item) {
        length++;

        // just add element
        if (length == 1) {
            array[0] = item;
            end = 1;
        }
        else if (start != end) {
            array[end++] = item;
            if (end == size) 
                end = 0;
        }
        // resize the array and add item
        else {
            resize(true);
            array[end++] = item;
        }
    }
    
    // return ture if array is empty otherwise false
    public boolean isEmpty() {
        return length == 0;
    }
    
    // return length of array
    public int size() {
        return length;
    }

    // print the element of array
    public void printDeque() {
        if (!isEmpty()) {
            int pos = start;
            while (pos != end) {
                System.out.print(array[pos]);
                pos++;
                if (pos == size)
                    pos = 0;
                if (pos != end)
                    System.out.print(" ");
            }
        }
    }
    
    // remove and return first element
    public Type removeFirst() {
        length--;
        Type res = array[start++];
        if (start == size)
            start = 0;
        if (size >= 16 && length <= size/4)
            resize(false);
        return res;
    }

    // remove and return last element
    public Type removeLast() {
        length--;
        end--;
        if (end < 0)
            end = size - 1;
        Type res = array[end];
        if (size >= 16 && length <= size/4)
            resize(false);
        return res;
    }

    // get nth element of array
    public Type get(int index) {
        if (index >= length)
            return null;
        if (index + start < size)
            index += start;
        else
            index = index + start - size;
        return array[index];
    }

    // resize the array, enlarge or shrink
    private void resize(boolean extend) {
        // enlarge the array to twice of original length
        if (extend){
            Type[] newarray = (Type[])new Object[size*2];
            if (start == 0){
                System.arraycopy(array, 0, newarray, 0, size);
            }
            else{
                System.arraycopy(array, start, newarray, 0, size-start);
                System.arraycopy(array, 0, newarray, size-start, start);
            }
            start = 0;
            end = size;
            size *= 2;
            array = newarray;
        }
        // shrink the array to 1/2 of original length
        else {
            Type[] newarray = (Type[])new Object[size/2];
            if (start < end) {
                System.arraycopy(array, start, newarray, 0, end-start);
            }
            else {
                System.arraycopy(array, start, newarray, 0, size-start);
                System.arraycopy(array, 0, newarray, size-start, end);
            }
            array = newarray;
            start = 0;
            end = length;
            size /= 2;
        }
    }
}
