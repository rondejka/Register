package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User interface of the application.
 */
public class ConsoleUI {
    /** register.Register of persons. */
    private Register register;
    
    /**
     * In JDK 6 use Console class instead.
     * @see readLine()
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Menu options.
     */
    private enum Option {
        PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
    };
    
    public ConsoleUI(Register register) {
        this.register = register;
    }
    
    public void run() {
        while (true) {
            switch (showMenu()) {
                case PRINT:
                    printRegister();
                    break;
                case ADD:
                    addToRegister();
                    break;
                case UPDATE:
                    updateRegister();
                    break;
                case REMOVE:
                    removeFromRegister();
                    break;
                case FIND:
                    findInRegister();
                    break;
                case EXIT:
                    return;
            }
        }
    }
    
    private String readLine() {
        //In JDK 6.0 and above Console class can be used
        //return System.console().readLine();
        
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    private Option showMenu() {
        System.out.println("Menu.");
        for (Option option : Option.values()) {
            System.out.printf("%d. %s%n", option.ordinal() + 1, option);
        }
        System.out.println("-----------------------------------------------");
        
        int selection = -1;
        do {
            System.out.println("Option: ");
            selection = Integer.parseInt(readLine());
        } while (selection <= 0 || selection > Option.values().length);
        
        return Option.values()[selection - 1];
    }
    
    //TODO: Implement the method printRegister
    private void printRegister() {

        for (int i =0; i < register.getCount(); i++) {
            System.out.println(i+1 + ". " + register.getPerson(i));
        }
        System.out.println();
    }
    
    private void addToRegister() {
        System.out.println("Enter Name: ");
        String name = readLine();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = readLine();
        
        register.addPerson(new Person(name, phoneNumber));
    }
    
    //TODO: Implement the method updateRegister
    private void updateRegister() {
        System.out.println("Enter index: ");
        int index = Integer.parseInt(readLine());
        System.out.println("Enter  Name: ");
        String name = readLine();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = readLine();
        register.updatePerson(new Person(name, phoneNumber), index - 1);
    }
    
    //TODO: Implement the method findInRegister
    private void findInRegister() {
        System.out.println("Enter Phone Number: ");
        String phoneNumber = readLine();

        Person personX = register.findPersonByPhoneNumber(phoneNumber);
        if (personX == null) {
            System.out.println("Nenajdene");
        } else {
            System.out.println(personX);
        }
    }
    
    private void removeFromRegister() {
        System.out.println("Enter index: ");
        int index = Integer.parseInt(readLine());
        Person person = register.getPerson(index - 1);
        //register.removePerson(person);
        register.removePerson(index - 1);
    }

}
