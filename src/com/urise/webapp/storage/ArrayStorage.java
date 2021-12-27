package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            if (getResumePos(resume.getUuid()) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("This resume " + resume.getUuid() + " is already in the array");
            }
        } else {
            System.out.println("The array is full. Resume " + resume.getUuid() + " has not saved");
        }
    }

    public Resume get(String uuid) {
        int index = getResumePos(uuid);
        return index != -1 ? storage[index] : null;
    }

    public void delete(String uuid) {
        int index = getResumePos(uuid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, storage.length - index - 1);
            size--;
        } else {
            System.out.println("Resume " + uuid + " has not deleted");
        }
    }

    public void update(Resume resume) {
        int index = getResumePos(resume.getUuid());
        if (index != -1)
            storage[index] = resume;
        else
            System.out.println("Resume " + resume.getUuid() + " has not updated");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int getResumePos(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        System.out.println("Resume " + uuid + " is not found");
        return -1;
    }
}
