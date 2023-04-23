import java.util.Scanner;

class BankAccount {
    int balance;
    int prevTransaction;
    String customerName;
    String customerId;
    int flag = 0;

    BankAccount(String cName, String cId) {
        customerName = cName;
        customerId = cId;
    }

    public final void clrscr() {
        try {
            try {
                final String os = System.getProperty("os.name");

                if (os.contains("windows")) {
                    Runtime.getRuntime().exec("cls");
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            } catch (final Exception e) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        }  catch (final Exception es) {
            //System.out.println("nothing here!");
        }
    }
    void checkId() {
        clrscr();
        System.out.println("welcome" + customerName);
        System.out.println();
        System.out.println("please enter the customer id or pin: ");
        Scanner id = new Scanner(System.in);
        String chk = id.nextLine();
        if (chk.equals(customerId)) {
            clrscr();
            showMenu();
        } else {
            System.out.println("================================================");
            System.out.println("wrong Login!!");
            System.out.println("================================================");

            if(flag < 3) {
                flag++;
                checkId();
            }
        }
    }
    void deposit(int amount) {
        if(amount != 0){
            balance = balance + amount;
            prevTransaction = amount;
        }
    }

    void withdraw(int amount) {
        if(this.balance >amount) {
            balance = balance - amount;
            prevTransaction = -amount;
        } else {
            clrscr();
            System.out.println("===================================================");
            System.out.println("Sufficent balance not available for withdrawl");
            System.out.println("==================================================");
        }
    }

    void getPrevTansaction() {
        if (prevTransaction > 0) {
            System.out.println("Deposited: " + prevTransaction);
        } else if (prevTransaction < 0) {
            System.out.println("withdraw: " + Math.abs(prevTransaction));
        }else {
            System.out.println("No Transaction Occured");
        }
    }

    public void transfer(double amount, BankAccount acc){
        if(this.balance < amount) {
            clrscr();
            System.out.println("===============================================");
            System.out.println("Transfer fail due to insufficent Balance!");
            System.out.println("================================================");
        } else {
            this.balance -= amount;
            acc.balance += amount;
            System.out.println("Account of " + this.customerName + "become $" + this.balance);
            System.out.println("Account of " + this.customerName + "become $" + acc.balance);
            System.out.println("\n");
        }
    }

    private void showMenu(){
        char option;
        Scanner sc = new Scanner(System.in);
        System.out.println("welcome " + customerName);
        System.out.println("your ID is  " + customerId);
        do {
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("1. Check balance  \n 2. Deposit \n 3. WithDraw  \n 4. previousatransaction \n 5. Transfer \n 6.Exit ");

            System.out.println("=============================");
            System.out.println("enter the option");
            System.out.println("=============================");
            option = sc.next().charAt(0);
            option = Character.toUpperCase(option);
            System.out.println("\n");

            switch (option){
                case 1:
                    clrscr();
                    System.out.println("===================");
                    System.out.println("Balance " + balance);
                    System.out.println("====================");
                    System.out.println("\n");
                    break;
                case 2 :
                    clrscr();
                    System.out.println("====================");
                    System.out.println("Enter the amount to deposit");
                    System.out.println("======================");
                    int amount = sc.nextInt();
                    deposit(amount);
                    System.out.println("\n");
                    break;
                case 3 :
                    clrscr();
                    System.out.println("====================");
                    System.out.println("Enter the amount to withdraw");
                    System.out.println("======================");
                    int amount2 = sc.nextInt();
                    deposit(amount2);
                    System.out.println("\n");
                    break;
                case 4:
                    clrscr();
                    System.out.println("===================");
                    getPrevTansaction();
                    System.out.println("====================");
                    System.out.println("\n");
                    break;
                case 5 :
                    clrscr();
                    System.out.println("***********************");
                    System.out.println("to whom");
                    BankAccount bb = new BankAccount("FARA" , "1002");
                    System.out.println(bb.customerName);
                    System.out.println("**********************");
                    System.out.println("amount to transfer");
                    double am = sc.nextDouble();
                    System.out.println("*************************");
                    transfer(am,bb);
                    break;
                case 6 :
                    clrscr();
                    System.out.println("*********************");
                    break;
                default:
                    clrscr();
                    System.out.println("invalid option !!!please enter again");
            }
        }while (option != 6);
        System.out.println("thank you for using our service");
    }
}



public class ATMinterface {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount("AZRA","9999");
        ba.checkId();
    }
}
