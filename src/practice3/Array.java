package practice3;

/**
 * Created with IntelliJ IDEA. Continued with Eclipse. by E. Shevchenko
 */
public class Array<E> {

    int lastPos = 0;
    private E[] array;

    /**
     * Constructor
     *
     * @param size
     */
    @SuppressWarnings("unchecked")
    public Array(int size) {
        array = (E[]) new Object[size];
    }

    /**
     * Returns size
     *
     * @return
     */
    public int getSize() {
        return array.length;
    }

    /**
     * Check whether the index is within array boundaries
     *
     * @param index
     * @return
     */
    private boolean indexOK(int index) {
        return index < array.length && index >= 0;
    }

    /**
     * If index is within array boundary, returns Element at specified index,
     * otherwise returns null
     *
     * @param index
     * @return
     */
    public E getElement(int index) {
        if (indexOK(index)) {
            return (E) array[index];
        }
        return null;
    }

    /**
     * If index is within array boundary, places specified element at specified
     * position and returns true, otherwise returns false
     *
     * @param index
     * @param element
     * @return
     */
    public boolean setElement(int index, E element) {
        if (indexOK(index)) {

            array[index] = element;

            if (index > lastPos && element != null) {
                lastPos = index;
            } else if (index == lastPos && element == null) {
                while (array[lastPos] == null && lastPos > 0) {
                    --lastPos;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Append element to the end  of the array and expand capacity by one if needed
     *
     * @param element
     * @return
     */
    public boolean append(E element) {
        if (lastPos > array.length - 1) {
            changeCapacityBy(1);
        }
        return setElement(lastPos, element);
    }

    /**
     * Remove cell with the passed index. Capacity is reduced by one.
     *
     * @param index
     */
    public void removeAt(int index) {
        E[] temparray = (E[]) new Object[array.length - 1];

        if (index == 0) {
            // Copy everything except first element
            System.arraycopy(array, 1, temparray, 0, array.length - 1);

        } else if (index == array.length - 1) {
            // Copy everything except last element
            System.arraycopy(array, 0, temparray, 0, array.length - 1);

        } else if (indexOK(index)) {
            // Assume deletion at index = 3
            // 0 1 2 3 4 5 6 7 8 9
            // _ _ _ x _ _ _ _ _ _

            // 0 1 2 _ _ _ _ _ _
            System.arraycopy(array, 0, temparray, 0, index);

            // _ _ _ 4 5 6 7 8 9
            System.arraycopy(array, index + 1, temparray, index, array.length - 1 - index);
            // Result:
            // 0 1 2 4 5 6 7 8 9

        } else {

            throw new IndexOutOfBoundsException("Cannot delete element - invalid index passed: "
                    + index + "not in 0..." + (array.length - 1) + " range.");
        }

        array = temparray;
    }

    /**
     * Change capacity by the passed number (may be negative)
     *
     * @param number
     */
    @SuppressWarnings("unchecked")
    public void changeCapacityBy(int number) {
        int newSize = array.length + number;
        E[] temparray = (E[]) new Object[newSize];
        System.arraycopy(array, 0, temparray, 0, array.length > newSize ? newSize : array.length);
        array = temparray;
    }

    /**
     * Change capacity by the passed number (may be negative)
     */
    public Array<E> getCopy() {
        Array<E> newArr = new Array<E>(array.length);
        System.arraycopy(array, 0, newArr.array, 0, array.length);
        return newArr;
    }

    /**
     * Shifts array content adding or removing cells at the beginning
     *
     * @param number
     */
    private void shiftBy(int number) {
        E[] temparr = (E[]) new Object[array.length + number];
        int srcPos = (number > 0) ? 0 : number;
        int destPos = (number > 0) ? number : 0;
        int length = (number > 0) ? array.length : array.length - number;
        System.arraycopy(array, srcPos, temparr, destPos, length);
        array = temparr;
    }

    /**
     * Shifts array content adding or removing cells starting at the passed index
     *
     * @param index
     * @param number
     */
    private void shiftBy(int index, int number) {
        E[] temparr = (E[]) new Object[array.length + number];
        int srcPos = (number > 0) ? 0 : number;
        int destPos = (number > 0) ? number : 0;
        int length = (number > 0) ? array.length : array.length - number;
        System.arraycopy(array, srcPos, temparr, destPos, length);
        array = temparr;
    }

    /**
     * Returns String
     */
    public int getLastPosition() {
        return lastPos;
    }

    /**
     * Returns String
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append("Array[" + i + "] = " + array[i]);
            if (i < array.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }


}
