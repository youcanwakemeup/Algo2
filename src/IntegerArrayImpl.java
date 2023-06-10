import exceptions.NoItemFoundInArrayException;
import exceptions.OutOfRangeException;

import java.util.Arrays;
import java.util.Objects;

public class IntegerArrayImpl implements IntegerArray {


    private Integer[] arr;

    public IntegerArrayImpl(int size) {
        this.arr = new Integer[size];
    }

    @Override
    public Object[] getArr() {
        return arr;
    }

    private void checkIfArgumentIsNull(Integer item) {
        if (item == null) {
            throw new NullPointerException("Пустое значение");
        }
    }

    @Override
    public Integer add(Integer item) {
        checkIfArgumentIsNull(item);
        int nextNullIndex = arr.length;
        if (arr[arr.length - 1] != null) {
            grow();
        }
        arr[nextNullIndex] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index > arr.length || index < 0) {
            throw new OutOfRangeException("Нет такого индекса");
        }
        checkIfArgumentIsNull(item);
        Integer[] newArr = new Integer[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        newArr[index] = item;
        if (newArr.length - (index + 1) >= 0)
            System.arraycopy(arr, index + 1 - 1, newArr, index + 1, newArr.length - (index + 1));
        arr = newArr;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index >= arr.length || index < 0) {
            throw new OutOfRangeException("Нет такого индекса");
        }
        checkIfArgumentIsNull(item);
        arr[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkIfArgumentIsNull(item);
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i], item)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoItemFoundInArrayException("Не найден такой элемент");
        }

        Integer[] newArr = new Integer[arr.length - 1];
        int newArrIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            newArr[newArrIndex] = arr[i];
            newArrIndex++;
        }
        arr = newArr;
        return item;
    }


    @Override
    public Integer remove(int index) {
        if (index >= arr.length || index < 0) {
            throw new OutOfRangeException("Нет такого индекса");
        }
        Integer[] newArr = new Integer[arr.length - 1];
        Integer removedElem = arr[index];
        for (int i = 0; i < newArr.length; i++) {
            if (i != index) {
                newArr[i] = arr[i];
            }
        }
        arr = newArr;
        return removedElem;
    }

    @Override
    public boolean contains(Integer item) {
        checkIfArgumentIsNull(item);
        sortArray(arr, arr[0], arr[arr.length - 1]);
        return binarySearch(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkIfArgumentIsNull(item);
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i], item)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoItemFoundInArrayException("Не найден такой элемент");
        }
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(this.arr[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkIfArgumentIsNull(item);
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i], item)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoItemFoundInArrayException("Не найден такой элемент");
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (Objects.equals(this.arr[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= arr.length || index < 0) {
            throw new OutOfRangeException("Нет такого индекса");
        }
        return arr[index];
    }

    @Override
    public boolean equals(IntegerArray otherList) {
        return Arrays.equals(arr, otherList.getArr());
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public boolean isEmpty() {
        return arr.length == 0;
    }

    @Override
    public void clear() {
        arr = new Integer[0];
    }

    @Override
    public Integer[] toArray() {
        Integer[] newArr = new Integer[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
        return arr;
    }

    @Override
    public int extendArray(int newSize) {
        Integer[] newArr = new Integer[newSize];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
        return newSize;
    }

    private void sortArray(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            sortArray(arr, begin, partitionIndex - 1);
            sortArray(arr, partitionIndex + 1, end);
        }
    }


    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                CompareSorts.swapElements(arr, i, j);
            }
        }

        CompareSorts.swapElements(arr, i + 1, end);
        return i + 1;
    }

    private int binarySearch(Integer item) {
        checkIfArgumentIsNull(item);
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item.equals(arr[mid])) {
                return mid;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }
    private void grow() {
        double newSize = arr.length * 1.5;
        int newSizeInt = (int) newSize;
        Integer[] newArr = new Integer[newSizeInt];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }
}


