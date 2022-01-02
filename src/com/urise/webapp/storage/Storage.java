package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    public void clear();

    public void save(Resume resume);

    public Resume get(String uuid);

    public void delete(String uuid);

    public void update(Resume resume);

    public Resume[] getAll();

    public int size();
}
