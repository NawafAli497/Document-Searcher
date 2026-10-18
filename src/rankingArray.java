
public class rankingArray<T> {
    PQElement<T>[] elements;
    int count;

   
    public rankingArray(int maxSize) {
        elements = (PQElement<T>[]) new PQElement[maxSize];
        count = 0;
    }
    public int length() {
    	return count;
    }
    

    // Insert method
    public void insert(T data, int priority) {
        if (count >= elements.length) {
            System.out.println("array is full cant insrert");
        }
        elements[count] = new PQElement<T>(data, priority);
        count++;
    }

    // Merge sort wrapper method
    public void mergeSort() {
        mergeSortHelper(elements, 0, count - 1);
    }

    // Merge sort implementation
    private void mergeSortHelper(PQElement<T>[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(PQElement<T>[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        PQElement<T>[] leftArray = new PQElement[n1];
        PQElement<T>[] rightArray = new PQElement[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            // Sort in descending order based on priority
            if (leftArray[i].p >= rightArray[j].p) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray, if any
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray, if any
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Print method for testing
    public void printQueue() {
        for (int i = 0; i < count; i++) {
            System.out.println("Data: " + elements[i].data + ", Priority: " + elements[i].p);
        }
    }
}
