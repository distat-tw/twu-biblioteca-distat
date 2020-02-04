package com.twu.login;

import com.twu.Biblioteca;
import com.twu.menu.MenuSelection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Login {
    private ArrayList<Account> accountList;
    private BufferedReader bufferedReader;
    private PrintStream printStream;
    private Account currentAccount;

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Login(ArrayList<Account> accountList, BufferedReader bufferedReader, PrintStream printStream) {
        this.accountList = accountList;
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
        setCurrentAccount(null);
    }

    public void initLogin() throws IOException {
        this.verifyUsernameAndPassword();

        // TODO: Get rid off this one
        Biblioteca biblioteca = new Biblioteca();
        System.out.println(biblioteca.showWelcomeMessage());
        System.out.println("Welcome "+currentAccount.getName());
        MenuSelection menu = new MenuSelection();
        menu.startMenuSelection();
    }

    public void verifyUsernameAndPassword() throws IOException {
        System.out.println("Username: ");
        while(!this.verifyLibraryIDInput()) {
            System.out.println("Please enter username again:");
        }
        System.out.println("Password: ");
        while(!this.verifyPasswordInput()) {
            System.out.println("Please enter password again:");
        }
    }

    public Boolean verifyLibraryIDInput() throws IOException {
        String libraryIDInput = this.input();
        return this.isLibraryIDInputValid(libraryIDInput);
    }

    public Boolean isLibraryIDInputValid(String libraryIDInput) {
        boolean validFormat = this.isInputFormatCorrect(libraryIDInput);
        boolean accountExist = this.isLibraryIDExisted(libraryIDInput);

        if (validFormat && accountExist) {
            return true;
        } else if (!validFormat) {
            printStream.println("Invalid Username Format");
            return false;
        } else {
            printStream.println("User not existed");
            return false;
        }
    }

    public Boolean isInputFormatCorrect(String libraryIDInput) {
        return libraryIDInput.matches("[0-9]{3}-[0-9]{4}");
    }

    public Boolean isLibraryIDExisted(String libraryIDInput) {
        for (Account account:accountList) {
            if (account.getLibraryID().equals(libraryIDInput)) {
                this.currentAccount = account;
                return true;
            }
        } return false;
    }

    public Boolean verifyPasswordInput() throws IOException {
        String passwordInput = this.input();
        boolean validPassword = this.isPasswordInputInDB(passwordInput);
        if (!validPassword) {
            printStream.println("Invalid Password");
            return false;
        } return true;
    }

    public boolean isPasswordInputInDB(String input) {
        if (currentAccount.getPassword().equals(input)) {
            System.out.println(currentAccount.getName());
            System.out.println("Password Checked");
            return true;
        } return false;
    }

    public ArrayList<Account> fetchAccountFromDB() {
        return accountList;
    }

    public String input() throws IOException {
        return bufferedReader.readLine();
    }
}