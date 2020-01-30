package com.twu.login;

import com.twu.Account;
import com.twu.Login;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matcher.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class LoginTest {
    private ArrayList<Account> accountList;
    private Login login;
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    @Before
    public void setUp() {
        accountList = new ArrayList<>();
        bufferedReader = mock(BufferedReader.class);
        printStream = mock(PrintStream.class);
        login = new Login(accountList, bufferedReader, printStream);
        Account account1 = new Account("John Cena", "111-1111", "password");
        Account account2 = new Account("Peter Parker", "222-2222", "password");
        Account account3 = new Account("Elon Mustnot", "333-3333", "password");
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
    }

    @Test
    public void shouldPrintInvalidUsernameFormatWhenInputWrongUsernameFormat() throws IOException {
        // Arrange
        when(bufferedReader.readLine()).thenReturn("11-11111");

        // Action
        login.verifyUsernameInput();

        // Assert
        verify(printStream).println("Invalid Username Format");
    }

    @Test
    public void shouldReturnInvalidMessageWhenLoggedInWithNonExistingUser() throws IOException {
        // message returned should contain some Invalid message
        // Arrange
        when(bufferedReader.readLine()).thenReturn("444-4444");

        // Action
        login.verifyUsernameInput();

        // Assert
        verify(printStream).println("User not existed");
    }

    @Test
    public void shouldReturnInvalidMessageWhenPasswordDoesNotMatch() throws IOException {
        // message returned should contain some Invalid message
        // Arrange
        when(bufferedReader.readLine()).thenReturn("pass");

        // Action
        login.verifyPasswordInput();

        // Assert
        verify(printStream).println("Invalid Password");
    }

    @Test
    public void shouldReturnTrueIfPasswordMatches() throws IOException {
        // Arrange
        when(bufferedReader.readLine()).thenReturn("password");

        // Assert/Action
        assertThat(login.verifyPasswordInput(), is(true));
    }

    @Test
    public void shouldMatchRightCredentialWhenLoggedInAsThatSpecificPerson() {
        // Name of the logged in person should matches with the username
    }
}