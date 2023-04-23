/*
 * Run Command Lines in Terminal
 * java bankAccount.java
 * javac BankSystem.class
 * ctrl-c to stop tun
 */
class BankSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        for (int i = 0; i < 4; i++) {
            new DepositThread(account).start();
        }
        for (int i = 0; i < 5; i++) {
            new WithdrawThread(account).start();
        }
    }
}

class BankAccount {
    private int balance = 0;

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + " dollars.");
        System.out.println("Current balance: " + balance + " dollars.");
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + " dollars.");
            System.out.println("Current balance: " + balance + " dollars.");
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + " dollars, but the balance is insufficient.");
            System.out.println("Current balance: " + balance + " dollars.");
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}

class DepositThread extends Thread {
    private BankAccount account;

    public DepositThread(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            int amount = (int) (Math.random() * 100) + 1;
            account.deposit(amount);
            try {
                Thread.sleep(1000); // Sleep for 1 second 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class WithdrawThread extends Thread {
    private BankAccount account;

    public WithdrawThread(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        while (true) {
            int amount = (int) (Math.random() * 100) + 1;
            account.withdraw(amount);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


