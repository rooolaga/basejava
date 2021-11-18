import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume resume) {
        storage[size] = resume;
        size++;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (Resume value : storage) {
            if (value != null && value.uuid.equals(uuid)) {
                resume = value;
                break;
            }
        }
        return resume;
    }

    void delete(String uuid) {
        int positionShift = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) positionShift++;

            if (positionShift > 0) {
                if (i + positionShift <= storage.length) {
                    storage[i] = storage[i + positionShift];
                } else {
                    storage[i] = null;
                }
            }
        }

        size -= positionShift;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
