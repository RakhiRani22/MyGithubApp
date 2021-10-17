package com.myapp.mygithubapp;

import com.myapp.mygithubapp.model.repo.Repo;
import com.myapp.mygithubapp.util.DataValidator;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class DataValidatorTest {
    @Test
    public void validateEmptyUserInput() {
        assertEquals(false, DataValidator.isUserInputValid(""));
    }

    @Test
    public void validateUserInput() {
        assertEquals(true, DataValidator.isUserInputValid("Rakhirani22"));
    }

    @Test
    public void validateNullUserInput() {
        assertEquals(false, DataValidator.isUserInputValid(null));
    }

    @Test
    public void validateRepositoryNotFound() {
        ArrayList<Repo> list = new ArrayList<>();
        list.add(new Repo("Sampl"));
        list.add(new Repo("Samp"));
        list.add(new Repo("Samplee"));
        assertEquals(false, DataValidator.isRepositoryFound(list, "Sample"));
    }

    @Test
    public void validateRepositoryFound() {
        ArrayList<Repo> list = new ArrayList<>();
        list.add(new Repo("Sampl"));
        list.add(new Repo("Sample"));
        list.add(new Repo("Samplee"));
        assertEquals(true, DataValidator.isRepositoryFound(list, "Sample"));
    }

    @Test
    public void validateRepositoryNotFoundWhenListIsEmptyFound() {
        ArrayList<Repo> list = new ArrayList<>();
        assertEquals(false, DataValidator.isRepositoryFound(list, "Sample"));
    }

    @Test
    public void validateRepositoryNotFoundWhenListIsNull() {
        ArrayList<Repo> list = null;
        assertEquals(false, DataValidator.isRepositoryFound(list, "Sample"));
    }
}
