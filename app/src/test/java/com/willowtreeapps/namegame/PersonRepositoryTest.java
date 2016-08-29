package com.willowtreeapps.namegame;

import android.test.suitebuilder.annotation.SmallTest;

import com.willowtreeapps.namegame.network.api.NameGameApi;
import com.willowtreeapps.namegame.network.api.Person;
import com.willowtreeapps.namegame.network.api.PersonRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SmallTest
public class PersonRepositoryTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private static final List<Person> PEOPLE;

    static {
        PEOPLE = new ArrayList<>();
        PEOPLE.add(new Person("Bill"));
        PEOPLE.add(new Person("Pam"));
        PEOPLE.add(new Person("Fred"));
    }

    @Test
    public void should_throw_for_multiple_registration_of_one_listener() throws Exception {
        NameGameApi api = mock(NameGameApi.class);
        when(api.getPeople()).thenReturn(SynchronousCallAdapter.forSuccess(PEOPLE));
        PersonRepository repo = new PersonRepository(api);
        PersonRepository.Listener listener = mock(PersonRepository.Listener.class);
        repo.register(listener);
        thrown.expect(IllegalStateException.class);
        repo.register(listener);
    }

    @Test
    public void should_allow_registration_of_multiple_listeners() throws Exception {
        NameGameApi api = mock(NameGameApi.class);
        when(api.getPeople()).thenReturn(SynchronousCallAdapter.forSuccess(PEOPLE));
        PersonRepository repo = new PersonRepository(api);
        PersonRepository.Listener one = mock(PersonRepository.Listener.class);
        PersonRepository.Listener two = mock(PersonRepository.Listener.class);
        PersonRepository.Listener three = mock(PersonRepository.Listener.class);
        repo.register(one);
        repo.register(two);
        repo.register(three);
    }

    @Test
    public void should_notify_new_registrants_on_success() throws Exception {
        NameGameApi api = mock(NameGameApi.class);
        when(api.getPeople()).thenReturn(SynchronousCallAdapter.forSuccess(PEOPLE));
        PersonRepository repo = new PersonRepository(api);
        PersonRepository.Listener listener = mock(PersonRepository.Listener.class);
        repo.register(listener);
        verify(listener, times(1)).onLoadFinished(anyListOf(Person.class));
    }

    @Test
    public void should_not_notify_new_registrants_on_load_failure() throws Exception {
        NameGameApi api = mock(NameGameApi.class);
        when(api.getPeople()).thenReturn(SynchronousCallAdapter.<List<Person>>forError());
        PersonRepository repo = new PersonRepository(api);
        PersonRepository.Listener listener = mock(PersonRepository.Listener.class);
        repo.register(listener);
        verify(listener, times(0)).onLoadFinished(anyListOf(Person.class));
    }

    @Test
    public void should_notify_existing_registrants_on_load_failure() throws Exception {
        NameGameApi api = mock(NameGameApi.class);
        when(api.getPeople()).thenReturn(SynchronousCallAdapter.<List<Person>>forError());
        PersonRepository.Listener listener = mock(PersonRepository.Listener.class);
        PersonRepository repo = new PersonRepository(api, listener);
        verify(listener, times(1)).onError(any(IOException.class));
    }

}
