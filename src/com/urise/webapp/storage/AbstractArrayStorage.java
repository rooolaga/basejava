package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getResumePos(uuid);
        return index != -1 ? storage[index] : null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getResumePos(resume.getUuid());
        if (size < STORAGE_LIMIT) {
            if (index < 0) {
                size++;
                insertElement(resume, index);
            } else {
                System.out.println("This resume " + resume.getUuid() + " is already in the array");
            }
        } else {
            System.out.println("The array is full. Resume " + resume.getUuid() + " has not saved");
        }
    }

    public void delete(String uuid) {
        int index = getResumePos(uuid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, size - 1);
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

    protected abstract int getResumePos(String uuid);

    protected abstract void insertElement(Resume resume, int index);
}
