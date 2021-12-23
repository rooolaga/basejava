package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            if (getResumePos(resume.uuid) == null) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("This resume is already in the array");
            }
        } else {
            System.out.println("The array is full");
        }
    }

    public Resume get(String uuid) {
        Integer resumePos = getResumePos(uuid);
        return resumePos != null ? storage[resumePos] : null;
    }

    public void delete(String uuid) {
        Integer resumePos = getResumePos(uuid);
        if (resumePos != null) {
            System.arraycopy(storage, resumePos + 1, storage, resumePos, storage.length - resumePos - 1);
            size--;
        }
    }

    public void update(Resume resume) {
        Integer resumePos = getResumePos(resume.uuid);
        if (resumePos != null) storage[resumePos] = resume;
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

    Integer getResumePos(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        System.out.println("Resume is not found");
        return null;
    }
}
